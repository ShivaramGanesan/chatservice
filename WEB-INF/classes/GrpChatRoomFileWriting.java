//package chat.community.chatRoom;

import chat.database.DatabaseManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GrpChatRoomFileWriting")
public class GrpChatRoomFileWriting extends HttpServlet {
	

    public GrpChatRoomFileWriting() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = session.getAttribute("shiva").toString().trim();
		String message = request.getParameter("message").trim();
		String grpName = request.getParameter("grpName").toString().trim();
		String parsedMessage = DatabaseManager.parseMessage(message);
		//String parsedMessage1 = parseMessage.replaceAll("\\", "\\\\");
//		BufferedReader br = new BufferedReader(new FileReader(f));
//		String line;
		//while((line = br.readLine()) != null){
			//StringTokenizer st = new StringTokenizer(line, ":");
			//System.out.println("GrpChatRoomFileWriting= "+grpName);
			//if(st.hasMoreTokens()){
				//if(st.nextToken().equals(name)){
		DatabaseManager.updateCommFlagValue(grpName, username);
		if(!message.equals("")){
					Calendar c = Calendar.getInstance();
					String time = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
					//write to database

					String validGrpName = DatabaseManager.changeValidGrpName(grpName);

					
					// DatabaseManager.sendToGrp(username, parsedMessage, validGrpName, time);
					if(DatabaseManager.isUser(username) && DatabaseManager.isCommunity(grpName)){
						DatabaseManager.sendToGrp(username, parsedMessage, grpName, time);
					}
					else{
						response.sendRedirect("ViewPrivateMessages?param1=3");
					}

				//}
				
			//}
		//}			
					String allMessages = DatabaseManager.getAllCommMessages(grpName);
					request.setAttribute("allMessages", allMessages);
					request.setAttribute("grpName", grpName);
					
					getServletConfig().getServletContext().getRequestDispatcher("/grpchatroom.jsp").forward(request, response);
		}
		else{
			String validGrpName = DatabaseManager.changeValidGrpName(grpName);
				String allMessages = DatabaseManager.getAllCommMessages(validGrpName);
					request.setAttribute("allMessages", allMessages);
					request.setAttribute("grpName", grpName);
					
					getServletConfig().getServletContext().getRequestDispatcher("/grpchatroom.jsp").forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
