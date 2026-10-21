// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// class Tictactoe extends JPanel implements ActionListener
// {
	// JButton b[]=new JButton[9];
	// JButton Re;
	// JLabel l;
	// Tictactoe()
	// {
		// setLayout(null);
		
		// Font f1=new Font("",Font.BOLD,30);
		// setFont(f1);
		// JLabel l1=new JLabel("LET'S START THE GAME");
		// l1.setSize(360,40);
		// l1.setLocation(180,100);
		// add(l1);
		// Color c=new Color(80,127,128);
		// l1.setBackground(c);

		// int x=200,y=200,k=0;
		// for(int i=1;i<=3;i++)
		// {
			// for(int j=1;j<=3;j++)
			// {
				// b[k]=new JButton();
				// b[k].setSize(100,100);
				// b[k].setLocation(x,y);
				// add(b[k]);
				// b[k].addActionListener(this);
				// Color c1=new Color(192,192,192);
				// b[k].setBackground(c1);
			// k++;
			// x+=100;
			// }
		    // x=200;
		    // y+=100;
		// }
		// Re=new JButton("RESTART");
		// Re.setBounds(450,570,150,50);
		// add(Re);
		// Re.addActionListener(this);
		// //setResizable(false);
	// }
	// int c=1;
	// public void actionPerformed(ActionEvent e)
	// {
		// JButton b1=(JButton)e.getSource();
		// if (e.getSource() == Re) 
        // {
			
           
		// for (int i = 0; i < 9; i++) 
        // {
            // b[i].setLabel("");
            // b[i].setBackground(new Color(192,192,192));
            // b[i].addActionListener(this); // add listener back
            
		
		// }

        // c = 1;   // reset move counter
        // if (l != null) remove(l); // remove win/draw label
        // repaint();
        // return;
        // }
		// if(c%2==0)
		// {
			// Color c2=new Color(219,125,174);
		    // b1.setBackground(c2);
			// b1.setLabel("O");
		// }
		// else
		// {
			// Color c3=new Color(255,254,145);
		    // b1.setBackground(c3);
			// b1.setLabel("X");
		// }
		// c++;
		// b1.removeActionListener(this);
	
	    // if((b[0].getLabel()=="O"&&b[1].getLabel()=="O"&&b[2].getLabel()=="O")||(b[3].getLabel()=="O"&&b[4].getLabel()=="O"&&b[5].getLabel()=="O")||(b[6].getLabel()=="O"&&b[7].getLabel()=="O"&&b[8].getLabel()=="O")||(b[0].getLabel()=="O"&&b[3].getLabel()=="O"&&b[6].getLabel()=="O")||(b[1].getLabel()=="O"&&b[4].getLabel()=="O"&&b[7].getLabel()=="O")||(b[2].getLabel()=="O"&&b[5].getLabel()=="O"&&b[8].getLabel()=="O")||(b[0].getLabel()=="O"&&b[4].getLabel()=="O"&&b[8].getLabel()=="O")||(b[2].getLabel()=="O"&&b[4].getLabel()=="O"&&b[6].getLabel()=="O"))
	    // {
		   // l=new JLabel("~O Wins~");
		   // l.setSize(800,60);
		   // l.setLocation(250,570);
		   // add(l);
		   // Color c4=new Color(0,0,0);
		   // l.setForeground(c4);
	    // }
	    // else if((b[0].getLabel()=="X"&&b[1].getLabel()=="X"&&b[2].getLabel()=="X")||(b[3].getLabel()=="X"&&b[4].getLabel()=="X"&&b[5].getLabel()=="X")||(b[6].getLabel()=="X"&&b[7].getLabel()=="X"&&b[8].getLabel()=="X")||(b[0].getLabel()=="X"&&b[3].getLabel()=="X"&&b[6].getLabel()=="X")||(b[1].getLabel()=="X"&&b[4].getLabel()=="X"&&b[7].getLabel()=="X")||(b[2].getLabel()=="X"&&b[5].getLabel()=="X"&&b[8].getLabel()=="X")||(b[0].getLabel()=="X"&&b[4].getLabel()=="X"&&b[8].getLabel()=="X")||(b[2].getLabel()=="X"&&b[4].getLabel()=="X"&&b[6].getLabel()=="X"))
	    // {
		// l=new JLabel("~X Wins~");
		// l.setSize(800,60);
		// l.setLocation(250,570);
		// add(l);
		// Color c4=new Color(0,0,0);
		// l.setForeground(c4);
	// }
	// else if(c>9)
	// {
		// l=new JLabel("IT'S A DRAW!!");
		// l.setSize(800,60);
		// l.setLocation(250,570);
		// add(l);	
	// }
	// }
