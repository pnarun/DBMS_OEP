package OE.PROJECT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ModifyTableFrame extends JFrame{
	
	JLabel lbl1fname,lbl2sname,lbl3Gender,lbl4DOB,Heading;
    JTextField txtfname,txtsname,txtGender,txtDOB;
    JButton btnSave,btnBack;
    JLabel title = new JLabel("YOUR    UPDATE    WILL    REFLECT    IN    DATABASE");;
    
	ModifyTableFrame(String str[],String id)
	{
		super("  Modify Frame  ");
		setVisible(true);
        setSize(1600,800);
    	setLayout(null);
        setResizable(false);    
        
        
        
        Heading = new JLabel("UPDATE   EMPLOYEE   DETAILS");
        
        
        lbl1fname=new JLabel("NAME");
        txtfname=new JTextField(str[1],20);
        lbl3Gender=new JLabel("Gender");
        txtGender=new JTextField(str[2],20);
        lbl4DOB=new JLabel("Date Of Birth");
        txtDOB=new JTextField(str[3],20);
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
        
        
        add(Heading);
        add(lbl1fname);
        add(txtfname);
        add(lbl3Gender);
        add(txtGender);
        add(lbl4DOB);
        add(txtDOB);
       
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
            	new HomeFrame();
            	dispose();
            }

        });

        btnSave.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)
            {
            	String dept = "";
                String fname="";
                String gender="";
                String dateofbirth="";
            	
                
                String empid = id;
                
                
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
                
                String subid = id.substring(0, 3);
    			
    			if(subid.equals("1DE"))
    				dept = "DEVELOPMENT";
    			else if(subid.equals("1RE"))
    				dept = "RESEARCH";
    			else if(subid.equals("1TE"))
    				dept = "TESTING";
                
                //JOptionPane.showMessageDialog(new JDialog(),"Before modify1\n"+empid+"\n"+fname+"\n"+gender+"\n"+dateofbirth+"\n"+dept);
                
                DatabaseHandler query =new DatabaseHandler();
                if(dept.equals("DEVELOPMENT"))
                	query.modifyd(empid,fname,gender,dateofbirth,dept);
                else if(dept.equals("RESEARCH"))
                	query.modifyr(empid,fname,gender,dateofbirth,dept);
                else
                	query.modifyt(empid,fname,gender,dateofbirth,dept);
                	
                txtfname.setText("");
                txtGender.setText("");
                txtDOB.setText("");
                new HomeFrame();
                dispose();

            }

        });//end of btnsave actionhandler

	}
}
