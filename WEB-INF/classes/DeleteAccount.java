//package chat.deleteAccount;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	
       

    public DeleteAccount() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			HttpSession session = request.getSession();
			String username = session.getAttribute("shiva").toString().trim();
			DatabaseManager.deleteAccount(username);//from users
			DatabaseManager.deleteMessages(username);//all private messages;
			DatabaseManager.removeFromAll(username);
			
			 response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}


