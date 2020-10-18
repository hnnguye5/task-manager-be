package com.hoang.jiraclonebe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String epicIdentifier;
    private Integer epicSequence = 0;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="epic_id", nullable = false)
    @JsonIgnore
    private Epic epic;

    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEpicIdentifier() {
        return epicIdentifier;
    }

    public void setEpicIdentifier(String epicIdentifier) {
        this.epicIdentifier = epicIdentifier;
    }

    public Integer getEpicSequence() {
        return epicSequence;
    }

    public void setEpicSequence(Integer epicSequence) {
        this.epicSequence = epicSequence;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
