import java.sql.*;

public class TestDatabase{
	public static void main(String[] args){
		 System.out.println(function("family", "shiva")+"");
      		
	}

	public static boolean function(String grpName, String name){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		 String DB_URL = "jdbc:mysql://localhost/chatservice";
		//  Database credentials
      	 String USER = "root";
      	 String PASS = "root";
		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();

	      		String tableName = grpName+"_members";
	      		DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, tableName, null);
	      		if(!r.next()){
	      			String createTableUsers = "create table "+tableName+"(username varchar(30))";
					stmt.executeUpdate(createTableUsers);	      			
					
	      		}
	      		String getUsers = "select username from "+tableName;
	      		ResultSet rs = stmt.executeQuery(getUsers);
	      		while(rs.next()){
	      			//System.out.println(rs.getString("username"));
	      			if(rs.getString("username").equals("shiva")){
	      				return true;
	      				
	      				//is a member
	      			}
	      		}
	      			
	      		return false;
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;
      		
	}
}