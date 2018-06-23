<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.File" %>
<%@page import="java.util.StringTokenizer" %>
<%@page import= "java.net.URLEncoder" %>
<!-- <%@page import="chat.database.DatabaseManager" %> -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    
</script>>>

<script type="text/javascript">


   
    
    function showAllMessages(mSender, mReciever){
        var mSender = document.getElementById('senderID').value;
    var mReciever = document.getElementById('recieverID').value;
    
    
        var xReq;
        
        if(window.XMLHttpRequest){
            xReq = new XMLHttpRequest();
        }
        else{
            xReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xReq.onreadystatechange=function(){
            if((xReq.readyState==4)){//remvoved status = = 200
                
                //var x = Math.random();
                 //alert(mSender+mReciever);
                //alert(jsAllMessages);
                
                    //alert("ajax");
                    var responseOfAjax = xReq.responseText;
                    var result = responseOfAjax.split("]");
                    var inDivTag="";
                    for(var i=0;i<result.length;i++){
                        inDivTag += result[i]+"]"+"<br>";
                    }
                document.getElementById('displayAllMessages').innerHTML = inDivTag;
  
                                
            }
            else{
                //alert("ERROR status="+xReq.status+"readyState="+xReq.readyState);
            }
        }
        xReq.open("GET", "GetAllMessages?sender="+encodeURIComponent(mSender)+"&reciever="+encodeURIComponent(mReciever) , true);
        
        xReq.send();
    }

    

    function repeat(){
        if(window.history.forward(1) != null)
         window.history.forward(1);
       setInterval(showAllMessages, 4000);
    }

    
    // var interval = setInterval(showAllMessages, 3000);
    //PrivateChatRoom?message=&sender="+mSender+"&reciever="+mReciever
</script>
<title>Insert title here</title>
</head>
<body onload="repeat()">
    
<% 
    // HttpSession session = request.getSession();
    
    String messageArray = request.getAttribute("allMessages").toString();
    String username = session.getAttribute("shiva").toString();
	String sender = request.getAttribute("sender").toString();
	String reciever = request.getAttribute("reciever").toString();
    DatabaseManager.updateFlagValue(sender, reciever);
    //String ram = session.getAttribute("ram").toString();
     //String tableName = sender+"_"+reciever+"_messages";
    //String allMessages = DatabaseManager.getAllMessages(tableName).toString();;
%>
<%
    // if(messageArray!=null && !messageArray.equals("###")){
    //     StringTokenizer st = new StringTokenizer(messageArray, "]");
    //     while(st.hasMoreTokens()){
    //         out.print(st.nextToken()+']');
    //         out.print("<br>");
    //     }
    // }
out.println(reciever);
%>
<div id = "displayAllMessages">
<%
    //out.print(messageArray);
    
    String validSenderName = DatabaseManager.changeValidUserName(sender);
    String validRecieverName = DatabaseManager.changeValidUserName(reciever);
    String tableName = validSenderName+"_"+validRecieverName+"_messages";
    if(!tableName.contains("null")){
        String allMessages = DatabaseManager.getAllMessages(sender, reciever);
        if(allMessages!=null){
        StringTokenizer st = new StringTokenizer(allMessages, "]");
        while(st.hasMoreTokens()){
                out.print(st.nextToken()+']');
                out.print("<br>");
            }
        }
    }

%>
</div>
		<form  id= "formID" action="PrivateChatRoom" name="formName"> <%-- this is the servlet name --%>
            message   <input type="text" id="messageID" name="message" size="20px"> <br>
             <br>
             
             <input type ="hidden" id = "senderID" name = "sender" value = "<%=sender%>">
             <input type ="hidden" name = "reciever" id="recieverID" value = "<%=reciever%>">
             
        <input type="submit" value="send">
        </form>
        <form action="ViewPrivateMessages"> <%-- this is the servlet name --%>
            <input type="hidden" name="param1" value="1">
        <input type="submit" value="back">
        </form>
</body>
</html>