package servlet;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.*;
import model.Question;
import model.Quiz;

@WebServlet("/admin/create-quiz")
public class QuizCreateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        QuestionDao qdao = new QuestionDao();
        List<Question> questions = qdao.getAll();
        req.setAttribute("list", questions);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/create-quiz.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String category = req.getParameter("category");
        String[] qids = req.getParameterValues("qId");

        int[] ids = (qids != null) ? new int[qids.length] : new int[0];
        if (qids != null) {
            for (int i = 0; i < qids.length; i++) {
                ids[i] = Integer.parseInt(qids[i]);
            }
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setCategory(category);

        QuizDao dao = new QuizDao();
        int quizId = dao.createQuiz(quiz, ids);

        resp.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
    }
}