<%-- 
    Author     : JacksonIsaac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
    </head>
    
<body background="images/bg_green_gradient.jpg">
    <center><img src="images/amrita.png"></center>

<%
   Cookie cookie = null;
   Cookie[] cookies = null;
   // Get an array of Cookies associated with this domain
   cookies = request.getCookies();
   if( cookies != null ){
    out.println("Logged in as: ");
    out.print(cookies[cookies.length - 1].getValue());
/*
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         out.print("Name : " + cookie.getName( ) + ",  ");
         out.print("Value: " + cookie.getValue( )+" <br/>");
      }*/
  } else{
      out.println("<h2>No cookies founds</h2>");
  }
%>
    <a href="index.html" style="text-align: right; margin-right: 0em" target="_top">Logout</a>
    <center><h1><u>Welcome to Amrita Hostel Systems</u></h1></center>

    
    <center>

        <form name="selectProfile" method="post" action="studentServlet">
        Enter Roll No:
        <input type="text" value="Roll No." name="profile" 
                onblur="if(value==='') value='Roll No.'"
                onfocus="if(value==='Roll No.') value=''"/>
        <!--select id="profile" name="profile">
            <option value="12023">Karthik Senorj</option>
            <option value="12044" selected="selected">Subramonian Inian</option>
            <option value="12021">Jackson Isaac</option>
        </select-->
        <input id="open" type="submit" value="Open"/>
        </form>

        <input type="submit" value="Create New Profile" onclick="location.href = 'newProfile.htm';">

    </center>
</body>
</html>

