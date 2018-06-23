<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN</title>
<script type="text/javascript">
    window.onbeforeUnload = function(){
        window.location = "signup.jsp";
    }
</script>
</head>
<body>
	
	<form method="get" action="LoginCheck">
		username:<input type="text" name="username"><br>
		 password<input	type="password" name="password"><br>
		  <input type="submit" value="login">
	</form>
	<form action="signup.jsp"> <%-- this is the servlet name --%>
            
        <input type="submit" value="sign up">
        </form>

</body>
</html>