<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
    window.onbeforeUnload = function(){
        window.location = "index.jsp";
    }
</script>
</head>
<body>
    
    
    <% 
    // HttpSession session = request.getSession();

    String username = session.getAttribute("shiva").toString();
    out.println("welcome " + username);
    %>
 <br>
 <ol>
    <li><a href="ViewPrivateMessages?param1=1">Inbox</a></li>
    <li><a href="ViewPrivateMessages?param1=2">Create Community</a></li>
    <li><a href="ViewPrivateMessages?param1=3">Send Message to Community</a></li>
    <li><a href="ViewPrivateMessages?param1=4">Add person to Community</a></li>
    <li><a href="ViewPrivateMessages?param1=5">Leave Community</a></li>
    <li><a href="ViewPrivateMessages?param1=6">Delete Account</a></li>
    <li><a href="ViewPrivateMessages?param1=7">log out</a></li>
        
       
    </ol>
 

    
</body>
</html>