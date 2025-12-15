package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.*;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/login");
            return;
        }

        AdminDao dao = new AdminDao();
        req.setAttribute("totalQuiz", dao.getTotalQuizzes());
        req.setAttribute("totalQuestions", dao.getTotalQuestions());
        req.setAttribute("totalUsers", dao.getTotalUsers());

        RequestDispatcher rd = req.getRequestDispatcher("/admin/dashboard.jsp");
        rd.forward(req, resp);
    }
}
