package jdbc;
import java.util.*;
import java.sql.*;
public class PreparedStatementApp {
	public static void main(String args[])
	{
		try {
			int choice;
			Scanner sc=new Scanner(System.in);
			//com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
			//DriverManager.registerDriver(d);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc1","root","Pallavi@9175");
			PreparedStatement pst ;
			String Query="create table student(id int(5),name varchar(200),email varchar(200),contact varchar(200),address varchar(200))";
			ResultSet vs;
			do {
				System.out.println("1:create table name student");
				System.out.println("2:insert 10 record in table");
				System.out.println("3:display all record");
				System.out.println("4:display all student record using all column values");
				System.out.println("5:display only name email and contact");
				System.out.println("6:display distinct name from student");
				System.out.println("7:delete student whose id is 1");
				System.out.println("8:delete student whose id is 1 and name is ram");
				System.out.println("9:update student whose id is 1 and name is shyam");
				System.out.println("10:chage name email contact whose id is 3");
				System.out.println("enter your choice");
				choice=sc.nextInt();
				switch(choice)
				{
					case 1:
						pst = con.prepareStatement(Query);
						int v=pst.executeUpdate();
						if(v==0)
						{
							System.out.println("table created....");
						}else {
							System.out.println("table not created....");
						}
						
					break;
					case 2:
						System.out.println("enter id,name,email,contact,address");
						int id=sc.nextInt();
						sc.nextLine();
						String name=sc.nextLine();
						String email=sc.nextLine();
						String contact=sc.nextLine();
						String address=sc.nextLine();
						pst=con.prepareStatement("insert into student values(?,?,?,?,?)");
						pst.setInt(1,id);
						pst.setString(2,name);
						pst.setString(3,email);
						pst.setString(4, contact);
						pst.setString(5, address);
					 v=pst.executeUpdate();
					 if(v>0)
					 {
						 System.out.println("student is added");
					 }else {
						 System.out.println("some problem is there");
					 }
						break;
					case 3:
						pst=con.prepareStatement("select *from student");
						vs=pst.executeQuery();
						while(vs.next())
						{
							System.out.println(vs.getInt(1)+"\t\t"+vs.getString(2)+"\t\t"+vs.getString(3)+"\t\t"+vs.getString(4)+"\t\t"+vs.getString(5));
						}
						break;
					case 4:
						pst=con.prepareStatement("select *from student");
						vs=pst.executeQuery();
						while(vs.next())
						{
							System.out.println(vs.getInt(1)+"\t\t"+vs.getString(2)+"\t\t"+vs.getString(3)+"\t\t"+vs.getString(4)+"\t\t"+vs.getString(5));
						}
						break;
					case 5:
						pst=con.prepareStatement("select name,email,contact from  student");
						vs=pst.executeQuery();
						while(vs.next())
						{
							System.out.println(vs.getString("name")+"\t\t"+vs.getString("email")+"\t\t"+vs.getString("contact"));
						}
						break;
					case 6:
						pst=con.prepareStatement("select distinct name from student");
						vs=pst.executeQuery();
						while(vs.next())
						{
							System.out.println(vs.getString("name"));
						}
						break;
					case 7:
						System.out.println("enter id for delete:");
						int ino=sc.nextInt();
						pst=con.prepareStatement("delete from student where id=?");
						pst.setInt(1,ino);
						v=pst.executeUpdate();
						if(v>0)
						{
							System.out.println("student is deleted..");
						}else {
							System.out.println("some problem is there.......");
						}
						break;
					case 8:
						System.out.println("enter id and name");
						int sid=sc.nextInt();
						sc.nextLine();
						String sname=sc.nextLine();
						pst=con.prepareStatement("delete from student where id=? and name=?");
						pst.setInt(1,sid);
						pst.setString(2, sname);
						v=pst.executeUpdate();
						if(v>0)
						{
							System.out.println("employee delete from table");
						}else {
							System.out.println("some problem is there...");
						}
						break;
					case 9:
						System.out.println("enter id where we want to update");
						int uid=sc.nextInt();
						System.out.println("enter id name email contact address");
						sid=sc.nextInt();
						sc.nextLine();
						sname=sc.nextLine();
						email=sc.nextLine();
						contact=sc.nextLine();
						address=sc.nextLine();
						pst=con.prepareStatement("update student set id=?,name=?,email=?,contact=?,address=? where id=? ");
						pst.setInt(1, sid);
						pst.setString(2, sname);
						pst.setString(3, email);
						pst.setString(4, contact);
						pst.setString(5, address);
						pst.setInt(6, uid);
						v=pst.executeUpdate();
						if(v>0)
						{
							System.out.println("employee update from table");
						}else {
							System.out.println("some problem is there...");
						}
						break;
					case 10:
						System.out.println("enter id where we want to update");
						uid=sc.nextInt();
						System.out.println("enter  name email contact ");
						sc.nextLine();
						sname=sc.nextLine();
						email=sc.nextLine();
						contact=sc.nextLine();
						pst=con.prepareStatement("update student set name=?,email=?,contact=? where id=? ");
						pst.setString(1, sname);
						pst.setString(2, email);
						pst.setString(3, contact);
						pst.setInt(4, uid);
						v=pst.executeUpdate();
						if(v>0)
						{
							System.out.println("employee update from table");
						}else {
							System.out.println("some problem is there...");
						}
						break;
					default :
						System.out.println("wrong choice");
				}
			}while(true);
			
		}catch(Exception ex)
		{
			System.out.println("error is : "+ex);
		}
	}
}
