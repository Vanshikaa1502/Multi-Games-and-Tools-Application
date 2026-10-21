import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game2048 extends JPanel 
{
    private static final int GRID_SIZE = 4;
    private static final int TILE_SIZE = 100;
    private int[][] board = new int[GRID_SIZE][GRID_SIZE];
    private int score = 0;

    public Game2048() 
	{
        setPreferredSize(new Dimension(450, 500)); // Increased height for the button
        setBackground(new Color(187, 173, 160));
        setFocusable(true);
        resetGame();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean moved = false;
                switch (e.getKeyCode()) 
				{
                    case KeyEvent.VK_UP:    moved = moveUp();    break;
                    case KeyEvent.VK_DOWN:  moved = moveDown();  break;
                    case KeyEvent.VK_LEFT:  moved = moveLeft();  break;
                    case KeyEvent.VK_RIGHT: moved = moveRight(); break;
                }
                
                if (moved) 
				{
                    addRandomTile();
                    if (isGameOver()) 
					{
                        JOptionPane.showMessageDialog(null, "Game Over! Score: " + score);
                    }
                }
                repaint();
            }
        });
    }

    private void resetGame() 
	{
        score = 0;
        board = new int[GRID_SIZE][GRID_SIZE];
        addRandomTile();
        addRandomTile();
        repaint();
    }

    private void addRandomTile() 
	{
        java.util.List<int[]> emptyCells = new ArrayList<int[]>();
        for (int r = 0; r < GRID_SIZE; r++) 
		{
            for (int c = 0; c < GRID_SIZE; c++) 
			{
                if (board[r][c] == 0) emptyCells.add(new int[]{r, c});
            }
        }
        if (!emptyCells.isEmpty()) 
		{
            int[] cell = emptyCells.get(new Random().nextInt(emptyCells.size()));
            board[cell[0]][cell[1]] = Math.random() < 0.9 ? 2 : 4;
        }
    }

    // --- Movement Logic ---
    private boolean moveLeft() 
	{
        boolean moved = false;
        for (int i = 0; i < GRID_SIZE; i++) 
		{
            int[] row = board[i];
            int[] newRow = new int[GRID_SIZE];
            int pos = 0;
            for (int j = 0; j < GRID_SIZE; j++) 
			{
                if (row[j] != 0) 
				{
                    if (pos > 0 && newRow[pos - 1] == row[j]) 
					{
                        newRow[pos - 1] *= 2;
                        score += newRow[pos - 1];
                        moved = true;
                    }
					else 
					{
                        newRow[pos++] = row[j];
                    }
                }
            }
            if (!Arrays.equals(board[i], newRow)) moved = true;
            board[i] = newRow;
        }
        return moved;
    }

    private boolean moveRight() { reverseBoard(); boolean m = moveLeft(); reverseBoard(); return m; }
    private boolean moveUp() { transpose(); boolean m = moveLeft(); transpose(); return m; }
    private boolean moveDown() { transpose(); reverseBoard(); boolean m = moveLeft(); reverseBoard(); transpose(); return m; }

    private void reverseBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE / 2; j++) {
                int temp = board[i][j];
                board[i][j] = board[i][GRID_SIZE - 1 - j];
                board[i][GRID_SIZE - 1 - j] = temp;
            }
        }
    }

    private void transpose() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = i; j < GRID_SIZE; j++) {
                int temp = board[i][j];
                board[i][j] = board[j][i];
                board[j][i] = temp;
            }
        }
    }

    private boolean isGameOver() {
        for (int i = 0; i < GRID_SIZE; i++)
            for (int j = 0; j < GRID_SIZE; j++)
                if (board[i][j] == 0) return false;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE - 1; j++) {
                if (board[i][j] == board[i][j+1] || board[j][i] == board[j+1][i]) return false;
            }
        }
        return true;
    }

    // Colors for the tiles
    private Color getTileColor(int value) {
        switch (value) {
            case 2:    return new Color(0xeee4da);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xedcc61);
            case 512:  return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                int val = board[r][c];
                g.setColor(getTileColor(val));
                g.fillRoundRect(c * 110 + 10, r * 110 + 10, TILE_SIZE, TILE_SIZE, 15, 15);
                if (val != 0) {
                    g.setColor(val <= 4 ? new Color(0x776e65) : Color.WHITE);
                    String s = String.valueOf(val);
                    FontMetrics fm = g.getFontMetrics();
                    int x = c * 110 + 10 + (TILE_SIZE - fm.stringWidth(s)) / 2;
                    int y = r * 110 + 10 + (TILE_SIZE + fm.getAscent()) / 2 - 5;
                    g.drawString(s, x, y);
                }
            }
        }
        g.setColor(new Color(0x776e65));
        g.drawString("Score: " + score, 10, 475);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2048 Java Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        final Game2048 game = new Game2048();
        frame.add(game, BorderLayout.CENTER);

        // Control Panel for Restart Button
        JPanel ctrl = new JPanel();
        JButton restartBtn = new JButton("Restart Game");
        restartBtn.setFocusable(false); // Crucial: otherwise button steals keyboard focus
        restartBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.resetGame();
            }
        });
        ctrl.add(restartBtn);
        frame.add(ctrl, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}