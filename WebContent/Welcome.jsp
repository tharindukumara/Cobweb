
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        Hello <s:property value="%{#session['loginId']}"/><br>
        <a href="<s:url action="logout"/>">Logout</a> 
               
        <s:form id="idAddDevice" action="addDevice" namespace="/">              
		<s:textfield name="name" placeholder="device name" label="Device name" />
		<s:textfield name="description" placeholder="description" label="Description" />		
		<s:select label="Device Type" 
		headerKey="-1" headerValue="Select Device Type"
		list="deviceList" 
		name="selectedDevice"/>	
		<s:textfield name="otherType" placeholder="sensor type" label="If other" />				
		<s:submit value="Submit" />		
		</s:form>
		
		
		<s:form id="idAddSensor" action="addSensor" namespace="/">
		<s:textfield name="name" placeholder="sensor name" label="Sensor name" />
		<s:textfield name="description" placeholder="description" label="Description" />		
		<s:select label="Sensor Type" 
  		headerKey="-1" headerValue="Select Sensor Type" 
  		list="sensorList"  
 		name="selectedSensor" />	 
 		<s:textfield name="otherType" placeholder="sensor type" label="If other" />	 
  		<s:submit value="Submit" />		  
 		</s:form>  
	
    </body>
</html>
