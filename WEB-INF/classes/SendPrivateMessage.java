//package chat.user.sendPrivateMessage;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SendPrivateMessage")
public class SendPrivateMessage extends HttpServlet {
	

    public SendPrivateMessage() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// String username = request.getParameter("username");
		HttpSession session = request.getSession();
        String username = session.getAttribute("shiva").toString();

		// File currentUser = new File("D:/database/currentUser.txt");
		// if(!currentUser.exists()){
		// 	currentUser.createNewFile();
		// }
		// BufferedReader b1 = new BufferedReader(new FileReader(currentUser));
		// StringTokenizer s = new StringTokenizer(b1.readLine(), "\n");
		// String currentUserName = s.nextToken();
		
		
		String name = request.getParameter("name").toString().trim();
		if(name.equals("")){
			PrintWriter w = response.getWriter();
			w.println("invalid user");
			return;
		}
		// String validSenderName = DatabaseManager.changeValidUserName(username);
		// String validRecieverName = DatabaseManager.changeValidUserName(name);
		if(name.equals(username)){
			//DatabaseManager.log("table name conflict", "sendProvateMessage.java sender="+username+" reciever="+name);
			response.sendRedirect("whomtosend.jsp");
			return;
		}
		if(!DatabaseManager.isUser(name)){
			PrintWriter w = response.getWriter();
			w.println("invalid user!");
			return;
		}
		else{
			
			//session.setAttribute("ram", name);
			request.setAttribute("username", username);
			request.setAttribute("sender", username);
			request.setAttribute("reciever", name);
			
			if(DatabaseManager.createTablePrivateMessages()){
				
					String allMessages = DatabaseManager.getAllMessages(username, name);
					request.setAttribute("allMessages", allMessages);
					getServletConfig().getServletContext().getRequestDispatcher("/privatechatroom.jsp").forward(request, response);
		
				
			}
			

			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
