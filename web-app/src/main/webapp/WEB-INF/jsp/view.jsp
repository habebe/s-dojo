<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

   if (request.getSession().getAttribute("access") == null) {
   %><jsp:forward
        page="../jsp/login.jsp" />
<%
   }
   %>
<html lang="en">
  <head>
  </head>

  <body>
  	<p>Hello World !!!</p>
  </body>
</html>