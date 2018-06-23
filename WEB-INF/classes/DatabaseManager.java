package chat.database;

import java.sql.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

public class DatabaseManager{


		public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		public static String DB_URL = "jdbc:mysql://localhost/chatservice";
		//  Database credentials
      	public static String USER = "root";
      	public static String PASS = "root";

      	
      	

      	public static boolean isUser(String username){
      		//username should not be changed tp validUserName;
      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "isUser()" );
	      		String readUsersTable = "select username from users";
	      		ResultSet rs = stmt.executeQuery(readUsersTable);
	      		while(rs.next()){
	      			String name = rs.getString("username");
	      			if(name.equals(username)){
	      				rs.close();
	      				stmt.close();
	      				conn.close();
	      				return true;
	      				//user exists;
	      			}
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return false;
	      		//user does not exist;
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;
      	}


      	public static String parseMessage(String message){
      		StringBuffer parsedBuffer=new StringBuffer();
      		for(int i=0;i<message.length();i++){
      			parsedBuffer.append(message.charAt(i));
      			if(message.charAt(i) == '\''){
      				parsedBuffer.append("\'");
      			}
      			else if(message.charAt(i) == '\\'){
      				parsedBuffer.append("\\");
      			}
      		}
      		return parsedBuffer.toString();

      	}

