<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<style>

    div{
        width: 200px;
        height:200px;
    }
    img{
        width: 100%;
        height: 100%;
        object-fit: contain;
    }

    #col1 {
        position: absolute;
        left: 5px;
        padding: 10px;
    }

    #col2 {
        position: absolute;
        margin-left: 200px;
        padding: 10px;
    }

    #col3 {
        margin-left: 400px;
        padding: 10px;
    }

</style>

<body>
<a href="/uploadFile"><button>Upload photo</button></a>
<c:forEach var="photo" varStatus="status" items="${photos}" step="1" begin="0">
    <div id="col${status.index % 3 + 1}">
       <a href="/photo/show/${photo.id}"><img src="data:image/jpeg;base64,${photo.data}"/></a>
    </div>
</c:forEach>

</body>
</html>