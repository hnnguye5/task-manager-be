package com.hoang.jiraclonebe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * This class is where the attributes are declared in an object
 * for class EpicTask. It is used to map the attributes to a database
 * table.
 *
 * @author Hoang Nguyen
 * @version 1.0, 20 Oct 2020
 */
@Entity
public class EpicTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String epicIdentifier;

    @Column(updatable = false, unique = true)
    private String epicSequence;

    @NotBlank(message = "Please write a summary for task")
    private String summary;

    @NotBlank(message = "Please enter a acceptance criteria for task")
    private String acceptanceCriteria;
    private String status;
    private Integer priority;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date dueDate;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date createdOn;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updatedOn;

    @PrePersist
    protected void createdOn() {
        this.createdOn = new Date();
    }

    @PreUpdate
    protected void updatedOn() {
        this.updatedOn = new Date();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;

    public EpicTask() {
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

    public String getEpicSequence() {
        return epicSequence;
    }

    public void setEpicSequence(String epicSequence) {
        this.epicSequence = epicSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

}
