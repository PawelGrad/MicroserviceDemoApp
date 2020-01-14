<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<h1>Spring Boot File Upload Example</h1>
<hr/>
<h4>Upload Single File:</h4>
<form method="POST" action="/uploadFile" enctype="multipart/form-data">
    <input type="file" name="file"/> <br/><br/>
    <button type="submit">Submit</button>
</form>
<hr/>
${message}
</body>
</html>