import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class FDemo extends JFrame implements ActionListener
{
	CardLayout card;
	Container cn=getContentPane();
	Login log;
	Regis regis;
	Menu menu;
	ToolMenu tl;
	GameMenu gm;
	Snake sn;
	SnakeLadder sl;
	Cal cal;
	Tictactoe ttt;
	DigitalClock dig;
	Password pass;
	Game2048 g2048;
	
	FDemo()
	{
	setTitle("Minor");
	card=new CardLayout();
	setLayout(card);
	log=new Login();
	add("login",log);
	regis=new Regis();
	add("reg",regis);
	menu=new Menu();
	add("menu",menu);	
	gm=new GameMenu();
	add("gamemenu",gm);	
	sn=new Snake();
	add("snakegame",sn);
	sl=new SnakeLadder();
	add("snakeladder",sl);	
	tl=new ToolMenu();
	add("toolmenu",tl);
	cal=new Cal();
	add("calculator",cal);
	ttt=new Tictactoe();
	add("tic",ttt);
	dig=new DigitalClock();
	add("dig",dig);
	pass=new Password();
	add("pass",pass);
	g2048=new Game2048();
	add("2048",g2048);

	menu.setBackground(Color.black);
	gm.setBackground(Color.black);
	tl.setBackground(Color.black);
	cal.setBackground(Color.gray);

	menu.b1.addActionListener(this);	
	menu.b2.addActionListener(this);	
	gm.b1.addActionListener(this);	
	gm.b2.addActionListener(this);	
	gm.b3.addActionListener(this);	
	gm.b4.addActionListener(this);	
	tl.b1.addActionListener(this);	
	tl.b2.addActionListener(this);	
	tl.b3.addActionListener(this);	
	tl.b4.addActionListener(this);	
	log.b1.addActionListener(this);
	log.b2.addActionListener(this);
	regis.b1.addActionListener(this);
	regis.b2.addActionListener(this);
	gm.b5.addActionListener(this);
}	

public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==menu.b2)
	{
		card.show(cn,"gamemenu");
	}
	if(e.getSource()==gm.b4)
	{
		card.show(cn,"menu");
	}
	
	if(e.getSource()==gm.b5)
    {
    card.show(cn,"2048");

    Window w = SwingUtilities.getWindowAncestor((Component)e.getSource());

    if(w != null)
    {
        w.setBounds(100,50,500,600);
    }

    g2048.requestFocusInWindow();
}
	
	if(e.getSource()==gm.b3)
	{
		card.show(cn,"snakeladder");
	
	Window w = SwingUtilities.getWindowAncestor((Component)e.getSource());
    if (w != null) 
    {
        w.setBounds(0,0,1400,900);
    }
}
	
if(e.getSource()==gm.b1)
{
	card.show(cn,"snakegame");
	
	Window w = SwingUtilities.getWindowAncestor((Component)e.getSource());
if (w != null) 
{
    w.setBounds(0,0,1400,900);
}
	
	}
    if(e.getSource()==menu.b1)
	{
		card.show(cn,"toolmenu");
	}
    if(e.getSource()==tl.b2)
	{
		card.show(cn,"menu");
	}
	if(e.getSource()==tl.b4)
	{
		card.show(cn,"pass");
	
	Window w = SwingUtilities.getWindowAncestor((Component)e.getSource());
if (w != null) 
{
    w.setBounds(300,100,500,500);
}
	
	
	}
    if(e.getSource()==tl.b1)
	{
		card.show(cn,"calculator");
	
	Window w = SwingUtilities.getWindowAncestor((Component)e.getSource());
if (w != null) 
{
    w.setSize(420,650);
}
	
	}
    if(e.getSource()==gm.b2)
	{
		card.show(cn,"tic");
	}
	if(e.getSource()==log.b1)
	{
		card.show(cn,"reg");
	}
	if(e.getSource()==regis.b1)
	{
		card.show(cn,"login");
	}
	
	if(e.getSource()==log.b2)
	{
	String s1=log.t1.getText();
	String s2=log.t2.getText();
	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql:///minor","root","root");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from login where NAME='"+s1+"' AND PASS='"+s2+"'");
	if(rs.next())
	{
	card.show(cn,"menu");	
	}
	else
	{
	JOptionPane.showMessageDialog(null,"Invalid Username or Password","",JOptionPane.ERROR_MESSAGE);	
	log.t1.setText("");
	log.t2.setText("");
	}
	}
	catch(Exception e1)
	{
		System.out.println(e1);
	}
	}	
		
    if(e.getSource()==tl.b3)
	{
		card.show(cn,"dig");
	
		Window w = SwingUtilities.getWindowAncestor((Component)e.getSource());
	if (w != null) //clock ki frame size change krne ke liye
	{
		w.setSize(400,200);
	}
		}	
	if(e.getSource()==regis.b2)
	{
	String s11=regis.t1.getText();
	String s12=regis.t2.getText();
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql:///minor","root","root");
		Statement st=con.createStatement();
		st.executeUpdate("insert into login values('"+s11+"','"+s12+"')");	
		JOptionPane.showMessageDialog(null,"REGISTRATION COMPLETE","",JOptionPane.PLAIN_MESSAGE);	
		regis.t1.setText("");
		regis.t2.setText("");
		card.show(cn,"login");
	}
	catch(Exception e1)
	{
		System.out.println(e1);
	}	
	}	
	}
}
class Minor
{
	static public void main(String ar[])
	{
		Snake sn= new Snake();
		Game2048 g = new Game2048();
		FDemo f1=new FDemo();
		f1.setVisible(true);
		sn.requestFocusInWindow();
		g.requestFocusInWindow();
		f1.setBounds(450,90,500,650);	
		f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);	
    }	
}






//set CLASSPATH=.;C:\New folder\New folder\Minor\mysql-connector-j-9.4.0\mysql-connector-j-9.4.0.jar;