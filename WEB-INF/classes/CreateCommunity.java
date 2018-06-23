//package chat.community.createCommunity;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CreateCommunity")
public class CreateCommunity extends HttpServlet {
	

    public CreateCommunity() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String CommunityName = request.getParameter("community_name").trim();
		String username = session.getAttribute("shiva").toString().trim();
		PrintWriter w = response.getWriter();
		//check community exists
		if(CommunityName.equals("") || CommunityName==null){
			PrintWriter w1 = response.getWriter();
			w1.println("invalid community name!");
			return;
		}
		if(!DatabaseManager.isCommunity(CommunityName)){
			//create entry in communities table
			if(DatabaseManager.createCommunityEntry(CommunityName, username)){
				//if(DatabaseManager.addUserToCommunity(CommunityName, username)){
					//DatabaseManager.addCommunityToUserTable(CommunityName, username);
							request.setAttribute("username", username);
							getServletConfig().getServletContext().getRequestDispatcher("/homepage.jsp").forward(request, response);
				//}
				//else{
				//	w.println("error in creating community!");
				//	return;
				//}
			}
			else{
					w.println("unable to create community under that name!\nuse only [a-zA-Z!@#$%^&*()_]");				
					return;
			}
		}
		else{
			w.println("community exist");
			return;
		}

	// 	if(!HelperClass.isCommunity(CommunityName)){
	// 		File currentUser = new File("D:/database/currentUser.txt");
	// 		if(!currentUser.exists()){
	// 			currentUser.createNewFile();
	// 		}
	// 		BufferedReader b1 = new BufferedReader(new FileReader(currentUser));
	// 		StringTokenizer s = new StringTokenizer(b1.readLine(), "\n");
	// 		String currentUserName = s.nextToken();
	// 		File f1 = new File("D:/database/communities.txt");
	// 		File f2 = new File("D:/database/"+currentUserName+".txt");
	// 		if(!f1.exists()){
	// 			f1.createNewFile();
	// 		}
	// 		if(!f2.exists()){
	// 			f2.createNewFile();
	// 		}
	// 		FileWriter fStream = new FileWriter(f1, true);
	// 		BufferedWriter out = new BufferedWriter(fStream);
	// 		out.write(CommunityName);
	// 		out.newLine();
	// //		out.write("\n");
	// 		out.close();
	// 		FileWriter fStream1 = new FileWriter(f2, true);
	// 		BufferedWriter out1 = new BufferedWriter(fStream1);
	// 		out1.write(CommunityName);
	// 		out1.newLine();
	// //		out.write("\n");
	// 		out1.close();
			
	// 		// response.sendRedirect("homepage.jsp");
	// 	}
	// 	else{
	// 		response.sendRedirect("error.jsp");
	// 	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
