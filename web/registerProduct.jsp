<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 27/11/2018
  Time: 11.09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrer Produkt</title>
</head>
<body>
<form action="RegisterProduct" method="post">
    <input type="text" placeholder="Indtast navnet på produktet" name="name">
    <input type="text" placeholder="Indtast varenummeret" name="productNumber">
    <input type="text" placeholder="Indtast antal per batch" name="batchSize">
    <input type="text" placeholder="Indtast prisen for en batch" name="cost">
    <input type="submit" value="Registrer">
</form>

</body>
</html>