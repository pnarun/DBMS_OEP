package OE.PROJECT;


import java.awt.FlowLayout;
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

    JButton btnAdd,btnModify,btnDelete,btnView;
    JLabel title = new JLabel("EMPLOYEE   DATABASE   MANAGEMENT   SIMULATOR");;

HomeFrame()
{
    super("  Home Frame  ");
    setVisible(true);
	setSize(1600,800);
	setLayout(null);
    setResizable(false);    //the resize option is greyed out
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    btnAdd=new JButton("Add");
    btnModify=new JButton("Modify");
    btnDelete=new JButton("Delete");
    btnView=new JButton("View");
    title.setSize(700,50);
	title.setLocation(600,100);
	btnAdd.setSize(300,40);
	btnAdd.setLocation(600,300);
	btnModify.setSize(300,40);
	btnModify.setLocation(600,400);
	btnDelete.setSize(300,40);
	btnDelete.setLocation(600,500);
	btnView.setSize(300,40);
	btnView.setLocation(600,600);
	add(title);
	
    //setLayout(new FlowLayout());//default centre
    add(btnAdd);
    add(btnModify);
    add(btnDelete);
    add(btnView);
    
    setLocationRelativeTo(null);   //y not setLayout(centre)?doubt
    /*btnAdd.setLocation(100, 100);
    btnModify.setLocation(200, 100);
    btnDelete.setLocation(300, 100);
    btnView.setLocation(400, 100);*/
    
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



}




public static void main(String args[])
{
	HomeFrame h=new HomeFrame();
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
            if(dept=="DEVELOPMENT")
            	dup_dep = "dev";
            else if(dept=="RESEARCH")
            	dup_dep = "research";
            else if(dept == "TESTING")
            	dup_dep = "testing";
            String q="insert into `"+dup_dep+"` values(?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(q);
            //pst.setString(1,dup_dep);
            pst.setString(1,id);
            pst.setString(2,name);
            pst.setString(3,gender);
            pst.setString(4,dateofbirth);
            
            dept_count1 = getDepartmentCount(dept);
            
            String p = "update department set dept_count=? where dept_name=?";
            PreparedStatement pstd=con.prepareStatement(p);
            pstd.setString(1,dept_count1);
            pstd.setString(2,dept);
            
            /*
            String m = "insert into manager values (?,?)";
            PreparedStatement pstm=con.prepareStatement(p);
            pstm.setString(1,mgr_id);
            pstm.setString(2,mgr_name);
            */
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

    public void modify(int id,String name)
    {
        try
        {
            getConnection();
            String q="update employee set name=? where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setInt(2,id); //this becomes 2 as the parameter ie id is accepted nd in the query
            pst.setString(1,name);

            int i=pst.executeUpdate();
            if(i==0){
                JOptionPane.showMessageDialog(new JDialog()," 0 record modified");
            }
            else
            {
                JOptionPane.showMessageDialog(new JDialog()," 1 record modified");
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(new JDialog()," Error in modification ");
        }


    }//modify end

    public void delete(int id)
    {
        try
        {
            getConnection();
            String q="delete from employee where id=?";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setInt(1,id); 


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
    
    public String getDepartmentCount(String dept)
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
            	x = x+ 1;
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
            String view="select * from "+dept+" order by id";
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
//            
        	
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
