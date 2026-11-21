package io.github.onichdev.backend.expression;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "expressions")
public class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String text;

    @Column(columnDefinition = "text")
    private String definition;

    @Column(columnDefinition = "text")
    private String example;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp 
    private Instant updatedAt;

    protected Expression() {}

    public Expression(String text, String definition, String example){
        this.text = text;
        this.definition = definition;
        this.example = example;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public String getDefinition() { return definition; }
    public String getExample() { return example; }
}
