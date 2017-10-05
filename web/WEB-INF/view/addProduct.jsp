<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<form action="addProduct" method="post">
    Add product:<br>
    <label>
        Description:
        <input name="description">
    </label><br>
    <label>
        Price:
        <input type="number" name="price" min="0" value="0.00" step="0.01">
    </label><br>
    Category: <label>
    <select name="category">
        <option>clothes</option>
        <option>electronics</option>
        <option>toys</option>
        <option>other</option>
    </select>
</label><br>
    <input type="submit" value="Create">
</form>
<br>
<a href="adminMain">Go back</a>
</body>
</html>
