package OE.PROJECT;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HomeFrame extends JFrame{
	static final String dbClass = "com.mysql.jdbc.Driver";
    static final String dbUrl = "jdbc:mysql://localhost/dbms_project";
    static final String dbUsr = "root";

    JButton btnAdd,btnModify,btnDelete,btnView,btnSearch;
    JLabel title = new JLabel("EMPLOYEE   DATABASE   MANAGEMENT   SIMULATOR");;

HomeFrame()
{
    super("  Home Frame  ");
    setVisible(true);
	setSize(1600,800);
	setLayout(null);
    setResizable(false);   
    btnAdd=new JButton("Add");
    btnModify=new JButton("Modify");
    btnDelete=new JButton("Delete");
    btnView=new JButton("View");
    btnSearch=new JButton("Search");
    title.setSize(700,50);
	title.setLocation(600,100);
	btnAdd.setSize(300,40);
	btnAdd.setLocation(300,300);
	btnModify.setSize(300,40);
	btnModify.setLocation(900,300);
	btnDelete.setSize(300,40);
	btnDelete.setLocation(300,450);
	btnView.setSize(300,40);
	btnView.setLocation(900,450);
	btnSearch.setSize(300,40);
	btnSearch.setLocation(600,600);
	add(title);
	
    add(btnAdd);
    add(btnModify);
    add(btnDelete);
    add(btnView);
    add(btnSearch);
    
    setLocationRelativeTo(null);   
    
    setVisible(true);

    //navigation to add
    btnAdd.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	dispose();//dispose the current frame
            new AddFrame();
        }

    });

    //navigation to modify
    btnModify.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	dispose();//dispose the current frame
            new ModifyFrame();
        }

    });

    btnDelete.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	dispose();//dispose the current frame
            new DeleteFrame();
        }

    });

    btnView.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	dispose();//dispose the current frame
            new ViewFrame();
        }

    });

    btnSearch.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	dispose();//dispose the current frame
            new SearchFrame();
        }

    });

}




public static void main(String args[])
{
	new HomeFrame();
}


}

class DatabaseHandler
{
    static Connection con;
    static final String dbClass = "com.mysql.jdbc.Driver";
    static final String dbUrl = "jdbc:mysql://localhost:3306/dbms_project";
    static final String dbUsr = "root";
    static final String dbpwd = "";

