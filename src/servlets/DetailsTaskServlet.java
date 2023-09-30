package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;
import validation.Validation;

import java.io.IOException;

@WebServlet(value = "/detail")
public class DetailsTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("task", DBManager.getTaskById(id));
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String deadlineDate = req.getParameter("deadlineDate");
        if(!Validation.emptyValues(name, description, deadlineDate)){
            DBManager.getTaskById(id).setName(name);
            DBManager.getTaskById(id).setDescription(description);
            DBManager.getTaskById(id).setDeadlineDate(deadlineDate);
        }
        resp.sendRedirect("/home");
    }
}
