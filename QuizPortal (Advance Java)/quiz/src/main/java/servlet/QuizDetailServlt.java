package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.QuizDao;
import model.Quiz;

@WebServlet("/admin/quiz-detail")
public class QuizDetailServlt extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        QuizDao dao = new QuizDao();
        Quiz quiz = dao.getQuizWithQuestions(id);
        req.setAttribute("quiz", quiz);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/quiz-detail.jsp");
        rd.forward(req, resp);
    }
    
}
