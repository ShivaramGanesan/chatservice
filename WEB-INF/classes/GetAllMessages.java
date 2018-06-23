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


@WebServlet("/GetAllMessages")
public class GetAllMessages extends HttpServlet {
	

    public GetAllMessages() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String sender = session.getAttribute("shiva").toString().trim();
		//String reciever = session.getAttribute("ram").toString().trim();
		String shiva = request.getParameter("sender").toString().trim();
		String ram = request.getParameter("reciever").toString().trim();
		String validUserName = DatabaseManager.changeValidUserName(shiva);
		String validRecieverName = DatabaseManager.changeValidUserName(ram);
		//String tableName = validUserName+"_"+validRecieverName+"_messages";
		String messages = DatabaseManager.getAllMessages(shiva, ram);
		DatabaseManager.updateFlagValue(shiva, ram);
		//String myMessages = DatabaseManager.getMyMessages(sender, reciever);
		//response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(messages);
		


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
