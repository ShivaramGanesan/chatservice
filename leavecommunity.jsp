<%@page import="java.util.StringTokenizer" %>
<%@page import= "java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN</title>
<script type="text/javascript">
    window.onbeforeUnload = function(){
        window.location = "homepage.jsp";
    }
</script>
</head>
<body>


	<%
		String allComm = request.getAttribute("allComm").toString();
		out.print("<b>COMMUNITIES</b><br>");
		StringTokenizer st = new StringTokenizer(allComm, ":");
		if(st.hasMoreTokens()){
	 	   while(st.hasMoreTokens()){
	   			String comm = st.nextToken();
	        	out.println("<a href =LeaveCommunity?grpName="+URLEncoder.encode(comm, "UTF-8")+">"+comm+"</a>");
	        	out.println("<br>");
	    	}
		}
		else{
			out.println("you have no communities<br>");
            
		}
	    out.print("<br>");
	    out.print("<br>");
	%>

	<!-- <form method="get" action="LeaveCommunity">
		Community name:<input type="text" name="grpName"><br>
		 
		  <input type="submit" value="leave">
	</form> -->
	<form action="homepage.jsp"> <%-- this is the servlet name --%>
            
        <input type="submit" value="back">
        </form>

</body>
</html>