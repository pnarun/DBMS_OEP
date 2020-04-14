package OE.PROJECT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchFrame extends JFrame{
	
	JLabel lbl1ID,Heading;
	JTextField txtID;
	JButton btnSave,btnBack;
	
	SearchFrame()
	{
		super("  Modify Employee  ");
		setVisible(true);
		setSize(1600,800);
		setLayout(null);
	    setResizable(false);     //the resize option is greyed out

	    Heading = new JLabel("ENTER    EMPLOYEE    ID    TO    SEARCH");
		lbl1ID=new JLabel("ID");
		txtID=new JTextField(10);
		
		btnSave=new JButton("Save");
		btnBack=new JButton("Back");
		
		Heading.setSize(700,50);
	    Heading.setLocation(600,100);
	    lbl1ID.setSize(100,50);
	    lbl1ID.setLocation(450,200);
	    txtID.setSize(300,25);
	    txtID.setLocation(550,200);
	    btnSave.setSize(100,25);
	    btnSave.setLocation(500,700);
	    btnBack.setSize(100,25);
	    btnBack.setLocation(700,700);
		add(Heading);
		add(lbl1ID);
		add(txtID);
		
		add(btnSave);
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

		btnSave.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent ae)
		{
			
			String id="";
			
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
				JOptionPane.showMessageDialog(new JDialog(),"The employee with id "+id+" belongs to "+dept+" Department");
			}//end try
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(new JDialog()," Invalid id ");
				//gets the focus back to id feild
				txtID.setText("");
				txtID.requestFocus();
				return;
			}//end catch
		
		
			DatabaseHandler query =new DatabaseHandler();
			String[] str = query.search(id,dept,1);
			txtID.setText("");
			
			}
		
			});//end of btnsave actionhandler

	}
}
