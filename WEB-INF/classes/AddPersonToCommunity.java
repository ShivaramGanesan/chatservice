// package chat.community.addPerson;

import chat.database.DatabaseManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddPersonToCommunity")
public class AddPersonToCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddPersonToCommunity() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String grpName = request.getParameter("grpName").trim();
		String username = request.getParameter("username").trim();
		String me = session.getAttribute("shiva").toString().trim();
		PrintWriter w = response.getWriter();
		String validGrpName = DatabaseManager.changeValidGrpName(grpName);
		String validUserName = DatabaseManager.changeValidUserName(username);


		

		if(username.equals("") || username == null || grpName.equals("") || grpName==null){
			PrintWriter w1 = response.getWriter();
			w1.println("invalid/incomplete credentials..");
			return;
		}

		if(DatabaseManager.isUser(username)){

			if(DatabaseManager.isCommunity(grpName)){

				if(DatabaseManager.isMember(grpName, me)){
					if(!DatabaseManager.isMember(grpName, username)){
						DatabaseManager.addPerson(username, grpName);//adds user to grp_members table;
						//DatabaseManager.addCommunityToUserTable(grpName, username);
						//w.println(username+grpName);
						response.sendRedirect("homepage.jsp");
					}
					else{
						w.println("this member is already a member of the grp");
					}
				}
				else{
					w.println("you are not a member!");
				}
			}
			else{
				w.println("this community does not exist!");
			}
		}
		
		else{
			w.println("not a valid user!");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
