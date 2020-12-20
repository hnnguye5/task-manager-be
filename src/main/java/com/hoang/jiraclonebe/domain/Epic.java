package com.hoang.jiraclonebe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * This class is where the attributes are declared in an object
 * for class Epic. It is used to map the attributes to a database
 * table.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@Entity
public class Epic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Must enter an Epic Identifier")
    @Column(updatable = false, unique = true)
    @Size(message = "Must have at least 1 to 5 characters" ,min = 1, max = 5)
    private String epicIdentifier;

    @NotBlank(message = "Must enter a name for Epic")
    private String epicName;

    @NotBlank(message = "Must enter a description")
    private String epicDescription;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date startDate;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date endDate;

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

    // if backlog is deleted, all of its children(EpicTask) will be deleted
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "epic")
    @JsonIgnore
    private Backlog backlog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    // user who created the epic
    private String epicCreator;

    public Epic() {
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

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getEpicDescription() {
        return epicDescription;
    }

    public void setEpicDescription(String epicDescription) {
        this.epicDescription = epicDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getEpicCreator() {
        return epicCreator;
    }

    public void setEpicCreator(String epicCreator) {
        this.epicCreator = epicCreator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
