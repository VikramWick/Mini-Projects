package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

@WebServlet("/auth")
public class UserAuthServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("register".equals(action)) {
            RequestDispatcher rd = req.getRequestDispatcher("/user/register.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
            rd.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        UserDao dao = new UserDao();

        if ("register".equals(action)) {
            User u = new User();
            u.setName(req.getParameter("name"));
            u.setEmail(req.getParameter("email"));
            u.setPassword(req.getParameter("password"));
            boolean ok = dao.register(u);
            if (ok) {
                req.setAttribute("msg", "Account created successfully.");
                RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("error", "Email already used.");
                RequestDispatcher rd = req.getRequestDispatcher("/user/register.jsp");
                rd.forward(req, resp);
            }
        } else if ("login".equals(action)) {
            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            User u = dao.login(email, pass);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", u);
                resp.sendRedirect(req.getContextPath() + "/quiz/list");
            } else {
                req.setAttribute("error", "Invalid email/password");
                RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
                rd.forward(req, resp);
            }
        }
    }
}