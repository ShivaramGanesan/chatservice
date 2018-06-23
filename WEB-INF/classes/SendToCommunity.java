//package chat.community.sendToCommunity;

import chat.database.DatabaseManager;//
import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SendToCommunity")
public class SendToCommunity extends HttpServlet {
	
    public SendToCommunity() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String communityName = request.getParameter("community_name").trim();
		 
		HttpSession session = request.getSession();
		String username = session.getAttribute("shiva").toString().trim();;
		String validGrpName = DatabaseManager.changeValidGrpName(communityName);
		//System.out.println("sendToCommunity = "+communityName);
		if(communityName.equals("") || communityName == null){
			PrintWriter w = response.getWriter();
			w.println("not a valid grp!");
			return;
		}

		if(DatabaseManager.isMember(communityName, username)){
			String tableName = validGrpName+"_messages";
			String allMessages = DatabaseManager.getAllCommMessages(communityName);
			request.setAttribute("allMessages", allMessages);
			request.setAttribute("grpName", communityName);
			DatabaseManager.updateCommFlagValue(communityName, username);
			getServletConfig().getServletContext().getRequestDispatcher("/grpchatroom.jsp").forward(request, response);

		}
		else{
			PrintWriter w = response.getWriter();
			w.println("you are not a member of this grp"+ communityName);
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
