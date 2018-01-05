<%@ page import="com.havryliuk.store.dao.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>List of products</title>
  </head>
  <body>
  <form action="list">
      <table>
          <tr>
              <th>Description</th>
              <th>Price</th>
              <th>Category</th>
          </tr>
      <c:forEach items="${products}" var="i">
          <tr>
              <td>
                  <a href="${i.getId()}">
                      ${i.getDescription()}</a></td>
              <td>${i.getPrice()}</td>
              <td>${i.getCategory()}</td>
          </tr>
      </c:forEach>
      </table>
  </form>
  <%if (session.getAttribute("userType").equals((UserType.ADMIN).toString())) {%>
  <a href="../admin/main">Go back</a>
  <%} else if (session.getAttribute("userType").equals((UserType.CUSTOMER).toString())) {%>
  <a href="../customer/main">Go back</a>
  <%}%>
  </body>
</html>
