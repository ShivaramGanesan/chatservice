//package chat.user.privateChatRoom;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PrivateChatRoom")
public class PrivateChatRoom extends HttpServlet {
	

    public PrivateChatRoom() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter writer = response.gettWriter();
		response.setContentType("text/html");
		PrintWriter ou = response.getWriter();

		HttpSession session = request.getSession();
        String username = session.getAttribute("shiva").toString().trim();
		String currentUserName = request.getParameter("sender").trim();
		String name = request.getParameter("reciever").trim();
		String message = request.getParameter("message").trim();
		//String ram = session.getAttribute("ram").toString().trim();
		List<String> unreadMessageList = DatabaseManager.unreadMessagesSender(username);
		StringBuffer sb1 = new StringBuffer();
		for(int i=0;i<unreadMessageList.size();i++){
						sb1.append(unreadMessageList.get(i)+":");
					}
					String unreadMessagesSender = sb1.toString();

		String validSenderName = DatabaseManager.changeValidUserName(username);
		String validRecieverUserName = DatabaseManager.changeValidUserName(name);
		DatabaseManager.updateFlagValue(username, name);
		if(username.equals(name)){
			//DatabaseManager.log("names are equal", "snder="+username+" reciever="+ram);
		}
		if(!message.equals("")){
			//String fileName1, fileName2;
					String parsedMessage = DatabaseManager.parseMessage(message);
			
					Calendar c = Calendar.getInstance();
					String time = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
					
					if(DatabaseManager.isUser(name) && DatabaseManager.isUser(username)){	
						if(DatabaseManager.sendMessage(username, name, time, parsedMessage) == 1 ){
								//DatabaseManager.writeToMessages(username, ram, message, time);
								request.setAttribute("username", username);
							request.setAttribute("sender", currentUserName);
							request.setAttribute("reciever", name);
							String allMessages = DatabaseManager.getAllMessages(username, name);



							ou.println(allMessages);
							request.setAttribute("allMessages", allMessages);
							request.setAttribute("newSenders", unreadMessagesSender);
							getServletConfig().getServletContext().getRequestDispatcher("/privatechatroom.jsp").forward(request, response);
						}
					}
					else{
						response.sendRedirect("ViewPrivateMessages?param1=1");
					}
			
					
		}
		else{	
				//response.sendRedirect("privatechatRoom.jsp");
					String tableName1="";
			
					tableName1 = validSenderName+"_"+validRecieverUserName+"_messages";
					
					
			
					//String allMessages = DatabaseManager.getAllMessages(tableName1);
					//request.setAttribute("messages", allMessages);
					if(DatabaseManager.isUser(name) && DatabaseManager.isUser(username)){
						if(!username.equals(name)){
							String allMessages = DatabaseManager.getAllMessages(username, name);
							request.setAttribute("allMessages", allMessages);
							request.setAttribute("username", username);
							request.setAttribute("sender", currentUserName);
							request.setAttribute("reciever", name);
							request.setAttribute("newSenders", unreadMessagesSender);
							getServletConfig().getServletContext().getRequestDispatcher("/privatechatroom.jsp").forward(request, response);		
						}
						else{
							//DatabaseManager.log("table doesnot exists", "sender="+username+"reciever="+ram+" from PrivateChatRoom.java");
						}
					}
					else{
						response.sendRedirect("ViewPrivateMessages?param1=1");
					}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