    public static void getConnection(){
    	// For tracing of Error
//    	StackTraceElement[] st = Thread.currentThread().getStackTrace();
//        System.out.println(  "create connection called from " + st[2] );
//        JOptionPane.showMessageDialog(new JDialog(),"create connection called from " + st[2]);
        try
        {
            Class.forName(dbClass);
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_project","root","");
            java.sql.DriverManager.getConnection(dbUrl,dbUsr,dbpwd);
        }//end of try
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(new JDialog()," "+e);
            System.out.println("error in loading driver"+e);
            //System.exit(1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," "+e);
        	System.out.println(" "+e);
            //System.exit(1);
        }


    }//end getconnection


    //doubt in whole method
    public void insert(String id,String name,String gender,String dateofbirth,String dept)
    {
    	String dept_count1="";
    	String dup_dep="";
        try
        {
            getConnection();
            if(dept.equals("DEVELOPMENT"))
            	dup_dep = "dev";
            else if(dept.equals("RESEARCH"))
            	dup_dep = "research";
            else if(dept.equals("TESTING"))
            	dup_dep = "testing";
            String q="insert into `"+dup_dep+"` values(?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,id);
            pst.setString(2,name);
            pst.setString(3,gender);
            pst.setString(4,dateofbirth);
            
            dept_count1 = getDepartmentCount(dept,0);
            
            String p = "update department set dept_count=? where dept_name=?";
            PreparedStatement pstd=con.prepareStatement(p);
            pstd.setString(1,dept_count1);
            pstd.setString(2,dept);
            
            int i=pst.executeUpdate();
            int j=pstd.executeUpdate();
            JOptionPane.showMessageDialog(new JDialog()," 1 record added");

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," Error in insertion. "+e);
            System.out.println( ""+e );
        }


    }//insert end
    
    public String[] search(String id,String dept,int ch)
    {
    	String[] str = new String[4];
    	String dup_dep = "";
    	String empid="";
		String name="";
		String gender = "";
		String dob = "";
		String get = "";
        try
        {
            getConnection();
            if(dept.equals("DEVELOPMENT"))
            {
            	get = "select * from dev where id=?";
            	dup_dep = "dev";
            }
            	
            else if(dept.equals("RESEARCH"))
            {
            	get = "select * from research where id=?";
            	dup_dep = "research";
            }
            	
            else if(dept.equals("TESTING"))
            {
            	get = "select * from testing where id=?";
            	dup_dep = "testing";
            }
            
    		PreparedStatement pst=con.prepareStatement(get);
    		
            pst.setString(1,id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            rs.last();
            int x=rs.getRow();
            rs.first();
            
            if(x>0) 
            {
            	empid = rs.getString(1);
            	name = rs.getString(2);
            	gender = rs.getString(3);
            	dob = rs.getString(4);
            	
            	str[0]=empid;
            	str[1]=name;
            	str[2]=gender;
            	str[3]=dob;
            	
            	if(ch==1)
            	{
            		JOptionPane.showMessageDialog(new JDialog(),"ID :  "+str[0]+"\nNAME : "+str[1]+"\nGENDER : "+str[2]+"\nDATE OF BIRTH : "+str[3]+"\nDEPARTMENT : "+dept);
            		String column[] = {"ID","NAME","GENDER","DATE OF BIRTH"};
    		    	String display[][] = new String[1][4];
    				display[0][0] = str[0];
    				display[0][1] = str[1];
    				display[0][2] = str[2];
    				display[0][3] = str[3];
    				
    				JFrame f = new JFrame();
    		        JTable j;
    				j = new JTable(display, column);
    				j.setDefaultEditor(Object.class, null);
    				JScrollPane sp = new JScrollPane(j); 
    				f.add(sp); 
    				f.setSize(500,500); 
    				f.setVisible(true);
            	}
            	
            	return str;
            }
            else
            {
            	JOptionPane.showMessageDialog(new JDialog()," No Data found. ");
            }
            	
            
        }
        catch(Exception e)
        {
        	JOptionPane.showMessageDialog(new JDialog()," Error in modification ");
        }
        return str;

    }//modify end
    
    public void modifyd(String id,String name,String gender,String dateofbirth,String dept)
    {
    	
    	String p = "";
    	String q = "";
    	String r = "";
    	String dup_dep = "";
        try
        {
            getConnection();
            
            	dup_dep = "dev";
            	
            	p = "update dev set name=? where id=?";
            	q = "update dev set gender=? where id=?";
            	r = "update dev set dateofbirth=? where id=?";
            	
            	PreparedStatement pst=con.prepareStatement(p);
                pst.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst.setString(1,name);
                int i=pst.executeUpdate();
                if(i!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Name modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Name modification");
                
                PreparedStatement pst1=con.prepareStatement(q);
                pst1.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst1.setString(1,gender);
                int j=pst1.executeUpdate();
                if(j!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Gender modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Gender modification");
                
                PreparedStatement pst2=con.prepareStatement(r);
                pst2.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst2.setString(1,dateofbirth);
                int k=pst2.executeUpdate();
                if(k!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Date of Birth modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Date of Birth modification");
                
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," Error in modification "+e);
        }
        String str = "Update Successfull.";
        JOptionPane.showMessageDialog(new JDialog(),str);

    }//modify end
    
    public void modifyr(String id,String name,String gender,String dateofbirth,String dept)
    {
    	
    	String p = "";
    	String q = "";
    	String r = "";
    	String dup_dep = "";
    	
        try
        {
            getConnection();
            
            	dup_dep = "research";
            	
            	p = "update research set name=? where id=?";
            	q = "update research set gender=? where id=?";
            	r = "update research set dateofbirth=? where id=?";
            	
            	PreparedStatement pst=con.prepareStatement(p);
                pst.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst.setString(1,name);
                int i=pst.executeUpdate();
                
                if(i!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Name modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Name modification");
                
                PreparedStatement pst1=con.prepareStatement(q);
                pst1.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst1.setString(1,gender);
                int j=pst1.executeUpdate();
                
                if(j!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Gender modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Gender modification");
                
                PreparedStatement pst2=con.prepareStatement(r);
                pst2.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst2.setString(1,dateofbirth);
                int k=pst2.executeUpdate();
                
                if(k!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Date of Birth modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Date of Birth modification");
                
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," Error in modification "+e);
        }
        String str = "Update Successfull.";
        JOptionPane.showMessageDialog(new JDialog(),str);

    }//modify end
    
    public void modifyt(String id,String name,String gender,String dateofbirth,String dept)
    {
    	
    	String p = "";
    	String q = "";
    	String r = "";
    	String dup_dep = "";
    	
        try
        {
            getConnection();
            
            	dup_dep = "testing";
            	
            	p = "update testing set name=? where id=?";
            	q = "update testing set gender=? where id=?";
            	r = "update testing set dateofbirth=? where id=?";
            	
            	PreparedStatement pst=con.prepareStatement(p);
                pst.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst.setString(1,name);
                int i=pst.executeUpdate();
                
                if(i!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Name modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Name modification");
                
                PreparedStatement pst1=con.prepareStatement(q);
                pst1.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst1.setString(1,gender);
                int j=pst1.executeUpdate();
                
                if(j!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Gender modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Gender modification");
                
                PreparedStatement pst2=con.prepareStatement(r);
                pst2.setString(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
                pst2.setString(1,dateofbirth);
                int k=pst2.executeUpdate();
                
                if(k!=0){
                    JOptionPane.showMessageDialog(new JDialog()," Date of Birth modified");
                }
                else
                	JOptionPane.showMessageDialog(new JDialog()," Failed in Date of Birth modification");
               
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," Error in modification "+e);
        }
        String str = "Update Successfull.";
        JOptionPane.showMessageDialog(new JDialog(),str);

    }//modify end

    public void delete(String id,String dept)
    {
    	String dept_count1="";
    	String dup_dep = "";
        try
        {
            getConnection();
            if(dept.equals("DEVELOPMENT"))
            	dup_dep = "dev";
            else if(dept.equals("RESEARCH"))
            	dup_dep = "research";
            else if(dept.equals("TESTING"))
            	dup_dep = "testing";
            String q="delete from `"+dup_dep+"` where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,id); 


            int i=pst.executeUpdate();
            
            
            if(i==0){
                JOptionPane.showMessageDialog(new JDialog()," 0 record deleted");
            }
            else
            {
                JOptionPane.showMessageDialog(new JDialog()," 1 record deleted");
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," Error in deletion ");
        }


    }//delete end
    
    public String getDepartmentCount(String dept,int ch)
    {
    	String temp="";
    	try
    	{
    		getConnection();
    		String get = "select dept_count from department where dept_name=?";
    		PreparedStatement pst=con.prepareStatement(get);
            pst.setString(1,dept);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if(rs.getRow()<1)
            {
            	temp = "001";
            }
            else
            {
            	temp = rs.getString(1);
            	int x = Integer.parseInt(temp);
            	if(ch==0)
            		x = x + 1;
            	else
            		x = x - 1;
            	temp = Integer.toString(x);
            	if(temp.length()==1)
            		temp = "00" + temp;
            	else if(temp.length() == 2)
            		temp = "0" + temp;
            }
    	}
    	catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," "+e);
        }
    	return temp;
    }

    public void query(String dept)
    {
    	String column[] = {"ID","NAME","GENDER","DATE OF BIRTH"};
        try
        {
            getConnection();
            String view="select * from "+dept+" ";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(view);
            rs.last();
            int x=rs.getRow();
            String[][] str = new String[x][4];
            rs.first();
            int i = 0;
            do 
            {
            	str[i][0]=rs.getString(1);
            	str[i][1]=rs.getString(2);
            	str[i][2]=rs.getString(3);
            	str[i][3]=rs.getString(4);
            	i++;
            }while(rs.next());
        	
            JFrame f = new JFrame();
            JTable j;
    		j = new JTable(str, column);
    		j.setDefaultEditor(Object.class, null);
    		JScrollPane sp = new JScrollPane(j); 
    		f.add(sp); 
   			f.setSize(500,500); 
   			f.setVisible(true); 
            rs.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," "+e);
        }  
    }
    


}//end dbhandler
