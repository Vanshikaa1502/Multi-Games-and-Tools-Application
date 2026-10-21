import javax.swing.*;
import java.awt.*;
class GameMenu extends JPanel
{
	ImageIcon img1,img2,img3,img4;
	Image sg,tt,sl,g2;

	JButton b1,b2,b3,b4,b5;
GameMenu()
{
	setLayout(null);
b1=new JButton("Snake Game");
b1.setBounds(40,160,130,30);
b1.setBackground(Color.black);
b1.setForeground(Color.white);
add(b1);	

b2=new JButton("TIC TAC");
b2.setBounds(300,170,90,30);
b2.setBackground(Color.black);
b2.setForeground(Color.white);

add(b2);

b3=new JButton("Snake Ladder");
b3.setBounds(40,370,120,30);
b3.setBackground(Color.black);
b3.setForeground(Color.white);
add(b3);

b4=new JButton("BACK");
b4.setBounds(200,450,100,50);
b4.setBackground(Color.black);
b4.setForeground(Color.white);
add(b4);

b5=new JButton("2048");
b5.setBounds(300,370,100,50);
b5.setBackground(Color.black);
b5.setForeground(Color.white);
add(b5);

	img1=new ImageIcon ("snake1.jpg");
sg=img1.getImage();	

img2=new ImageIcon ("tt1.png");
tt=img2.getImage();	

img3=new ImageIcon ("sl1.jpg");
sl=img3.getImage();	

img4=new ImageIcon("2048.png");
g2=img4.getImage();
}	

public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	 g.drawImage(sg,10,50,this);
	 g.drawImage(tt,250,50,this);
	  g.drawImage(sl,10,250,this);
	g.drawImage(g2,250,250,this);
}
	
	
}