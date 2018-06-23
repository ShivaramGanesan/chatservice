//package chat.community.leaveCommunity;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LeaveCommunity")
public class LeaveCommunity extends HttpServlet {
	
    public LeaveCommunity() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
        	String username = session.getAttribute("shiva").toString().trim();
			String grpName = request.getParameter("grpName").trim();

			if(grpName.equals("") || grpName==null){
				PrintWriter w = response.getWriter();
				w.println("invalid inputs..");
				return;
			}

			if(DatabaseManager.isMember(grpName, username)){
				// DatabaseManager.removeFrom(grpName, username);//comm_members
				// DatabaseManager.removeFromMe(grpName,username);//delet from username table;
				DatabaseManager.leaveCommunity(grpName, username);
				response.sendRedirect("ViewPrivateMessages?param1=5");
			}
			else{
				PrintWriter w = response.getWriter();
				w.println("invalid inputs..");	
				return;
			}
					
				
			
			
			 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
