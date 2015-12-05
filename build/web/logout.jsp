<%-- 
    Document   : logout
    Created on : Dec 5, 2015, 1:31:04 PM
    Author     : JacksonIsaac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Successful</title>
    </head>
    <body>
        <%
            session.invalidate();
        %>
        <script type="text/javascript">
            alert('Logout Successful');
            location='index.html';
        </script>
    </body>
</html>
