<%@page import="java.util.StringTokenizer" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	window.onbeforeUnload = function(){
        window.location = "homepage.jsp";
    }
</script>
</head>
<body>
		

	<%
		String username = session.getAttribute("shiva").toString();
		String allUsers = request.getAttribute("allUsers").toString();
		String allComm = request.getAttribute("allComm").toString();
	%>

	

	<%
		out.print("<b>COMMUNITIES</b><br>");
		StringTokenizer st = new StringTokenizer(allComm, ":");
		if(st.hasMoreTokens()){
		    while(st.hasMoreTokens()){
		    String gName = st.nextToken();
		        out.print(gName);
		        out.print("<br>");
		    }
		}
		else{
			out.println("you have no communities<br>");
            out.println("<a href =ViewPrivateMessages?param1=2>"+"Create new Community"+"</a>");
            out.println("<br>");
		}
		out.print("<br><b>USERS</b><br>");
		StringTokenizer st1 = new StringTokenizer(allUsers, ":");
		if(st1.hasMoreTokens()){
	        while(st1.hasMoreTokens()){
	            String displayName = st1.nextToken();
	            if(!displayName.equals(username)){
	                out.print(displayName);
	                out.print("<br>");
	            }
	        }
	    }
	    else{
	    	out.println("no user exists!");
		}
        //out.print(allUsers);
        
        out.print("<br>");
        out.print("<br>");
	%>
<form method="get" action="AddPersonToCommunity">
		group name:<input type="text" name="grpName"><br>
		 user <input	type="text" name="username"><br>
		  <input type="submit" value="add person to the group">
	</form>
	<form action="homepage.jsp"> <%-- this is the servlet name --%>
            
        <input type="submit" value="back">
        </form>

</body>
</html>