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

@WebServlet(value = "/task-details")
public class DetailsTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Task task = DBUtils.getTaskById(id);
        req.setAttribute("task", task);
        req.getRequestDispatcher("taskDetails.jsp").forward(req, resp);
    }

}
