package dao;

import java.sql.*;

public class AdminDao {

    public int getTotalQuizzes() {
        return getCount("SELECT COUNT(*) FROM quizzes");
    }

    public int getTotalQuestions() {
        return getCount("SELECT COUNT(*) FROM questions");
    }

    public int getTotalUsers() {
        return getCount("SELECT COUNT(*) FROM users");
    }

    public boolean login(String email, String password) {
        String sql = "SELECT * FROM admin_users WHERE email=? AND password=?";
        try (Connection con = DBUtil.getConnection()) {
            if (con == null) return false;
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    private int getCount(String sql) {
         try (Connection con = DBUtil.getConnection()) {
            if (con == null) return 0;
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}