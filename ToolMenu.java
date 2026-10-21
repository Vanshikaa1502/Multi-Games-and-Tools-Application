import javax.swing.*;
import java.awt.*;

class ToolMenu extends JPanel
{
ImageIcon img1,img2,img3;
	Image cal,dig,pass;
	JButton b1,b2,b3,b4;
ToolMenu()
{
	setLayout(null);
img1=new ImageIcon ("Cal1.jpg");
cal=img1.getImage();	
img2=new ImageIcon ("clock.png");
dig=img2.getImage();	
img3=new ImageIcon ("passwordf.png");
pass=img3.getImage();	
b1=new JButton("Calculator");
b1.setBounds(50,170,100,30);
b1.setBackground(Color.black);
b1.setForeground(Color.white);
add(b1);

b3=new JButton("DigitalClock");
b3.setBounds(260,170,150,30);
b3.setBackground(Color.black);
b3.setForeground(Color.white);
add(b3);

b4=new JButton("Password Generator");
b4.setBounds(10,380,170,30);
b4.setBackground(Color.black);
b4.setForeground(Color.white);
add(b4);



b2=new JButton("Back");
b2.setBounds(220,550,90,30);
b2.setBackground(Color.black);
b2.setForeground(Color.white);
add(b2);

	
}

public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	 g.drawImage(cal,10,50,this);
	 g.drawImage(dig,270,50,this);
	 g.drawImage(pass,30,250,this);//(pass,len,bred,x,y,this)
	 
}

}