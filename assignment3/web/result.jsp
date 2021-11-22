<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"  type="text/css" href="css/style.css">
    <meta charset="UTF-8">
    <title>Result</title>
</head>
<body>
    <h1> Result </h1>
    <img src="images/image1.png" alt="a notebook and a pen">
    <article class="center">
        <h2> <%=session.getAttribute("subject")%>  Score: <%=session.getAttribute("score")%></h2>
        <form action="/network-programming/Controller" method="get">
            <input type="submit" value="Main Page"/>
        </form>
    </article>
</body>
</html>