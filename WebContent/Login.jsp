
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<body>
	<s:actionmessage />

	<s:actionerror />

	<s:form id="idLoginForm" action="loginUser" namespace="/">
		<s:textfield name="username" placeholder="Username" label="Username/Email" />
		<s:password name="password" placeholder="Password" label="Password" />
		<s:checkbox name="remember" fieldValue="false" lable="Remember" />
		<s:submit value="Submit" />
	</s:form>
	
	<s:form id="idRegisterForm" action="registerUser" namespace="/">
		<s:textfield name="firstName" placeholder="First name" label="First Name" />
		<s:textfield name="lastName" placeholder="Last name" label="Last Name" />
		<s:textfield name="email" placeholder="Email" label="Email" />
		<s:password name="password" placeholder="Password" label="Password" />
		<s:password name="passwordReentered" placeholder="Password" label="Re Enter Password" />				
		<s:submit value="Submit" />
	</s:form>
	
</body>
</html>
