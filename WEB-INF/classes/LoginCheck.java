//package chat.user.login;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	
    public LoginCheck() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		
		// if(username.equals("users")){
		// 	PrintWriter w = response.getWriter();
		// 	w.println("restricted username");
		// }
		// if(username.equals("admin") && password.equals("admin")){
		// 	response.sendRedirect("adminlogin.jsp");
		// 	return;
		// }

		if(username.equals("") || username == null){
			response.sendRedirect("login.jsp");	
			return;
		}
		//check valid user and password
		if(DatabaseManager.isUser(username)){
			String passFromDB = DatabaseManager.getPassword(username);

			if(password.equals(passFromDB)){
				HttpSession session = request.getSession(true);
				session.setAttribute("shiva", username);
				response.sendRedirect("homepage.jsp");
				return;
			}
			else{
				PrintWriter w = response.getWriter();
				w.println("incorrect credentials");
			}
		}
		else{
			PrintWriter w = response.getWriter();
			w.println("User not registered!");	
		}
		
		
		
			
//			response.sendRedirect("homepage.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
