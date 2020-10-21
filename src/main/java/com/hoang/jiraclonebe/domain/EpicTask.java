package com.hoang.jiraclonebe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class EpicTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String epicIdentifier;

    @Column(updatable = false)
    private String epicSequence;
    @NotBlank(message = "Please write a summary for task")
    private String summary;
    @NotBlank(message = "Please enter a acceptance criteria for task")
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private Date createOn;
    private Date updatedOn;

    @PrePersist
    protected void createOn() {
        this.createOn = new Date();
    }

    @PreUpdate
    protected void updatedOn() {
        this.updatedOn = new Date();
    }

    // Cascade refresh the backlog if epic task is deleted
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
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

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
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

    @Override
    public String toString() {
        return "EpicTask{" +
                "id=" + id +
                ", epicIdentifier='" + epicIdentifier + '\'' +
                ", epicSequence='" + epicSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", dueDate=" + dueDate +
                ", createOn=" + createOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
