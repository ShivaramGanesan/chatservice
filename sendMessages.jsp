<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.File" %>

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
BufferedReader br = new BufferedReader(new FileReader("C:/xyz.txt"));
try {
    
    String line = br.readLine();
 	
    while (line != null) {
    	
    	out.println(line);
    	out.println("<br>");
        line = br.readLine();
    }
    
    
}catch(Exception e){
e.printStackTrace();
} finally {
    br.close();
}
%>
	
	<div align="center" style="margin-top: 50px;">
 
        <form action="SendMessage"> <%-- this is the servlet name --%>
            <input type="text" name="message" size="20px"> <br>
             <br>
        <input type="submit" value="send">
        </form>
 
    </div>
    <form action="homepage.jsp"> <%-- this is the servlet name --%>
            
        <input type="submit" value="back">
        </form>
</body>
</html>