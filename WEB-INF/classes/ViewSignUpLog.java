

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewSignUpLog")
public class ViewSignUpLog extends HttpServlet {
	
       
    
    public ViewSignUpLog() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String param1 = request.getParameter("param1");
		File f = new File("D:/database/signuplog.txt");
		if(!f.exists()){
			f.createNewFile();
		}

		response.sendRedirect("viewsignuplog.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
