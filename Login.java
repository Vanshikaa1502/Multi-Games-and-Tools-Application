import javax.swing.*;
import java.awt.*;

class Login extends JPanel
{
    Image bgImage ,lgImage;
    JTextField t1, t2;
    JButton b1, b2;
    JLabel l1, l2;

    Login()
    {
        
        bgImage = new ImageIcon("background1.jpg").getImage();//image ko load krega
        lgImage = new ImageIcon("login.jpg").getImage();//image ko load krega
          
        setLayout(null);
         
        l1 = new JLabel("Enter Name");
        l1.setBounds(10,1, 220, 290);
		l1.setFont(new Font("Arial", Font.BOLD,30));
		l1.setForeground(Color.black);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(190, 120, 270, 50);
        t1.setFont(new Font("Arial", Font.BOLD, 20));
		t1.setBackground(Color.WHITE);
		t1.setForeground(Color.BLACK);
		add(t1);

        l2 = new JLabel("Enter Password");
        l2.setBounds(10, 100, 320, 290);
		l2.setFont(new Font("Arial", Font.BOLD, 30));
        l2.setForeground(Color.black);
		add(l2);

        t2 = new JPasswordField();
        t2.setBounds(250, 210, 230, 50);
        t2.setFont(new Font("Arial", Font.BOLD, 30));
		t2.setBackground(Color.WHITE);
		t2.setForeground(Color.BLACK);
		add(t2);

        
        b1 = new JButton("Sign Up");
        b1.setBounds(50, 370, 150, 90);
	    b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
		 b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Arial", Font.BOLD, 28));
		add(b1);

        b2 = new JButton("Login");
        b2.setBounds(220, 370, 150, 90);
		b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
		 b2.setForeground(Color.BLACK);
		 b2.setFont(new Font("Arial", Font.BOLD, 28));
		
        add(b2);
		//repaint();
    }

    // background image 
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(lgImage, 220, 40, 60, 60, this);
    }
}