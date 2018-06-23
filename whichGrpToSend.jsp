<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.File" %>
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

    function showAllNewMessages(){
        
   
        var xReq;
        
        if(window.XMLHttpRequest){
            xReq = new XMLHttpRequest();
        }
        else{
            xReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xReq.onreadystatechange=function(){
            if((xReq.readyState==4)){//remvoved status = = 200
                
                
                    var responseOfAjax = xReq.responseText;
                    var result = responseOfAjax.split(":");
                    var inDivTag="";
                    
                    for(var i=0;i<result.length;i++){
                        
                        inDivTag += result[i]+"<br>"
                    }
                   // "<a href=SendPrivateMessage?name="+URLEncoder.encode(result[i])+">"+result[i]+"</a>"+"<br>";
                document.getElementById('newCommMessagesDiv').innerHTML = inDivTag;
  
                                
            }
            else{
                //alert("ERROR status="+xReq.status+"readyState="+xReq.readyState);
            }
        }
        xReq.open("GET", "GetAllNewCommMessages", true);
        
        xReq.send();
    }

     

    

    function repeat(){
        
       setInterval(showAllNewMessages, 4000);
    }
</script>


</head>
<body onload="repeat()">
    
    <%
        // list communities
        String allComm = request.getAttribute("allComm").toString();
        String allNewMessages = request.getAttribute("newCommMessages").toString();
    %>
    <%
        out.println("Unread Messages<br>");
    %>
<div id="newCommMessagesDiv">
    <%
        StringTokenizer st1 = new StringTokenizer(allNewMessages, ":");
        while(st1.hasMoreTokens()){
        String nameOfComm = st1.nextToken();
        //out.println("<a href=SendPrivateMessage?name="+URLEncoder.encode(nameOfSender)+">"+nameOfSender+"</a>");
        out.println(nameOfComm);
        out.println("<br>");
        }
    out.println("<br>");
    %>
</div>
<%
    out.println("communities:<br>");
%>
    <%
        StringTokenizer st = new StringTokenizer(allComm, ":");
        if(st.hasMoreTokens()){
            while(st.hasMoreTokens()){
                String gName = st.nextToken();
                //String url = "SendToCommunity?community_name="+gName;
                out.println("<a href =SendToCommunity?community_name="+URLEncoder.encode(gName, "UTF-8")+">"+gName+"</a>");
                out.print("<br>");
            }
        }
        else{
            out.println("you have no communities<br>");
            out.println("<a href =ViewPrivateMessages?param1=2>"+"Create new Community"+"</a>");

        }
    out.print("<br>");out.print("<br>");
    %>
    
	

	<!-- <form action="SendToCommunity"> <%-- this is the servlet name --%>
            group name  <input type="text" name="community_name" size="20px"> <br>
             <br>
             
        <input type="submit" value="GO">
        </form> -->
        
        <form action="homepage.jsp"> <%-- this is the servlet name --%>
            
        <input type="submit" value="back">
        </form>
        
        
</body>
</html>