package model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    private int id;
    private String title;
    private String category;
    private List<Question> questions;
}
