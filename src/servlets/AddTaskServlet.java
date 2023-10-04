package servlets;

import db.DBManager;
import db.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;
import validation.Validation;

import java.io.IOException;

@WebServlet(value = "/addTask")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("task_name");
        String description = req.getParameter("task_description");
        String deadLineDate = req.getParameter("task_deadlineDate");
        if(!Validation.emptyValues(name, description, deadLineDate))
            DBUtils.addTask(new Task(name, description, deadLineDate));
        resp.sendRedirect("/home");
    }
}
