<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="productList">
      Products:<br>
      <table border="1">
          <tr>
              <th>Description</th>
              <th>Price</th>
              <th>Category</th>
          </tr>
      <c:forEach items="${products}" var="i">
          <tr>
              <td>${i.getDescription()}</td>
              <td>${i.getPrice()}</td>
              <td>${i.getCategory()}</td>
          </tr>
      </c:forEach>
      </table>
  </form>
  </body>
</html>
