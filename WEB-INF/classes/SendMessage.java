//package chat.user.sendMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import chat.database.DatabaseManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	
    public SendMessage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getAttribute("username").toString();
		String message = request.getParameter("message");
		Calendar c = Calendar.getInstance();
		String time = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
		File f = new File("D:/database/xyz.txt");
		if(!f.exists()){
			f.createNewFile();
		}
		FileWriter fStream = new FileWriter(f, true);
		BufferedWriter out = new BufferedWriter(fStream);
		out.write(message+"\t["+time+"]");
		out.newLine();
//		out.write("\n");
		out.close();
		response.sendRedirect("sendMessages.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
