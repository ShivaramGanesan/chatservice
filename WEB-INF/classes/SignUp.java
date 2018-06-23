//package chat.user.signUp;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	
    public SignUp() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// JDBC driver name and database URL

      	// finally{
      	// 	try{
      	// 		if(stmt != null){
      	// 			stmt.close();
      	// 		}
      	// 	}
      	// 	catch(SQLException e1){

      	// 	}
      	// 	try {
       //      	if(conn!=null)
       //      		conn.close();
       //  	} 
       //  	catch(SQLException se) {
       //      	se.printStackTrace();
       //   	}
      	// }

		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String email = request.getParameter("email").trim();
		String username1 = DatabaseManager.parseMessage(username);
		String password1 = DatabaseManager.parseMessage(password);
		String email1 = DatabaseManager.parseMessage(email);
		//String username1 = DatabaseManager.parseMessage(username);
		//check email validity
		// if(!HelperClass.isValidEmail(email)){
		// 	response.sendRedirect("error.jsp");
		// 	return;
		// }
		//check user exists
		if(email.equals("") || username.equals("") || password.equals("")){
			response.sendRedirect("signup.jsp");
			return;
		}
		if(!DatabaseManager.isValidEmail(email)){
			PrintWriter ww1 = response.getWriter();
			ww1.println("invalid email!");
			return;
		}
		PrintWriter w = response.getWriter();

		if(email1.contains(" ")){
			w.println("oops!..email cannot have white spaces");
			return;
		}
		if(DatabaseManager.isUser(username)){
			w.println("An user with this username hass already been registered");
			return;	
		}
		if(DatabaseManager.isEmail(email)){
			w.println("An account has been registered with this email already!");
			return;
		}
		//write to table #users
		if(DatabaseManager.signUpUser(username, email, password)){
			//signup successful
			response.sendRedirect("login.jsp");
			return;
		}
		else{
			w.println(" unsupported characters in input \n\n use [a-zA-Z0-9!@#$%^&*()] only");
			return;
		}
		/*
		File f = new File("D:/database/users.txt");
		if(!f.exists()){
			f.createNewFile();
		}
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		while((line = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line, ":");
			String s;
			if(st.hasMoreTokens()){
				if((s = st.nextToken()).equals(username)){
					//error
					System.out.print(s);
					response.sendRedirect("error.jsp");
					return;
				}
				if(st.hasMoreTokens()){
					if(st.nextToken().equals(email)){
						//error
						response.sendRedirect("error.jsp");
						return;
					}
				}
			}
		}
		
		FileWriter fStream = new FileWriter(f, true);
		BufferedWriter out = new BufferedWriter(fStream);
		out.write(username+":"+email+":"+password);
		out.newLine();
//		out.write("\n");
		out.close();
		File nFile = new File("D:/database/viewsignuplog.txt");
		if(!nFile.exists()){
			nFile.createNewFile();
		}
		
		// DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		// Date dateobj = new Date();
		Calendar c = Calendar.getInstance();
		String time = c.getTime().toString();
		FileWriter fww = new FileWriter(nFile, true);
		BufferedWriter out12 = new BufferedWriter(fww);
		out12.write(username+":"+email+":"+"["+time+"]");
		out12.newLine();
//		out.write("\n");
		out12.close();
		response.sendRedirect("login.jsp");
		*/
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
