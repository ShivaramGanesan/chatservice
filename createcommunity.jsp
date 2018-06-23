<%@page import="java.util.StringTokenizer" %>
<%@page import= "java.net.URLEncoder" %>
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
    
	Object o = request.getAttribute("object");
    String allComm = request.getAttribute("allComm").toString();
	if(o == null){
		o = new String("null");
	}
%>
<%
    StringTokenizer st = new StringTokenizer(allComm, ":");
    while(st.hasMoreTokens()){
    String gName = st.nextToken();
        out.println("<a href =SendToCommunity?community_name="+URLEncoder.encode(gName, "UTF-8")+">"+gName+"</a>");
        out.print("<br>");
    }
%>
<form action="CreateCommunity"> <%-- this is the servlet name --%>
        community name: <input type = "text" name = "community_name">
        
        <input type="submit" value="create">
        </form>
        <form action="homepage.jsp"> <%-- this is the servlet name --%>
        
        
        <input type="submit" value="back">
        </form>
</body>
</html>