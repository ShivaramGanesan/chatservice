// package chat.user.homePage;

import chat.database.DatabaseManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;


@WebServlet("/ViewPrivateMessages")
public class ViewPrivateMessages extends HttpServlet {
	
       
    
    public ViewPrivateMessages() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String choice = request.getParameter("choice");
		 HttpSession session = request.getSession();
		 
		String username = session.getAttribute("shiva").toString().trim();

		String param = request.getParameter("param1").toString().trim();
		int number = Integer.parseInt(param);
		//System.out.println(choice);
		//int ch=0;
		try{
		//	ch = Integer.parseInt(choice);
		}catch(Exception e){e.printStackTrace();
			response.sendRedirect("homepage.jsp");
		}
		// if(ch<0 || ch>6){
		// 	response.sendRedirect("homepage.jsp");
		// }
//		else{
			switch(number){
				case 1:{

					//send message
					if(username!=null && DatabaseManager.isUser(username)){
						List<String> unreadMessageList = DatabaseManager.unreadMessagesSender(username);

						List<String> list = new ArrayList<String>();
						list = DatabaseManager.getAllUsers();
						StringBuffer sb = new StringBuffer();
						StringBuffer sb1 = new StringBuffer();//for unread messages
						for(int i=0;i<list.size();i++){
							sb.append(list.get(i)+":");
						}
						for(int i=0;i<unreadMessageList.size();i++){
							sb1.append(unreadMessageList.get(i)+":");
						}
						String unreadMessagesSender = sb1.toString();
						String allUsers = sb.toString();
						request.setAttribute("allUsers", allUsers);
						request.setAttribute("username", username);
						request.setAttribute("newSenders", unreadMessagesSender);
						getServletConfig().getServletContext().getRequestDispatcher("/whomtosend.jsp").forward(request, response);
						break;
						//return;	
					}
					else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}
				
				}
				case 2:{
					//create community
					if(username!=null &&  DatabaseManager.isUser(username)){
						StringBuffer sb = new StringBuffer();
						List<String> allCommunities = DatabaseManager.allCommunities();
						for(int i=0;i<allCommunities.size();i++){
							sb.append(allCommunities.get(i)+":");
						}
						request.setAttribute("allComm", sb.toString());
						getServletConfig().getServletContext().getRequestDispatcher("/createcommunity.jsp").forward(request, response);
						// response.sendRedirect("createcommunity.jsp");
						// request.setAttribute("username", username);
						// 		getServletConfig().getServletContext().getRequestDispatcher("/createcommunity.jsp").forward(request, response);
						break;
						//return;
					}
					else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}					
					
				}
				case 3:{
					//send message to community
					if(username!=null &&  DatabaseManager.isUser(username)){
						StringBuffer sb = new StringBuffer();
						StringBuffer sb1 = new StringBuffer();
						//String ValidUserName = DatabaseManager.changeValidUserName(username);
						List<String> allCommunities = DatabaseManager.myCommunities(username);
						for(int i=0;i<allCommunities.size();i++){
							sb.append(allCommunities.get(i)+":");
						}

						List<String> newCommMessages = DatabaseManager.newCommMessages(username);
						for(int i=0;i<newCommMessages.size();i++){
							sb1.append(newCommMessages.get(i)+":");
						}
						request.setAttribute("newCommMessages", sb1.toString());
						request.setAttribute("allComm", sb.toString());
						System.out.println(sb.toString());
						getServletConfig().getServletContext().getRequestDispatcher("/whichGrpToSend.jsp").forward(request, response);
						// response.sendRedirect("createcommunity.jsp");
						//response.sendRedirect("whichGrpToSend.jsp");
						// request.setAttribute("username", username);
						// 		getServletConfig().getServletContext().getRequestDispatcher("/whichGrpToSend.jsp").forward(request, response);
						break;
						//return;
					}
					else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}
					
					
				}
				case 4:{
					//Add person to community
					if(username!=null &&  DatabaseManager.isUser(username)){


					StringBuffer sb = new StringBuffer();
					//String ValidUserName = DatabaseManager.changeValidUserName(username);
					List<String> allCommunities = new ArrayList<String>();
					allCommunities = DatabaseManager.myCommunities(username);
					for(int i=0;i<allCommunities.size();i++){
						sb.append(allCommunities.get(i)+":");
					}
					List<String> list = new ArrayList<String>();
					list = DatabaseManager.getAllUsers();
					StringBuffer sb1 = new StringBuffer();
					for(int i=0;i<list.size();i++){
						sb1.append(list.get(i)+":");
					}
					String allComm = sb.toString();
					String allUsers = sb1.toString();
					request.setAttribute("allUsers", allUsers);
					request.setAttribute("allComm", allComm);
					getServletConfig().getServletContext().getRequestDispatcher("/addpersontocommunity.jsp").forward(request, response);
					break;
					//return;
					// response.sendRedirect("addpersontocommunity.jsp");
					// request.setAttribute("username", username);
					// 		getServletConfig().getServletContext().getRequestDispatcher("/addpersontocommunity.jsp").forward(request, response);
					}
					else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}
					//break;
				}
				case 5:{
					//leave community
					if(username!=null && DatabaseManager.isUser(username)){
					StringBuffer sb = new StringBuffer();
					//String ValidUserName = DatabaseManager.changeValidUserName(username);
					List<String> allCommunities = DatabaseManager.myCommunities(username);
					for(int i=0;i<allCommunities.size();i++){
						sb.append(allCommunities.get(i)+":");
					}
					request.setAttribute("allComm", sb.toString());
					getServletConfig().getServletContext().getRequestDispatcher("/leavecommunity.jsp").forward(request, response);
					break;
					//return;
//					response.sendRedirect("leavecommunity.jsp");
					// request.setAttribute("username", username);
					// 		getServletConfig().getServletContext().getRequestDispatcher("/leavecommunity.jsp").forward(request, response);
					}
					else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}
					//break;
				}
				case 6:{
					//delete account
					if(username!=null && DatabaseManager.isUser(username)){
					response.sendRedirect("DeleteAccount");
					break;
					//return;
					}
					else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}
					//break;
				}
				case 7:{

					//logout
					if(DatabaseManager.isUser(username)){
					session.removeAttribute("shiva");
					session.invalidate();
					response.sendRedirect("index.jsp");
					break;
					//return;
					}
										else{
						response.sendRedirect("login.jsp");
						break;
						//return;
					}
					//break;
				}
				default :{
					break;
				}
			}
//		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
