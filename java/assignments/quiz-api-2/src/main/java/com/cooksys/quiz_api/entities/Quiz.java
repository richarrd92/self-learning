package com.cooksys.quiz_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Quiz {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @CreationTimestamp
  private Timestamp created;

  private boolean deleted = false;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @SQLRestriction("deleted = false") // This annotation ensures that no deleted questions are pulled from the database
    private List<Question> questions = new ArrayList<>();

    /*
      Helper methods to manage in-memory relationships between Quiz and Question entities.
      Ensures the bidirectional links are properly maintained.
     */

    /**
     * Adds a question and sets the owning side automatically
     */
    public void addQuestion(Question question) {
        questions.add(question);
        question.setQuiz(this);
    }


    /**
     * Removes a question (orphanRemoval will delete it from DB)
     */
    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setQuiz(null);
    }

    /**
     * Clears all questions safely
     */
    public void clearQuestions() {
        for (Question q : questions) {
            q.setQuiz(null);
        }
        questions.clear();
    }
}
