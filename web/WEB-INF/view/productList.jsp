<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>List of products</title>
  </head>
  <body>
  <form action="productList">
      <table>
          <tr>
              <th>Description</th>
              <th>Price</th>
              <th>Category</th>
          </tr>
      <c:forEach items="${products}" var="i">
          <tr>
              <td>
                  <a href="product/${i.getId()}">
                      ${i.getDescription()}</a></td>
              <td>${i.getPrice()}</td>
              <td>${i.getCategory()}</td>
          </tr>
      </c:forEach>
      </table>
  </form>
  <a href="adminMain">Go back</a>
  </body>
</html>
