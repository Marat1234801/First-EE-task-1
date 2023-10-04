package servlets;

import db.DBUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;
import validation.Validation;

import java.io.IOException;

@WebServlet(value = "/task-edit")
public class EditTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String deadlineDate = req.getParameter("deadlineDate");
        Task task = DBUtils.getTaskById(id);
        if (task != null) {
            task.setName(name);
            task.setDescription(description);
            task.setDeadlineDate(deadlineDate);
        }
        if (!Validation.emptyValues(name, description, deadlineDate)) {
            DBUtils.editTask(task);
        }
        resp.sendRedirect("/home");
    }
}
