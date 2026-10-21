import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

class SnakeLadder extends JPanel implements ActionListener
{
    Image board, diceImg;
    ImageIcon imgBoard, imgDice;

    int pos1=1,pos2=1;
    int px1,py1,px2,py2;

    boolean player1Turn=true;

    JLabel l1,l2;
    JButton rollBtn;
    JTextField player1NameField,player2NameField;

    String player1Name="Player 1";
    String player2Name="Player 2";

    Timer diceTimer;
    int rollCount=0;
    int finalDice;

    int[] snakeHead={27,40,43,54,65,76,89,99};
    int[] snakeTail={5,3,18,31,45,58,53,41};

    int[] ladderBottom={4,13,33,42,50,62,74};
    int[] ladderTop={25,46,49,63,69,81,92};

    SnakeLadder()
    {
        setLayout(null);
        setBackground(Color.white);

        imgBoard=new ImageIcon("board.jpeg");
        board=imgBoard.getImage();

        imgDice=new ImageIcon("dice1.jpeg");
        diceImg=imgDice.getImage();

        l1=new JLabel("Player 1");
        l1.setBounds(10,50,140,30);
        l1.setFont(new Font("Cambria Math",Font.BOLD,18));
        add(l1);

        l2=new JLabel("Player 2");
        l2.setBounds(10,100,140,30);
        l2.setFont(new Font("Cambria Math",Font.BOLD,18));
        add(l2);

        player1NameField=new JTextField("");
        player1NameField.setBounds(90,50,140,30);
        player1NameField.setFont(new Font("Cambria Math",Font.BOLD,18));
        add(player1NameField);

        player2NameField=new JTextField("");
        player2NameField.setBounds(90,100,140,30);
        player2NameField.setFont(new Font("Cambria Math",Font.BOLD,18));
        add(player2NameField);

        rollBtn=new JButton("ROLL");
        rollBtn.setBounds(50,500,120,40);
        rollBtn.addActionListener(this);
        add(rollBtn);

        updatePlayerPositions();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(board,300,20,700,700,this);

        g.setColor(Color.BLUE);
        g.fillOval(px1,py1,30,30);

        g.setColor(Color.BLACK);
        g.fillOval(px2,py2,30,30);

        g.drawImage(diceImg,70,400,80,80,this);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Cambria Math",Font.BOLD,18));

        player1Name=player1NameField.getText().trim();
        player2Name=player2NameField.getText().trim();

        g.drawString(player1Turn ?
        "Turn: "+player1Name+" (Blue)" :
        "Turn: "+player2Name+" (Black)",30,350);

        g.drawString(player1Name+" Position: "+pos1,30,200);
        g.drawString(player2Name+" Position: "+pos2,30,230);
    }

    public void actionPerformed(ActionEvent e)
    {
        rollDiceAnimation();
    }

    void rollDiceAnimation()
    {
        playSound("dice.wav");

        rollCount=0;

        diceTimer=new Timer(100,new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int dice=(int)(Math.random()*6)+1;

                imgDice=new ImageIcon("dice"+dice+".jpeg");
                diceImg=imgDice.getImage();

                repaint();

                rollCount++;

                if(rollCount>10)
                {
                    diceTimer.stop();

                    finalDice=(int)(Math.random()*6)+1;

                    imgDice=new ImageIcon("dice"+finalDice+".jpeg");
                    diceImg=imgDice.getImage();

                    movePlayer(finalDice);
                }
            }
        });

        diceTimer.start();
    }

    void movePlayer(int dice)
    {
        if(player1Turn)
        {
            if(pos1+dice<=100) pos1+=dice;

            if(pos1==100)
            {
                playSound("win.wav");
                JOptionPane.showMessageDialog(this,player1Name+" WINS");
                resetGame();
                return;
            }

            int old=pos1;
            pos1=checkSnake(pos1);
            if(old!=pos1) playSound("snakehiss.wav");

            int old2=pos1;
            pos1=checkLadder(pos1);
            if(old2!=pos1) playSound("ladder.wav");

            updatePlayerPositions();
        }
        else
        {
            if(pos2+dice<=100) pos2+=dice;

            if(pos2==100)
            {
                playSound("win.wav");
                JOptionPane.showMessageDialog(this,player2Name+" WINS");
                resetGame();
                return;
            }

            int old=pos2;
            pos2=checkSnake(pos2);
            if(old!=pos2) playSound("snakehiss.wav");

            int old2=pos2;
            pos2=checkLadder(pos2);
            if(old2!=pos2) playSound("ladder.wav");

            updatePlayerPositions();
        }

        player1Turn=!player1Turn;
        repaint();
    }

    int checkSnake(int pos)
    {
        for(int i=0;i<snakeHead.length;i++)
        {
            if(pos==snakeHead[i]) return snakeTail[i];
        }
        return pos;
    }

    int checkLadder(int pos)
    {
        for(int i=0;i<ladderBottom.length;i++)
        {
            if(pos==ladderBottom[i]) return ladderTop[i];
        }
        return pos;
    }

    void updatePlayerPositions()
    {
        Point p1=getCoordinates(pos1);
        px1=p1.x;
        py1=p1.y;

        Point p2=getCoordinates(pos2);
        px2=p2.x+25;
        py2=p2.y;
    }

    Point getCoordinates(int cell)
    {
        if(cell<1) cell=1;

        int row=(cell-1)/10;
        int col=(cell-1)%10;

        if(row%2==1) col=9-col;

        int cellSize=70;
        int startX=300;
        int startY=650;

        int x=startX+col*cellSize+10;
        int y=startY-row*cellSize+10;

        return new Point(x,y);
    }

    void resetGame()
    {
        pos1=1;
        pos2=1;
        player1Turn=true;
        updatePlayerPositions();
        repaint();
    }

    void playSound(String file)
    {
        try
        {
            AudioInputStream audio=AudioSystem.getAudioInputStream(new File(file));
            Clip clip=AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}