<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>

<h1>Current User Name:  ${userSession.fullName}</h1>
<br>
<a href="logout">Logout</a>

</body>
</html>
