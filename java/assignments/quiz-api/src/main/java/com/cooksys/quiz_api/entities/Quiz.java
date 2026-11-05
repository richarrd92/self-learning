package com.cooksys.quiz_api.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

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

}
