package dao;

import java.sql.*;
import java.util.*;
import model.Quiz;
import model.Question;

public class QuizDao {

    public int createQuiz(Quiz quiz, int[] questionIds) {
        String sql = "INSERT INTO quizzes(title, category) VALUES (?, ?)";
        int quizId = 0;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, quiz.getTitle());
            ps.setString(2, quiz.getCategory());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) quizId = rs.getInt(1);

            if (quizId > 0 && questionIds != null) {
                String sqlQ = "INSERT INTO quiz_questions(quiz_id, question_id) VALUES (?, ?)";
                try (PreparedStatement psQ = con.prepareStatement(sqlQ)) {
                    for (int qId : questionIds) {
                        psQ.setInt(1, quizId);
                        psQ.setInt(2, qId);
                        psQ.addBatch();
                    }
                    psQ.executeBatch();
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return quizId;
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Quiz q = new Quiz();
                q.setId(rs.getInt("id"));
                q.setTitle(rs.getString("title"));
                q.setCategory(rs.getString("category"));
                list.add(q);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Quiz getQuizWithQuestions(int id) {
        Quiz q = null;
        String sql = "SELECT * FROM quizzes WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                q = new Quiz();
                q.setId(rs.getInt("id"));
                q.setTitle(rs.getString("title"));
                q.setCategory(rs.getString("category"));
                
                QuestionDao qd = new QuestionDao();
                q.setQuestions(qd.getByQuizId(id));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return q;
    }
}