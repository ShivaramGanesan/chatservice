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
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetAllCommunityMessages")
public class GetAllCommunityMessages extends HttpServlet {
	

    public GetAllCommunityMessages() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = session.getAttribute("shiva").toString().trim();
		//String reciever = session.getAttribute("ram").toString().trim();
		String tableName = request.getParameter("communityName").toString().trim();
		String validGrpName = DatabaseManager.changeValidGrpName(tableName)+"_messages";
		//System.out.println("valid = "+validGrpName);
		String messages = DatabaseManager.getAllCommMessages(tableName);
		DatabaseManager.updateCommFlagValue(tableName, username);
		//response.setContentType("text/html");
		//System.out.println("GetAllCommunityMessages = "+tableName);
		PrintWriter out = response.getWriter();
		out.println(messages);
		


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
