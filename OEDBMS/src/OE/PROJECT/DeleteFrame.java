package OE.PROJECT;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DeleteFrame extends JFrame{
	JPanel jp1,jp2;
	JLabel lbl1ID,Heading;
	JTextField txtID;
	JButton btnDel,btnBack;
	DeleteFrame()
	{
		super("  Delete Employee  ");
		setVisible(true);
		setSize(1600,800);
		setLayout(null);
	    setResizable(false);     //the resize option is greyed out
	
	    Heading = new JLabel("ENTER    EMPLOYEE    ID    TO    DELETE");
		lbl1ID=new JLabel("ID");
		txtID=new JTextField(10);
		
		btnDel=new JButton("Delete");
		btnBack=new JButton("Back");
		
		Heading.setSize(700,50);
	    Heading.setLocation(600,100);
	    lbl1ID.setSize(100,50);
	    lbl1ID.setLocation(450,200);
	    txtID.setSize(300,25);
	    txtID.setLocation(550,200);
	    btnDel.setSize(100,25);
	    btnDel.setLocation(500,700);
	    btnBack.setSize(100,25);
	    btnBack.setLocation(700,700);
		
		add(Heading);
		add(lbl1ID);
		add(txtID);
		
		add(btnDel);
		add(btnBack);
	
	
		//back button should take us back to homeframe
		btnBack.addActionListener(new ActionListener(){
	
		public void actionPerformed(ActionEvent e)
		{
			dispose();
			new HomeFrame();
		}
	
		});
	
	
		//close button also should take us back to homeframe
		addWindowListener(new WindowAdapter(){
	
		public void windowClosing(WindowEvent e)
		{
			dispose();
			new HomeFrame();
		}
	
		});
		
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id="";
				String name="";
				String subid = "";
				String dept = "";
				
				//id validation
				try
				{
					id = txtID.getText();
					//id validation.
					if(id==""){
						JOptionPane.showMessageDialog(new JDialog()," Invalid id.ID should not be EMPTY.");
						txtID.setText("");
						txtID.requestFocus();
						return;
					}
				
					subid = id.substring(0, 3);
					if(subid.equals("1DE"))
						dept = "DEVELOPMENT";
					else if(subid.equals("1RE"))
						dept = "RESEARCH";
					else if(subid.equals("1TE"))
						dept = "TESTING";
					else
					{
						JOptionPane.showMessageDialog(new JDialog()," Invalid id.\n Please enter valid ID.");
						//gets the focus back to id feild
						txtID.setText("");
						txtID.requestFocus();
						return;
					}
				}//end try
				catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(new JDialog()," Invalid id ");
					//gets the focus back to id feild
					txtID.setText("");
					txtID.requestFocus();
					return;
				}//end catch
			
			
				DatabaseHandler query =new DatabaseHandler();
				query.delete(id,dept);
				txtID.setText("");
				
			}
			
		});

	}
}
