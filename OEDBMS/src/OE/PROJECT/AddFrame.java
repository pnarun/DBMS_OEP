package OE.PROJECT;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AddFrame extends JFrame{
	JPanel jp1,jp2;
    JLabel lbl1fname,lbl2sname,lbl3Gender,lbl4DOB,DepartmentSelection,Heading;
    JTextField txtfname,txtsname,txtGender,txtDOB;
    JButton btnSave,btnBack;
    JRadioButton d1, d2, d3;
    ButtonGroup G;

    AddFrame()
    {
        super("  Add Employee  ");
        setVisible(true);
        setSize(1600,800);
    	setLayout(null);
        setResizable(false);    
        d1 = new JRadioButton();
        d2 = new JRadioButton();
        d3 = new JRadioButton();
        d1.setText("Development");
        d2.setText("Research");
        d3.setText("Testing");
        
        DepartmentSelection = new JLabel("SELECT   DEPARTMENT");
        Heading = new JLabel("INSERT   EMPLOYEE   DETAILS");
        
        G = new ButtonGroup();
        
        lbl1fname=new JLabel("NAME");
        txtfname=new JTextField(10);
        lbl3Gender=new JLabel("Gender");
        txtGender=new JTextField(10);
        lbl4DOB=new JLabel("Date Of Birth");
        txtDOB=new JTextField(10);
        btnSave=new JButton("Save");
        btnBack=new JButton("Back");
        
        Heading.setSize(700,50);
        Heading.setLocation(600,100);
        lbl1fname.setSize(100,50);
        lbl1fname.setLocation(450,200);
        txtfname.setSize(300,25);
        txtfname.setLocation(550,200);
        lbl3Gender.setSize(100,25);
        lbl3Gender.setLocation(450,250);
        txtGender.setSize(300,25);
        txtGender.setLocation(550,250);
        lbl4DOB.setSize(100,25);
        lbl4DOB.setLocation(450,300);
        txtDOB.setSize(300,25);
        txtDOB.setLocation(550,300);
        btnSave.setSize(100,25);
        btnSave.setLocation(500,700);
        btnBack.setSize(100,25);
        btnBack.setLocation(700,700);
        DepartmentSelection.setSize(300,50);
        DepartmentSelection.setLocation(500,400);
        d1.setSize(100,50);
        d1.setLocation(500,450);
        d2.setSize(100,50);
        d2.setLocation(500,500);
        d3.setSize(100,50);
        d3.setLocation(500,550);
        
        add(Heading);
        add(lbl1fname);
        add(txtfname);
        add(lbl3Gender);
        add(txtGender);
        add(lbl4DOB);
        add(txtDOB);
        add(DepartmentSelection);
        add(d1);
        add(d2);
        add(d3);
        add(btnSave);
        add(btnBack);
        
        G.add(d1);
        G.add(d2);
        G.add(d3);
        
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
            	new HomeFrame();
            	dispose();
            }

        });

        btnSave.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)
            {
            	String dept = "";
            	
            	if(d1.isSelected())
            		dept = "DEVELOPMENT";
            	else if(d2.isSelected())
            		dept = "RESEARCH";
            	else if(d3.isSelected())
            		dept = "TESTING";
            	
            	if(dept=="")
            	{
            		JOptionPane.showMessageDialog(new JDialog()," Please select the Department. ");
            		return;
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(new JDialog()," You have selected "+dept+" as Department.");
            	}
            	
                String fname="";
                String gender="";
                String dateofbirth="";
                
                String empid = "";
                if(dept=="DEVELOPMENT")
                	empid = empid + "1DE";
                else if(dept=="RESEARCH")
                	empid = empid + "1RE";
                else 
                	empid = empid + "1TE";
                
                DatabaseHandler query1 =new DatabaseHandler();
                
                String deptID = query1.getDepartmentCount(dept);
                
                empid = empid + deptID;
                
                
                fname=txtfname.getText();

                //FNAME validation
                if(fname.length()==0)
                {
                    JOptionPane.showMessageDialog(new JDialog()," Invalid NAME.\nEnter NAME ");
                    txtfname.setText("");
                    txtfname.requestFocus();
                    return;
                }

                try
                {
                    if(!fname.matches("[a-zA-Z0-9_.-]{3,}"))
                    {
                        JOptionPane.showMessageDialog(new JDialog()," Invalid NAME. ");
                        txtfname.setText("");
                        txtfname.requestFocus();
                        return;
                    }

                }//end try
                catch(PatternSyntaxException pse)
                {
                	JOptionPane.showMessageDialog(new JDialog()," Pattern Syntax Exception in NAME. ");
                }
                
                
                gender=txtGender.getText();

                //gender validation
                if(gender.length()==0)
                {
                    JOptionPane.showMessageDialog(new JDialog()," Invalid gender.Enter gender ");
                    txtGender.setText("");
                    txtGender.requestFocus();
                    return;
                }

                try
                {
                	/*int got = 0;
                	if(gender == "male" || gender == "MALE" || gender == "Male" || gender == "female" || gender == "FEMALE" || gender == "Female")
                		got = 1;*/
                	if(gender.matches("[/^male$|^Male$|^MALE$|^female$|^Female$|^FEMALE$/g]"))
                    {
                        JOptionPane.showMessageDialog(new JDialog()," Invalid gender. ");
                        txtGender.setText("");
                        txtGender.requestFocus();
                        return;
                    }

                }//end try
                catch(PatternSyntaxException pse)
                {
                	JOptionPane.showMessageDialog(new JDialog()," Pattern Syntax Exception in gender. ");
                }
                
                dateofbirth=txtDOB.getText();

                //dateofbirth validation
                if(dateofbirth.length()==0)
                {
                    JOptionPane.showMessageDialog(new JDialog()," Invalid Date Of Birth.Enter Date Of Birth ");
                    txtDOB.setText("");
                    txtDOB.requestFocus();
                    return;
                }

                try
                {
                    if(dateofbirth.matches("[0-9_.-]{8,}"))
                    {
                        JOptionPane.showMessageDialog(new JDialog()," Invalid dateofbirth. ");
                        txtDOB.setText("");
                        txtDOB.requestFocus();
                        return;
                    }

                }//end try
                catch(PatternSyntaxException pse)
                {
                	JOptionPane.showMessageDialog(new JDialog()," Pattern Syntax Exception in dateofbirth. ");
                }
                String str = "Welcome to AH Company.\n"+empid+" is your ID and please not it for further use.";
                JOptionPane.showMessageDialog(new JDialog(),str);
                DatabaseHandler query =new DatabaseHandler();
                query.insert(empid,fname,gender,dateofbirth,dept);
                txtfname.setText("");
                txtGender.setText("");
                txtDOB.setText("");
                G.clearSelection();


            }

        });//end of btnsave actionhandler

    }
}
