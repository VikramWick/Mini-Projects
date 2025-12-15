package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.QuizDao;
import dao.ResultDao;
import model.Question;
import model.Quiz;
import model.User;

@WebServlet(urlPatterns = {"/quiz/start", "/quiz/submit"})
public class AttemptQuizServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        QuizDao dao = new QuizDao();
        Quiz quiz = dao.getQuizWithQuestions(id);
        req.setAttribute("quiz", quiz);
        req.setAttribute("questions", quiz.getQuestions());
        RequestDispatcher rd = req.getRequestDispatcher("/user/quiz.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int quizId = Integer.parseInt(req.getParameter("quizId"));
        QuizDao qdao = new QuizDao();
        Quiz quiz = qdao.getQuizWithQuestions(quizId);
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        for (Question q : questions) {
            String ans = req.getParameter("q_" + q.getId());
            if (ans != null && ans.charAt(0) == q.getCorrectOption()) {
                score++;
            }
        }

        HttpSession session = req.getSession(false);
        User u = (User) session.getAttribute("user");

        ResultDao rdao = new ResultDao();
        rdao.saveResult(quizId, u.getId(), score);

        resp.sendRedirect(req.getContextPath() + "/quiz/leaderboard?id=" + quizId);
    }
    
}
