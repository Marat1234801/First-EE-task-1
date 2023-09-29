package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;

import java.io.IOException;

@WebServlet(value = "/add-task-servlet")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("task_name");
        String description = req.getParameter("task_description");
        String deadLineDate = req.getParameter("task_deadlineDate");
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDeadlineDate(deadLineDate);
        DBManager.addTask(task);
        resp.sendRedirect("/home");
    }
}
