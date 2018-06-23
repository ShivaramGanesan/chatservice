<%@page import="java.io.FileReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream" %>
<%@page import="java.io.FileReader" %>
<%@page import="java.io.File" %>
<%@page import="java.util.StringTokenizer" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
    window.onbeforeUnload = function(){
        window.location = "homepage.jsp";
    }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    
</script>>
<script type="text/javascript">

//     function ajaxFunction(){
//     $(document).ready(function(){
//         $('#displayAllMessages').load('GetAllMessages'), function(){
//             $(this).unwrap();
//         });
//     });
// }
// ajaxFunction();
// setInterval(function(){
//     ajaxFunction()
// }, 5000);

    
    function showAllMessages(mSender, mReciever){
        var grpName = document.getElementById("grpID").value;
    
        var xReq;
        
        if(window.XMLHttpRequest){
            xReq = new XMLHttpRequest();
        }
        else{
            xReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xReq.onreadystatechange=function(){
            if((xReq.readyState==4) ){//removed xReq.status==200
                
                var x = Math.random();
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
        xReq.open("GET", "GetAllCommunityMessages?communityName="+grpName , true);
        
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
	String grpName = request.getAttribute("grpName").toString();
    String allMessages = request.getAttribute("allMessages").toString() ;
%>
<%
    out.println(grpName);
%>
<div id="displayAllMessages">
<%
    if(allMessages!=null && !allMessages.equals("###")){
        StringTokenizer st = new StringTokenizer(allMessages, "]");
        while(st.hasMoreTokens()){
            out.print(st.nextToken()+']');
            out.print("<br>");
        }
    }

%>
</div>
		<form action="GrpChatRoomFileWriting"> <%-- this is the servlet name --%>
            message   <input type="text" name="message" size="20px"> <br>
             <br>
             <input type="hidden" name="grpName" id="grpID" value="<%=grpName %>">
        <input type="submit" value="send">
        </form>
        <form action="ViewPrivateMessages"> <%-- this is the servlet name --%>
            <input type="hidden" name="param1" value="3">
        <input type="submit" value="back">
        </form>
</body>
</html>