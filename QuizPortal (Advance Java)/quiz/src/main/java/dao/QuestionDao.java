package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDao {

     public void addQuestion(Question q) {
        String sql = "INSERT INTO questions(question_text,option1,option2,option3,option4,correct_option,category) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, q.getQuestionText());
            ps.setString(2, q.getOption1());
            ps.setString(3, q.getOption2());
            ps.setString(4, q.getOption3());
            ps.setString(5, q.getOption4());
            ps.setString(6, String.valueOf(q.getCorrectOption()));
            ps.setString(7, q.getCategory());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Question> getAll() {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Question q = mapRow(rs);
                list.add(q);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Question getById(int id) {
        String sql = "SELECT * FROM questions WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    private Question mapRow(ResultSet rs) throws Exception {
        Question q = new Question();
        q.setId(rs.getInt("id"));
        q.setQuestionText(rs.getString("question_text"));
        q.setOption1(rs.getString("option1"));
        q.setOption2(rs.getString("option2"));
        q.setOption3(rs.getString("option3"));
        q.setOption4(rs.getString("option4"));
        q.setCorrectOption(rs.getString("correct_option").charAt(0));
        q.setCategory(rs.getString("category"));
        return q;
    }

    public List<Question> getByQuizId(int quizId) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT q.* FROM questions q " +
                     "JOIN quiz_questions qq ON q.id = qq.question_id " +
                     "WHERE qq.quiz_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
}
