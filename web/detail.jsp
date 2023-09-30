<%@ page import="models.Task" %><%--
  Created by IntelliJ IDEA.
  User: amant
  Date: 30.09.2023
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <%@include file="navbar.jsp"%>
    <br>
    <div class="container">
        <%
            Task task = (Task) request.getAttribute("task");
            if(task != null){
        %>
                <form action="/detail" method="post" >
                    <input type="hidden" name="id" value="<%=task.getId()%>">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name of the task</label>
                        <input type="text" name="name" class="form-control" id="name" value="<%=task.getName()%>">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" name="description" id="description" ><%=task.getDescription()%></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="deadlineDate" class="form-label">Deadline date</label>
                        <input type="date" name="deadlineDate" class="form-control" id="deadlineDate" value="<%=task.getDeadlineDate()%>">
                    </div>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
                <form action="/delete" method="post">
                    <input type="hidden" name="id" value="<%=task.getId()%>">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
        <%
            }
        %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
