<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<style>

.photoDiv {
width: 600px;
height:600px;

}
.commentsDiv {
width: 460px;
height:600px;
overflow: auto;
}
.photo {
width: 100%;
height: 100%;
object-fit: contain;
}

.split {
height: 100%;
width: 50%;
position: fixed;
z-index: 1;
top: 0;
overflow-x: hidden;
padding-top: 20px;
}

/* Control the left side */
.left {
left: 0;
}

/* Control the right side */
.right {
right: 0;
}

/* If you want the content centered horizontally and vertically */
.centered {
position: absolute;
top: 50%;
left: 50%;
transform: translate(-50%, -50%);
text-align: center;
}
.footer {

padding: 10px;
bottom: 0;
}

.commentContainer {
overflow-wrap: break-word;
border-radius: 15px;
border: 1px solid ;
padding: 20px;
width: 400px;


}
/* Style the image inside the centered container, if needed */
.centered img {

}
</style>

<body>

<div class="split left">
    <a href="/"><button>All photos</button></a>
    <div class="centered">
        <div class="photoDiv">
        <img class="photo" src="data:image/jpeg;base64,${photo.data}" /> <br>
        </div>
        <c:choose>
            <c:when test="${photo.rating/photo.voteCounter == 'NaN'}">
                <br />
            </c:when>
            <c:otherwise>
                Rating: <fmt:formatNumber type="number" maxFractionDigits="2" value="${photo.rating/photo.voteCounter}" />
                <br />
            </c:otherwise>
        </c:choose>
        Number of votes: ${photo.voteCounter} <br>

        <form action="/photo/show/${photo.id}" method="post">
            <p>Rate picture:</p>
            <div>
                <input type="radio" id="Choice1"
                       name="rating" value="1">
                <label for="Choice1">1</label>

                <input type="radio" id="Choice2"
                       name="rating" value="2">
                <label for="Choice2">2</label>

                <input type="radio" id="Choice3"
                       name="rating" value="3" checked="checked">
                <label for="Choice3">3</label>

                <input type="radio" id="Choice4"
                       name="rating" value="4">
                <label for="Choice4">4</label>

                <input type="radio" id="Choice5"
                       name="rating" value="5">
                <label for="Choice5">5</label>
            </div>
            <div>
                <button type="submit">Submit</button>
            </div>
        </form>

    </div>
</div>

<div class="split right">
    <div class="centered">
        <div class="commentsDiv">
        <c:forEach items="${comments}" var="comment">
            <div class="commentContainer">
                <div> Author: ${comment.commentAuthor}</div>
                <div>${comment.commentText}</div>
            </div>
        </c:forEach>
        </div>
        <div class="footer">
            <form  action="/photo/addComment/${photo.id}" method="post">
                <div>
                    <input type="text" id="author" name="author" placeholder="author" required>
                </div>
                <div>
                    <textarea name="comment" rows="5" cols="60" placeholder="you comment" required></textarea>
                </div>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
    </div>

</div>




</body>
</html>