<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.File" %>
<%@page import="java.util.StringTokenizer" %>
<%@page import="JarPackage.*" %>
<%@page import= "java.net.URLEncoder" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">


   
    
    function showAllNewMessages(mSender, mReciever){
        
    var allNewMessages = document.getElementById("newMessagesID").value;
    var mSender = document.getElementById("currentuserID").value;
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
                document.getElementById('messagesNew').innerHTML = inDivTag;
  
                                
            }
            else{
                //alert("ERROR status="+xReq.status+"readyState="+xReq.readyState);
            }
        }
        xReq.open("GET", "GetAllSenders?user="+encodeURIComponent(mSender) , true);
        
        xReq.send();
    }

     

    

    function repeat(){
        
       setInterval(showAllNewMessages, 4000);
    }

</script>

</head>
<body onload="repeat()">
    
    <% 
        // HttpSession session = request.getSession();
        String allUsers = request.getAttribute("allUsers").toString();
        String username = session.getAttribute("shiva").toString();
        String newMessages = request.getAttribute("newSenders").toString();
    %>   
    <% 
    out.println("unread Messages<br>");  
    %>      
    <div id="messagesNew">  
    <%
        
        StringTokenizer st1 = new StringTokenizer(newMessages, ":");
        while(st1.hasMoreTokens()){
        String nameOfSender = st1.nextToken();
        //out.println("<a href=SendPrivateMessage?name="+URLEncoder.encode(nameOfSender)+">"+nameOfSender+"</a>");
        out.println(nameOfSender);
        out.println("<br>");
        }
    out.println("<br>");

    %>
    </div>
    <%
        out.println("<b>users</b><br>");
    %>
    <div id="allUsers">
	<%
        //show all the users
        
        StringTokenizer st = new StringTokenizer(allUsers, ":");
        while(st.hasMoreTokens()){
            String displayName = st.nextToken();
            if(!displayName.equals(username)){
                out.println("<a href=SendPrivateMessage?name="+URLEncoder.encode(displayName)+">"+displayName+"</a>");
                out.print("<br>");
            }
        }
        out.print("<br>");
        out.print("<br>");
    %>
    </div>
    <input type="hidden" name="allNewMessages" id="newMessagesID" value="<%=newMessages%>">
    <input type="hidden" name="currentuser" id="currentuserID" value="<%=username%>">

	<!-- <form action="SendPrivateMessage"> <%-- this is the servlet name --%>         
            user  <input type="text" name="name" size="20px"> <br>
             <br>
             
        <input type="submit" value="GO">
        </form> -->
        
        <form action="homepage.jsp"> <%-- this is the servlet name --%>
            
        <input type="submit" value="back">
        </form>
        
        
</body>
</html>