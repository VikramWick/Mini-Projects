package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import dao.AdminDao;

// No @WebServlet annotation because this is mapped in web.xml
public class AdminLoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/admin/login.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        System.out.println("DEBUG LOGIN: Email=['" + email + "'] Password=['" + pass + "']");

        if (email != null) email = email.trim();
        if (pass != null) pass = pass.trim();

        AdminDao dao = new AdminDao();

        if (dao.login(email, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute("admin", "true");
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            System.out.println("Connected as Admin");
        } else {
            req.setAttribute("error", "Invalid Admin Credentials");
            RequestDispatcher rd = req.getRequestDispatcher("/admin/login.jsp");
            rd.forward(req, resp);
        }
    }
}