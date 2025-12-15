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
import model.Result;
import model.User;

@WebServlet("/quiz/leaderboard")
public class LeaderboardServlet extends HttpServlet{
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int quizId = Integer.parseInt(req.getParameter("id"));
        QuizDao qdao = new QuizDao();
        Quiz quiz = qdao.getQuizWithQuestions(quizId);

        ResultDao rdao = new ResultDao();
        List<Result> results = rdao.getLeaderboard(quizId);

        req.setAttribute("quiz", quiz);
        req.setAttribute("results", results);

        RequestDispatcher rd = req.getRequestDispatcher("/user/leaderboard.jsp");
        rd.forward(req, resp);
    }

}
