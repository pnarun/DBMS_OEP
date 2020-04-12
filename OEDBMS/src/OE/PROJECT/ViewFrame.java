package OE.PROJECT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewFrame extends JFrame{
	
	JScrollPane sp1;
	JButton dept1,dept2,dept3,btnBack;
	JLabel Heading;
	JTable table;
	
	ViewFrame()
	{
	super("  View all Employees  ");
	setVisible(true);
    setSize(1600,800);
	setLayout(null);
    setResizable(false); 
	
	Heading = new JLabel("SELECT   THE   DEPARTMENT");
	dept1 = new JButton("DEVELOPMENT");
	dept2 = new JButton("RESEARCH");
	dept3 = new JButton("TESTING");
	btnBack=new JButton("BACK");
	
	Heading.setSize(300,100);
	Heading.setLocation(600,100);
	dept1.setSize(300,50);
	dept1.setLocation(600,300);
	dept2.setSize(300,50);
	dept2.setLocation(600,400);
	dept3.setSize(300,50);
	dept3.setLocation(600,500);
	btnBack.setSize(300,50);
	btnBack.setLocation(600,600);
	
	
	add(Heading);
	add(dept1);
	add(dept2);
	add(dept3);
    add(btnBack);
	
	setLocationRelativeTo(null);   //SETS THE WHOLE FRAME AT CENTRE
	setVisible(true);

	//back button should take us back to homeframe
    btnBack.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e)
        {
        	dispose();
            new HomeFrame();
        }

    });

	addWindowListener(new WindowAdapter(){

	public void windowClosing(WindowEvent e)
	{
		new HomeFrame();
		dispose();
	}

	});

	dept1.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e)
		{
			DatabaseHandler q =new DatabaseHandler();
			q.query("dev");
		}
		
	});
	
	dept2.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e)
		{
			DatabaseHandler q =new DatabaseHandler();
			q.query("research");
		}
		
	});

	dept3.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e)
		{
			DatabaseHandler q =new DatabaseHandler();
			q.query("testing");
		}
		
	});

	}
}
