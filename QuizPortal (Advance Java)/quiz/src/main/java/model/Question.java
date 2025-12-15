package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private char correctOption;
    private String category;
    
}
