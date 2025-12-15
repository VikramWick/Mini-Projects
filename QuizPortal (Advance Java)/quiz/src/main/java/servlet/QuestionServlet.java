package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import dao.QuestionDao;
import model.Question;

public class QuestionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        QuestionDao dao = new QuestionDao();

        if ("list".equals(action)) {
            List<Question> list = dao.getAll();
            req.setAttribute("questions", list);
            RequestDispatcher rd = req.getRequestDispatcher("/admin/question-list.jsp");
            rd.forward(req, resp);
        } else { 
            RequestDispatcher rd = req.getRequestDispatcher("/admin/add-question.jsp");
            rd.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Question q = new Question();
        q.setQuestionText(req.getParameter("question"));
        q.setOption1(req.getParameter("option1"));
        q.setOption2(req.getParameter("option2"));
        q.setOption3(req.getParameter("option3"));
        q.setOption4(req.getParameter("option4"));
        q.setCorrectOption(req.getParameter("correct").charAt(0));
        q.setCategory(req.getParameter("category"));

        QuestionDao dao = new QuestionDao();
        dao.addQuestion(q);

        resp.sendRedirect(req.getContextPath() + "/admin/questions?action=list");
    }
}