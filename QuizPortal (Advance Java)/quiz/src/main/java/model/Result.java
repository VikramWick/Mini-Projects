package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int id;
    private int quizId;
    private int userId;
    private int score;
    private String userName; // for leaderboard display
}
