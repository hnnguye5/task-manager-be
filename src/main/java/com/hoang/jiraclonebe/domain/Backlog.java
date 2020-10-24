package com.hoang.jiraclonebe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is where the attributes are declared in an object
 * for class Backlog. It is used to map the attributes to a database
 * table.
 *
 * @author Hoang Nguyen
 * @version 1.0, 20 Oct 2020
 */
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

    // when EpicTask is deleted, refresh database to get updated list
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "backlog", orphanRemoval = true)
    private List<EpicTask> epicTaskList = new ArrayList<>();

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

    public List<EpicTask> getEpicTaskList() {
        return epicTaskList;
    }

    public void setEpicTaskList(List<EpicTask> epicTaskList) {
        this.epicTaskList = epicTaskList;
    }
}