      	public static List<String> unreadMessagesSender(String username){
      		Connection conn = null;
   			Statement stmt = null;
   			List<String> list = new ArrayList<String>();
   			String username1 = DatabaseManager.parseMessage(username);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		
	      			String unreadMessages = "select distinct sender from privateMessages where reciever='"+username1+"' and flag=1";
	      			ResultSet rs = stmt.executeQuery(unreadMessages);
	      			while(rs.next()){
	      				String line = rs.getString("sender");
	      				list.add(line);
	      			}
	       		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return list;
	      		}

	      
      		
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return new ArrayList<String>();
      		
      	} 

      	public static List<String> unreadCommunityMessages(String username){
      		Connection conn = null;
   			Statement stmt = null;
   			List<String> list = new ArrayList<String>();
   			String username1 = DatabaseManager.parseMessage(username);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();

	      		DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, "communityUnreadMessages", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table communityUnreadMessages(communityName varchar(255), username varchar(255), flag int)";
					stmt.executeUpdate(createTableUsers);	      			
	      		}
	      // 		
	      			String unreadMessages = "select distinct communityName from communityUnreadMessages where username='"+username1+"' and flag=1";
	      			ResultSet rs = stmt.executeQuery(unreadMessages);
	      			while(rs.next()){
	      				String line = rs.getString("communityName");
	      				list.add(line);
	      			}
	       		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return list;
	      		}

	      
      		
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return new ArrayList<String>();
      		
      	} 

     //  	public static void addCommunityToUserTable(String communityName, String username){
     //  		Connection conn = null;
   		// 	Statement stmt = null;
   		// 	Statement stmt1 = null;
   		// 	String validUserName = DatabaseManager.changeValidUserName(username);
   		// 	try{
	    //   		//Register JDBC driver
	    //   		Class.forName("com.mysql.jdbc.Driver");
	      		
	    //   		//open connection
	    //   		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    //   		stmt = conn.createStatement();
	    //   		stmt1 = conn.createStatement();
	    //   		DatabaseMetaData dbm = conn.getMetaData();
	    //   		ResultSet r = dbm.getTables(null, null, validUserName, null);
	    //   		if(!r.next()){
	    //   			String createTableUsers = "create table "+validUserName+"(grpName varchar(255))";
					// stmt.executeUpdate(createTableUsers);	      			
	    //   		}
	    //   		String insertTableName = "insert into "+validUserName+" values('"+communityName+"')";
	    //   		stmt1.executeUpdate(insertTableName);
	    //   		r.close();
	    //   		stmt.close();
	    //   		stmt1.close();
	    //   		conn.close();

     //  		}
     //  		catch(SQLException e){
     //  			e.printStackTrace();
     //  		}
     //  		catch(Exception e){
     //  			e.printStackTrace();
     //  		}
      		
     //  	}
      	public static List<String> myCommunities(String me){
      		Connection conn = null;
   			Statement stmt = null;
   			String me1 = DatabaseManager.parseMessage(me);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "allCommunities()" );

    			
    			//if(!isUser(me)){
    			//	return new ArrayList<String>();
    			//}
    			DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, "communityMembers", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table communityMembers(communityName varchar(255), username varchar(255))";
					stmt.executeUpdate(createTableUsers);	      			
	      		}
	      		List<String> list = new ArrayList<String>();
	      		String allComm = "select communityName from communityMembers where username='"+me1+"'";
	      		ResultSet rs = stmt.executeQuery(allComm);
	      		while(rs.next()){
	      			list.add(rs.getString("communityName"));
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return list;
	      	
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return new ArrayList<String>();
      	}

      // 	public static void removeFromMe(String grpName, String username){
      // 		Connection conn = null;
   			// Statement stmt = null;
   			// String validUserName = DatabaseManager.changeValidUserName(username);
   			// try{
	     //  		//Register JDBC driver
	     //  		Class.forName("com.mysql.jdbc.Driver");
	     //  		//open connection
	     //  		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	     //  		stmt = conn.createStatement();
	     //  // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    		// 	// DatabaseManager.log("create connection called from " + st[2], "allCommunities()" );
	     //  		String delete = "delete from "+validUserName+" where grpName='"+grpName+"'";
	     //  		stmt.executeUpdate(delete);
	     //  		// /rs.close();
	     //  		stmt.close();
	     //  		conn.close();
	      		
	      	
      // 		}
      // 		catch(SQLException e){
      // 			e.printStackTrace();
      // 		}
      // 		catch(Exception e){
      // 			e.printStackTrace();
      // 		}
      		
      		
      // 	}

      	public static List<String> allCommunities(){
      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "allCommunities()" );
	      		List<String> list = new ArrayList<String>();
	      		String allComm = "select communityName from communities";
	      		ResultSet rs = stmt.executeQuery(allComm);
	      		while(rs.next()){
	      			list.add(rs.getString("communityName"));
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return list;
	      	
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return new ArrayList<String>();
      		
      	}
      	public static boolean isValidEmail(String email) {
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(email);
		if(m.find())return true;
		else return false;
		
	}
      	public static String getPassword(String username){
      		Connection conn = null;
   			Statement stmt = null;
   			String username1 = DatabaseManager.parseMessage(username);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "getPassword()" );
	      		DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, "users", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table users(email varchar(255), username varchar(255), password varchar(255))";
					stmt.executeUpdate(createTableUsers);	      			
	      		}
	      		String sql = "select password from users where username='"+username1+"'";
	      		ResultSet rs = stmt.executeQuery(sql);
	      		if(rs.next()){
	      			return rs.getString("password");
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return null;
      	}

      	public static boolean createUsersTable(){
      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      
	      			String createTableUsers = "create table users(email varchar(255), username varchar(255), password varchar(255))";
					stmt.executeUpdate(createTableUsers);	      			
					String createCommunitiesTable = "create table communities(communityName varchar(255), dateOfCreation varchar(255), members varchar(255), admin varchar(255))";
					stmt.executeUpdate(createCommunitiesTable);
	      		
	      		stmt.close();
	      		conn.close();
	      		return true;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;
      	}



      	public static boolean createMessageTable(){
      		Connection conn = null;
      		Statement stmt = null;
      		try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "createTable()" );
	      		DatabaseMetaData dbm = conn.getMetaData();

	      		ResultSet r = dbm.getTables(null, null, "allMessages", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table allMessages (sender varchar(255), reciever varchar(255), time varchar(30), message varchar(255))";
					stmt.executeUpdate(createTableUsers);	
					
	      		}
	      		r.close();
	      		stmt.close();
	      		conn.close();
	      		return true;
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;
      	}
      	public static boolean writeToMessages(String sender, String reciever, String message, String time){
      		createMessageTable();
      			Connection conn = null;
   			Statement stmt = null;
   			String sender1 = DatabaseManager.parseMessage(sender);
   			String reciever1 = DatabaseManager.parseMessage(reciever);

   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "sendMessage()" );
	      		String writeToDB = "insert into "+"allMessages"+" values('"+sender1+"', '"+reciever1+"', '"+time+"', '"+message+"')" ;
	      		stmt.executeUpdate(writeToDB);
	      		// rs.close();
	      		stmt.close();
	      		conn.close();
	      		return true;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;     
      	}
      	public static boolean createTablePrivateMessages(){
      		Connection conn = null;
      		Statement stmt = null;
      		try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "createTable()" );
	      		DatabaseMetaData dbm = conn.getMetaData();

	      		ResultSet r = dbm.getTables(null, null, "privateMessages", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table privateMessages (sender varchar(255), reciever varchar(255), time varchar(30), message varchar(255), flag int)";
					stmt.executeUpdate(createTableUsers);	
					
	      		}
	      		r.close();
	      		stmt.close();
	      		conn.close();
	      		return true;
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;
      	}

      // 	public static void log(String name, String message){
      // 		Connection conn = null;
   			// Statement stmt = null;
   			// StringBuffer sb=null;
   			// try{
	     //  		//Register JDBC driver
	     //  		Class.forName("com.mysql.jdbc.Driver");
	     //  		//open connection
	     //  		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	     //  		stmt = conn.createStatement();
	      		
	     //  		String sql = "insert into log values('"+name+"', '"+message+"')";
	     //  		stmt.executeUpdate(sql);

	     //  		stmt.close();
	     //  		conn.close();
	      		
      // 		}
      // 		catch(SQLException e){
      // 			e.printStackTrace();
      // 		}
      // 		catch(Exception e){
      // 			e.printStackTrace();
      // 		}
      		
      // 	}

      	public static String getAllMessages(String sender, String reciever){
      		//table name is valid grp name
      		
      		Connection conn = null;
   			Statement stmt = null;
   			StringBuffer sb=null;
   			String sender1 = DatabaseManager.parseMessage(sender);
   			String reciever1 = DatabaseManager.parseMessage(reciever);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();

	      		DatabaseMetaData dbm = conn.getMetaData();

	      		ResultSet r = dbm.getTables(null, null, "privateMessages", null);
	      		if(!r.next()){
	      			return new String();	
					
	      		}
	      		r.close();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "getAllMessages()" );
	      		sb = new StringBuffer();
	      		String line="";
	      		String sql = "select * from privateMessages where (sender='"+sender1+"' and reciever='"+reciever1+"') or (sender='"+reciever1+"' and reciever='"+sender1+"')";
      			ResultSet rs = stmt.executeQuery(sql);
      			while(rs.next()){
      				line=rs.getString("sender")+":"+rs.getString("message")+"\t["+rs.getString("time")+"]";
      				sb.append(line);
      				sb.append(System.lineSeparator());
      			}
      			rs.close();
	      		stmt.close();
	      		conn.close();
	      		return sb.toString();
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return sb.toString();
      		 
      	}


		public static String getAllCommMessages(String communityName){
      		//table name is valid grp name
      		
      		Connection conn = null;
   			Statement stmt = null;
   			StringBuffer sb=null;
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();

	      		DatabaseMetaData dbm = conn.getMetaData();

	      		ResultSet r = dbm.getTables(null, null, "communityMessages", null);
	      		if(!r.next()){
	      			return new String();	
					
	      		}
	      		r.close();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "getAllMessages()" );
	      		sb = new StringBuffer();
	      		String line="";
	      		String sql = "select * from communityMessages where communityName='"+communityName1+"'";
      			ResultSet rs = stmt.executeQuery(sql);
      			while(rs.next()){
      				line=rs.getString("messages");
      				sb.append(line);
      				sb.append(System.lineSeparator());
      			}
      			rs.close();
	      		stmt.close();
	      		conn.close();
	      		return sb.toString();
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return sb.toString();
      		 
      	}

      	public static String getMyMessages(String sender, String reciever){
      		//table name is valid grp name
      		
      		Connection conn = null;
   			Statement stmt = null;
   			StringBuffer sb=null;
   			String sender1 = DatabaseManager.parseMessage(sender);
   			String reciever1 = DatabaseManager.parseMessage(reciever);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();

	      		DatabaseMetaData dbm = conn.getMetaData();

	      		ResultSet r = dbm.getTables(null, null, "allMessages", null);
	      		if(!r.next()){
	      			return new String();	
					
	      		}
	      		r.close();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "getAllMessages()" );
	      		sb = new StringBuffer();
	      		String line="";
	      		String sql = "select * from allMessages where (sender='"+sender1+"' and reciever='"+reciever1+"') or (sender='"+reciever1+"' and reciever='"+sender1+"')";
      			ResultSet rs = stmt.executeQuery(sql);
      			while(rs.next()){
      				line=rs.getString("sender")+":"+rs.getString("message")+"\t["+rs.getString("time")+"]";
      				sb.append(line);
      				sb.append(System.lineSeparator());
      			}
      			rs.close();
	      		stmt.close();
	      		conn.close();
	      		return sb.toString();
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return sb.toString();
      		 
      	}
      	public static int sendMessage(String sender, String reciever, String time, String message){
			Connection conn = null;
   			Statement stmt = null;
   			String sender1 = DatabaseManager.parseMessage(sender);
   			String reciever1 = DatabaseManager.parseMessage(reciever );
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "sendMessage()" );
	      		String writeToDB = "insert into privateMessages values('"+sender1+"', '"+reciever1+"', '"+time+"', '"+message+"', 1)" ;
	      		stmt.executeUpdate(writeToDB);
	      		//writeToMessages(sender, reciever, message, time);
	      		// rs.close();
	      		stmt.close();
	      		conn.close();
	      		return 1;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return 0;      		
      	}


      	public static void updateFlagValue(String sender, String reciever){
			Connection conn = null;
   			Statement stmt = null;
   			String sender1 = DatabaseManager.parseMessage(sender);
   			String reciever1 = DatabaseManager.parseMessage(reciever);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "sendMessage()" );
	      		String updateToDB = "update privateMessages set flag=0 where reciever='"+sender1+"' and sender='"+reciever1+"'" ;
	      		stmt.executeUpdate(updateToDB);
	      		//writeToMessages(sender, reciever, message, time);
	      		// rs.close();
	      		stmt.close();
	      		conn.close();
	      		
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
     		
      	}
      	public static boolean signUpUser(String username, String email, String password){
      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "signUpUser()" );
	      		DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, "users", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table users(email varchar(255), username varchar(255), password varchar(255))";
					stmt.executeUpdate(createTableUsers);	      			
	      		}
	      		String writeToDB = "insert into users values('"+email+"', '"+username+"', '"+password+"')" ;
	      		stmt.executeUpdate(writeToDB);
	      		r.close();
	      		stmt.close();
	      		conn.close();
	      		return true;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return false;
      	}

      	public static boolean isCommunity(String communityName){
      		

      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "isCommunity()" );
	      		DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, "communities", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table communities(communityName varchar(255), dateOfCreation varchar(255), members varchar(255), admin varchar(255))";
					stmt.executeUpdate(createTableUsers);	      			
					return false;
	      		}
	      		String sql = "select communityName from communities";
	      		ResultSet rs = stmt.executeQuery(sql);
	      		while(rs.next()){
	      			if(rs.getString("communityName").equals(communityName)){
	      				return true;
	      			}
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
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
      	public static boolean createCommunityEntry(String communityName, String username){
      		Connection conn = null;
   			Statement stmt = null;
   			Statement stmt3 = null;
   			Statement stmt2 = null;
   			Statement stm = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		stmt2 = conn.createStatement();
	      		stmt3 = conn.createStatement();
	      		stm = conn.createStatement();
	      		//String members="";
	      		//StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			//DatabaseManager.log("create connection called from " + st[2], "createCommunityEntry()" );
	      		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   			LocalDateTime now = LocalDateTime.now();
   				String time=dtf.format(now).toString();
      			String entry = "insert into communities values('"+communityName+"', '"+time+"', '"+username+"', 'admin')";
      			DatabaseMetaData dbm = conn.getMetaData();
      			ResultSet r = dbm.getTables(null, null, "communityMembers", null);
	      		if(!r.next()){
	      			String createCommunityMembers = "create table communityMembers(communityName varchar(255), username varchar(255))";
					stmt3.executeUpdate(createCommunityMembers);	      			
	      		}
	      		String parsedCommunityName = DatabaseManager.parseMessage(communityName);
	      		String username1 = DatabaseManager.parseMessage(username);
      			String insertInMembers = "insert into communityMembers values('"+communityName+"', '"+username+"')";
      			stmt.executeUpdate(entry);
      			stmt2.executeUpdate(insertInMembers);
      			

      			DatabaseMetaData dbmData = conn.getMetaData();
      			ResultSet rSet = dbmData.getTables(null, null, "communityUnreadMessages", null);
      			if(!rSet.next()){
      				String createCommunityMembers = "create table communityUnreadMessages(communityName varchar(255), username varchar(255), flag int)";
					stmt.executeUpdate(createCommunityMembers);	
      			}
      				String in = "insert into communityUnreadMessages values('"+communityName+"', '"+username+"', 0)";
      				stm.executeUpdate(in);

      			
      			
      			stmt.close();
      			stm.close();
      			stmt2.close();
      			stmt3.close();
      			conn.close();

      			return true;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}

      		return false;

      	}
      	public static String changeValidGrpName(String communityName){
      		String grpName = communityName.replaceAll(" ", "_");
      		return grpName;
      	}

      	public static String changeValidUserName(String username){
      		String valid = username.replaceAll(" ", "_");
      		return valid;
      	}

      	public static boolean addUserToCommunity(String communityName, String username){
      		
      		String grpName = changeValidGrpName(communityName);

      		Connection conn = null;
   			Statement stmt = null;
   			Statement stm=null;
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			String username1 = DatabaseManager.parseMessage(username);
   			//Statement stmt2 = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		stm = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "addUserToCommunity()" );
    			String updateValue = "update communities set members=concat(members, ':"+username1+"') where communityName='"+communityName1+"'";
									      		// String tableName = grpName+"_members";
									      		// System.out.println(tableName);
								    //   		DatabaseMetaData dbm = conn.getMetaData();
								    //   		ResultSet r = dbm.getTables(null, null, tableName, null);
								    //   		if(!r.next()){
								    //   			String createTableUsers = "create table "+tableName+"(username varchar(255))";
												// stmt.executeUpdate(createTableUsers);	      			
												
								    //   		}
	      							//			String insertUser = "insert into "+tableName+" values('"+username+"')";
	      		stmt.executeUpdate(updateValue);

	      		//communityMessagesUnread
	      		DatabaseMetaData dbmdata = conn.getMetaData();
      			ResultSet rset = dbmdata.getTables(null, null, "communityUnreadMessages", null);
	      		if(!rset.next()){
	      			String createCommunityMembers = "create table communityUnreadMessages(communityName varchar(255), username varchar(255), flag int)";
					stmt.executeUpdate(createCommunityMembers);	      			
	      		}
	      		String in = "insert into communityUnreadMessages values('"+communityName+"', '"+username+"', 1)";
	      		stm.executeUpdate(in);
	      		//r.close();
	      		stmt.close();
	      		stm.close();
	      		//stmt2.close();
	      		conn.close();
	      		return true;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		
      		return false;
      	}

      	public static void updateCommFlagValue(String communityName, String username){
      		Connection conn = null;
   			Statement stmt = null;
   			
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		
	      		String updateValue = "update communityUnreadMessages set flag=0 where communityName='"+communityName+"' and username='"+username+"'";
	      		stmt.executeUpdate(updateValue);
	      		

	      		stmt.close();
	      		
	      		conn.close();
	      		
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		
      	}

      	public static List<String> newCommMessages(String username){
      		Connection conn = null;
   			Statement stmt = null;
   			
   			
   			List<String> list = new ArrayList<String>();
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		
	      		String newMessages = "select communityName from communityUnreadMessages where username='"+username+"' and flag=1";
	      		ResultSet rs = stmt.executeQuery(newMessages);
	      		while(rs.next()){
	      			list.add(rs.getString("communityName"));
	      		}

	      		stmt.close();
	      		
	      		conn.close();
	      		return list;
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return new ArrayList<String>();
      	}

      	public static void removeFrom(String grpName, String username){
      		//grpname has to be changed to valid grp anme
      		String grpName1 = DatabaseManager.parseMessage(grpName);
      		Connection conn = null;
   			Statement stmt = null;
   			Statement stmt1 = null;
   			Statement stmt2 = null;
   			Statement stmt3 = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		stmt1 = conn.createStatement();
	      		stmt2 = conn.createStatement();
	      		stmt3 = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "removeFrom()" );
	      		// String tableName = validGrpName+"_members";
	      		// String sql = "delete from "+tableName+" where username='"+username+"'";
	      		// stmt.executeUpdate(sql);
	      		// if(isEmptyTable(tableName)){
	      		// 	//delete table
	      		// 	String deleteTable = "drop table "+tableName;
	      		//  	stmt1.executeUpdate(deleteTable);

	     //  		 	DatabaseMetaData dbm = conn.getMetaData();
	     //  			ResultSet r = dbm.getTables(null, null, validGrpName+"_messages", null);
	     //  			if(r.next()){
	     //  				//String createTableUsers = "create table "+tableName+"(username varchar(30))";
						// //stmt.executeUpdate(createTableUsers);	      			
						// String deleteFromComm ="drop table "+validGrpName+"_messages";
	     //  		 		stmt2.executeUpdate(deleteFromComm);
	     //  			}
	      		 	
	      		 	//also delete from communites table
	      		 	String delFromComm = "delete from communities where communityName='"+grpName1+"'";
	      		 	stmt3.executeUpdate(delFromComm);
	      		 	
	      		
	      		// rs.close();
	      		//stmt1.close();
	      		stmt3.close();
	      		stmt2.close();
	      		stmt.close();
	      		conn.close();
	      		
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		
      		
      	}
      	public static void deleteCommunity(String communityName){
      		Connection conn = null;
   			Statement stmt = null;
   			Statement stmt2 = null;
   			int count=0;
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		stmt2 = conn.createStatement();
	      		//List<String> allComms = DatabaseManager.allCommunities();
	      		String deleteFromCommunities = "delete from communities where communityName='"+communityName+"'";
	      		String deletecommMessages = "delete from communityMessages where communityName='"+communityName+"'";
	      		stmt.executeUpdate(deletecommMessages);
	      		stmt.executeUpdate(deleteFromCommunities);
                //rs.close();
	      		stmt.close();
	      		stmt2.close();
	      		conn.close();
	      		
	      		
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		//return false;
      	}
      	public static boolean isCommunityEmpty(String communityName){
      		Connection conn = null;
   			Statement stmt = null;
   			int count=0;
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		List<String> allComms = DatabaseManager.allCommunities();
	      		//for(String comm : allComms){
	      			String commMembers = "select username from communityMembers where communityName='"+communityName1+"'";
	      			ResultSet rs = stmt.executeQuery(commMembers);
	      			if(!rs.next()){
	      				return true;
	      			}

	      		//}
	      		
                rs.close();
	      		stmt.close();
	      		conn.close();
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
      // 	public static boolean isEmptyTable(String tableName){
      // 		Connection conn = null;
   			// Statement stmt = null;
   			// int count=0;
   			// try{
	     //  		//Register JDBC driver
	     //  		Class.forName("com.mysql.jdbc.Driver");
	     //  		//open connection
	     //  		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	     //  		stmt = conn.createStatement();
	      		
	     //  		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM "+tableName);
      //           while (rs.next()){
      //               count = rs.getInt(1);
      //           }
      //           if(count == 0){

	     //  		 return true;
      //           }
      //           rs.close();
	     //  		stmt.close();
	     //  		conn.close();
	     //  		return false;
	      		
      // 		}
      // 		catch(SQLException e){
      // 			e.printStackTrace();
      // 		}
      // 		catch(Exception e){
      // 			e.printStackTrace();
      // 		}
      // 		return false;
      // 	}

      	public static boolean isMember(String communityName, String username){
      		//community name has to be changed to valid grp name;
      		Connection conn = null;
   			Statement stmt = null;
   			String username1 = DatabaseManager.parseMessage(username);
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "isMember()" );
    	// 		String validGrpName = DatabaseManager.changeValidGrpName(communityName);
	    //   		String tableName = validGrpName+"_members";
	    //   		DatabaseMetaData dbm = conn.getMetaData();
	    //   		ResultSet r = dbm.getTables(null, null, tableName, null);
	    //   		if(!r.next()){
	    //   			String createTableUsers = "create table "+tableName+"(username varchar(255))";
					// stmt.executeUpdate(createTableUsers);	      			
					
	    //   		}
	      		String usersInCommunity = "select members from communities where communityName='"+communityName1+"'";
	      		String getUsers = "select username from communityMembers where communityName='"+communityName1+"'";
	      		ResultSet rs = stmt.executeQuery(getUsers);//used to be getUsers here
	      		//used to be while
	      		while(rs.next()){
	      			// if(rs.getString("username").equals(username)){
	      				String checkUser = rs.getString("username");
	      				if(checkUser.equals(username1)){
	      					return true;
	      				}
	      				
	      				
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      	}
	      		
	      			
	      		
      		
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		
      		return false;
      	}


      	public static void addPerson(String username, String communityName){
      		Connection conn = null;
   			Statement stmt = null;
   			Statement stmt3 = null;
   			Statement stm = null;
   			String username1 = DatabaseManager.parseMessage(username);
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			//String validGrpName = DatabaseManager.changeValidGrpName(grpName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		stmt3 = conn.createStatement();
	      		stm = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "addPerson()" );
	      		DatabaseMetaData dbm = conn.getMetaData();
      			ResultSet r = dbm.getTables(null, null, "communityMembers", null);
	      		if(!r.next()){
	      			String createCommunityMembers = "create table communityMembers(communityName varchar(255), username varchar(255))";
					stmt3.executeUpdate(createCommunityMembers);	      			
	      		}
      			String insertInMembers = "insert into communityMembers values('"+communityName1+"', '"+username1+"')";
	      		stmt.executeUpdate(insertInMembers);//used to be insertUser;

	      		//addCommunityToUserTable(tableName, username);

	      		DatabaseMetaData dbmdata = conn.getMetaData();
      			ResultSet rset = dbmdata.getTables(null, null, "communityUnreadMessages", null);
	      		if(!rset.next()){
	      			String createCommunityMembers = "create table communityUnreadMessages(communityName varchar(255), username varchar(255), flag int)";
					stmt3.executeUpdate(createCommunityMembers);	      			
	      		}
	      		String in = "insert into communityUnreadMessages values('"+communityName+"', '"+username+"', 1)";
	      		stm.executeUpdate(in);
	      		// rs.close();
	      		r.close();
	      		stm.close();
	      		stmt3.close();
	      		stmt.close();
	      		conn.close();
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}

      		
      	}

      	public static void leaveCommunity(String communityName, String user){
      		Connection conn = null;
   			Statement stmt = null;
   			String user1 = DatabaseManager.parseMessage(user);
   			String communityName1 = DatabaseManager.parseMessage(communityName);
   			//String validGrpName = DatabaseManager.changeValidGrpName(grpName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "addPerson()" );
	      		
      			String leaveComm = "delete from communityMembers where username='"+user1+"' and communityName='"+communityName1+"'";
	      		stmt.executeUpdate(leaveComm);//used to be insertUser;
	      		if(DatabaseManager.isCommunityEmpty(communityName)){
	      			deleteCommunity(communityName);
	      		}
	      		//addCommunityToUserTable(tableName, username);
	      		// rs.close();
	      		//r.close();
	      		//stmt3.close();
	      		stmt.close();
	      		conn.close();
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      	}
      	public static void sendToGrp(String sender, String message, String grpName, String time){
      		Connection conn = null;
   			Statement stmt = null;
   			
   			Statement stmt4 = null;
   			Statement action1Statement = null;
   			Statement action2Statement = null;
   			String grpName1 = DatabaseManager.parseMessage(grpName);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      		action2Statement = conn.createStatement();
	      		action1Statement = conn.createStatement();
	      		stmt4 = conn.createStatement();
	      		

	      		DatabaseMetaData dbm = conn.getMetaData();
	      		ResultSet r = dbm.getTables(null, null, "communityUnreadMessages", null);
	      		if(!r.next()){
	      			String createTableUsers = "create table communityUnreadMessages(communityName varchar(255), username varchar(255), flag int)";
					stmt4.executeUpdate(createTableUsers);	      			
	      		}
	       	
	      		DatabaseMetaData dbm11 = conn.getMetaData();
	      		ResultSet r12 = dbm11.getTables(null, null, "communityMessages", null);
	      		if(!r12.next()){
	      			String createTableUsers = "create table communityMessages (communityName varchar(255), messages varchar(255))";
					stmt.executeUpdate(createTableUsers);	      			
					
	      		}
	      		
	      		String messageEntry = sender+":"+message+"\t["+time+"]";
	      		String send = "insert into communityMessages values('"+grpName1+"', '"+messageEntry+"')";
	      		stmt.executeUpdate(send);

	      		String action1 = "update communityUnreadMessages set flag=1 where communityName='"+grpName+"'";
	      		action1Statement.executeUpdate(action1);
	      		String action2 = "update communityUnreadMessages set flag=0 where communityName='"+grpName+"' and username='"+sender+"'";
	      		action2Statement.executeUpdate(action2);

	      		//epoch time


	      		r.close();
	      		action1Statement.close();
	      		action2Statement.close();
	      		stmt4.close();
	      		stmt.close();
	      		
	      		conn.close();

	      			
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}

      		
      	}

      	

      	public static void removeFromAll(String username){
      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "removeFromAll()" );
	      		List<String> myComms = DatabaseManager.myCommunities(username);
	      		
	      		for(int i=0;i<myComms.size();i++){
	      			String myComm = myComms.get(i);
	      			if(isMember(myComm, username)){
	      				leaveCommunity(myComm, username);
	      			}
	      		}
	      		//getgrps.close();
	      		stmt.close();
	      		conn.close();

	      			
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}

      	}
      	public static void deleteMessages(String username){
      		Connection conn = null;
   			Statement stmt = null;
   			String username1 = DatabaseManager.parseMessage(username);
   			//String reciever1 = DatabaseManager.parseMessage(reciever);
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "deleteMessages()" );
	      		String delMessages = "delete from privateMessages where sender='"+username1+"' or reciever='"+username1+"'";
	      		stmt.executeUpdate(delMessages);
	      		//rs.close();
	      		stmt.close();
	      		conn.close();
	      		

	      			
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      	}

      	public static List<String> getAllUsers(){
      		Connection conn = null;
   			Statement stmt = null;
   			List<String> list=null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "getAllUsers()" );
	      		list = new ArrayList<String>();
	      		String getUsers = "select username from users";
	      		ResultSet rs = stmt.executeQuery(getUsers);
	      		while(rs.next()){
	      			list.add(rs.getString("username"));
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return list;

	      			
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		return list;
      	}
      	public static void deleteAccount(String username){
      			Connection conn = null;
   			Statement stmt = null;
   			String username1 = DatabaseManager.parseMessage(username);
   			
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "deleteAccount()" );
	      		String delete = "delete from users where username='"+username1+"'";
	      		stmt.executeUpdate(delete);
	      		
	      		
	      		// rs.close();
	      		stmt.close();
	      		conn.close();
	      			
      		}
      		catch(SQLException e){
      			e.printStackTrace();
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      	}

      	public static boolean isEmail(String email){
      		Connection conn = null;
   			Statement stmt = null;
   			try{
	      		//Register JDBC driver
	      		Class.forName("com.mysql.jdbc.Driver");
	      		//open connection
	      		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      		stmt = conn.createStatement();
	      // 		StackTraceElement[] st = Thread.currentThread().getStackTrace();
    			// DatabaseManager.log("create connection called from " + st[2], "isUser()" );
	      		String readUsersTable = "select email from users";
	      		ResultSet rs = stmt.executeQuery(readUsersTable);
	      		while(rs.next()){
	      			String name = rs.getString("email");
	      			if(name.equals(email)){
	      				rs.close();
	      				stmt.close();
	      				conn.close();
	      				return true;
	      				//user exists;
	      			}
	      		}
	      		rs.close();
	      		stmt.close();
	      		conn.close();
	      		return false;
	      		//user does not exist;
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

// Statement stmt = conn.createStatement();
//       		String DROP_TABLE_IF_EXISTS = "drop table if exists users";
//       		ResultSet createTableUsers = stmt.executeQuery(DROP_TABLE_IF_EXISTS);
      		
//       		String sql;
//       		sql = "SELECT * FROM test";
//       		ResultSet rs = stmt.executeQuery(sql);

//       		//parse result set
//       		while(rs.next()){
//       			//retreiving by col name
//       			int id = rs.getInt("id");
//       			String nameFromDB = rs.getString("name");
//       			response.getWriter().append("id: "+id+"<br>");
//       			response.getWriter().append("name: "+nameFromDB+"<br>");
//       		}
//       		rs.close();
//       		stmt.close();
//       		conn.close();