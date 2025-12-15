package dao;

import java.sql.*;
import java.util.*;
import model.Result;

public class ResultDao {

    public void saveResult(int quizId, int userId, int score) {
        String sql = "INSERT INTO results(quiz_id, user_id, score) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            ps.setInt(2, userId);
            ps.setInt(3, score);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Result> getLeaderboard(int quizId) {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT r.score, u.name FROM results r JOIN users u ON r.user_id = u.id WHERE r.quiz_id=? ORDER BY r.score DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Result r = new Result();
                r.setScore(rs.getInt("score"));
                r.setUserName(rs.getString("name"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}