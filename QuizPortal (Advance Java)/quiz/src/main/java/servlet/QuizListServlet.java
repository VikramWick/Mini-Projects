package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.QuizDao;
import model.Quiz;

@WebServlet("/quiz/list")
public class QuizListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        QuizDao dao = new QuizDao();
        List<Quiz> quizzes = dao.getAllQuizzes();
        req.setAttribute("list", quizzes);
        RequestDispatcher rd = req.getRequestDispatcher("/user/quiz-list.jsp");
        rd.forward(req, resp);
    }
}   
