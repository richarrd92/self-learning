package com.cooksys.quiz_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Question {

  @Id
  @GeneratedValue
  private Long id;

  private String text;

  @CreationTimestamp
  private Timestamp created;

  private boolean deleted = false;

  @ManyToOne
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

      /*
      Helper methods to manage in-memory relationships between Quiz and Question entities.
      Ensures the bidirectional links are properly maintained.
     */

    /**
     * Adds an answer and sets the owning side automatically
     */
    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    /**
     * Removes an answer (orphanRemoval will delete it from DB)
     */
    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }

    /**
     * Clears all answers safely
     */
    public void clearAnswers() {
        for (Answer answer : answers) {
            answer.setQuestion(null);
        }
        answers.clear();
    }
}
