import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 */
public class ServletFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
    throws IOException, ServletException {

    	HttpSession session = ((HttpServletRequest) request).getSession(false);
    	String param = session.getAttribute("shiva").toString();
    	if(session != null){
        	filterChain.doFilter(request, response);
        	return;
    	}
    	else{
    		HttpServletResponse respo = (HttpServletResponse) response;
    		respo.sendRedirect("/login.jsp");
    		return;
    	}

    }

    public void destroy() {
    }
}