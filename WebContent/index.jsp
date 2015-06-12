<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html ng-app="HttpAuth">

<head>
<link rel="icon" href="img\favicon.png">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>    
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular-cookies.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular-route.js"></script>



<script src="js/scripts/app.js"></script>
<script src="js/modules/authentication/services.js"></script>
<script src="js/modules/authentication/controllers.js"></script>
<script src="js/modules/home/controllers.js"></script>

<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css\theme.css">
</head>

  <body>
  <div ng-view></div>
  </body>


</html>