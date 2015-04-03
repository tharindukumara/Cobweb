<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<title>Struts 2 - Login Application | ViralPatel.net</title>



<h2>Struts 2 - Login Application</h2>

<s:form action="login.action" method="post">
	<s:textfield name="username" key="label.username" size="20">
		<s:password name="password" key="label.password" size="20">
			<s:submit method="execute" key="label.login" align="center">
			</s:submit>
		</s:password>
	</s:textfield>
</s:form>
