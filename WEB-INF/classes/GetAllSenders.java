//package chat.user.privateChatRoom;

import chat.database.DatabaseManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import javax.servlet.http.HttpSession;
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


@WebServlet("/GetAllSenders")
public class GetAllSenders extends HttpServlet {
	

    public GetAllSenders() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user").toString().trim();
		HttpSession session = request.getSession();
		String shiva = session.getAttribute("shiva").toString();
		List<String> allSendersList = DatabaseManager.unreadMessagesSender(user);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<allSendersList.size();i++){
			sb.append(allSendersList.get(i)+":");
		}
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
