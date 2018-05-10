<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<form action="add" method="post">
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
        <option>CLOTHES</option>
        <option>ELECTRONICS</option>
        <option>TOYS</option>
        <option>OTHER</option>
    </select>
</label><br><br>
    <input type="submit" value="Create">
</form>
<br>
<a href="../admin/main">Go back</a>
</body>
</html>
