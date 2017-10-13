package com.csye6225.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id"},
        allowGetters = true)
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(length = 10)
    private String description;



    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private List<FileUpload> files = new ArrayList<>();

    @ManyToOne
    private User user;

    public Task() {

    }

    public Task(String description) {
        this.description=description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FileUpload> getFiles() {
        return files;
    }

    public void setFiles(List<FileUpload> files) {
        this.files = files;
    }
}