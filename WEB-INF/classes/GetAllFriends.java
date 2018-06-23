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

import java.util.List;


@WebServlet("/GetAllFriends")
public class GetAllFriends extends HttpServlet {
	

    public GetAllFriends() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = session.getAttribute("shiva").toString().trim();
		List<String> users = DatabaseManager.getAllUsers();
		StringBuffer sb = new StringBuffer();
		for(String s:users){
			sb.append(s+":");
		}
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