// }
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Tictactoe extends JPanel implements ActionListener 
{
    JButton buttons[] = new JButton[9];
    JButton startBtn, restartBtn, closeBtn;
    JLabel status;
    JPanel boardPanel, controlPanel;

    boolean playerX = true;
    boolean gameStarted = false;

    Image background;

    Tictactoe() 
	{
        // setTitle("Tic Tac Toe Game");
        setSize(500,550);
        setLayout(new BorderLayout());
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        background = new ImageIcon("tttt.png").getImage();

        // status label
        status = new JLabel("Click START to begin", JLabel.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 18));
        status.setForeground(Color.DARK_GRAY);

        // Board panel with background
        boardPanel = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(background,0,0,getWidth(),getHeight(),this);
            }
        };

       boardPanel.setLayout(new GridLayout(3,3,10,10));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        Font btnFont = new Font("Arial",Font.BOLD,40);

        for(int i=0;i<9;i++){
            buttons[i] = new JButton("");
            buttons[i].setFont(btnFont);
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(new Color(240,240,240));
            buttons[i].addActionListener(this);
            boardPanel.add(buttons[i]);
        }

        // Control panel
        controlPanel = new JPanel();
        controlPanel.setBackground(new Color(230,240,250));

        startBtn = new JButton("Start Game");
        restartBtn = new JButton("Restart");
        closeBtn = new JButton("Close");

        startBtn.addActionListener(this);
        restartBtn.addActionListener(this);
        closeBtn.addActionListener(this);

        controlPanel.add(startBtn);
        controlPanel.add(restartBtn);
        controlPanel.add(closeBtn);

        add(status,BorderLayout.NORTH);
        add(boardPanel,BorderLayout.CENTER);
        add(controlPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==startBtn){
            gameStarted = true;
            status.setText("Player X Turn");
        }

        if(e.getSource()==restartBtn){
            resetGame();
        }

        if(e.getSource()==closeBtn){
            System.exit(0);
        }

        for(int i=0;i<9;i++){

            if(e.getSource()==buttons[i] && gameStarted){

                if(buttons[i].getText().equals("")){

                    if(playerX){
                        buttons[i].setText("X");
                        buttons[i].setForeground(Color.BLUE);
                        status.setText("Player O Turn");
                    }
                    else{
                        buttons[i].setText("O");
                        buttons[i].setForeground(Color.RED);
                        status.setText("Player X Turn");
                    }

                    playerX = !playerX;

                    checkWinner();
                }
            }
        }
    }

    void resetGame(){

        for(int i=0;i<9;i++){
            buttons[i].setText("");
        }

        playerX = true;
        gameStarted = false;
        status.setText("Click START to begin");
    }

    void checkWinner(){

        int win[][] = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for(int i=0;i<8;i++){

            int a = win[i][0];
            int b = win[i][1];
            int c = win[i][2];

            if(!buttons[a].getText().equals("") &&
               buttons[a].getText().equals(buttons[b].getText()) &&
               buttons[b].getText().equals(buttons[c].getText())){

                JOptionPane.showMessageDialog(this,
                        "Player " + buttons[a].getText() + " Wins!");

                resetGame();
                return;
            }
        }

        // Draw condition
        boolean draw = true;

        for(int i=0;i<9;i++){
            if(buttons[i].getText().equals("")){
                draw = false;
                break;
            }
        }

        if(draw){
            JOptionPane.showMessageDialog(this,"Game Draw!");
            resetGame();
        }
    }
}