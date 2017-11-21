package com.csye6225.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Mihir Patil,     001220443, patil.m@husky.neu.edu
 * Vivek Shetye,    001237626, shetye.v@husky.neu.edu
 * Pushkar Khedekar,001225610, khedekar.p@husky.neu.edu
 * Atul Takekar,    001220479, takekar.a@husky.neu.edu
 **/

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"id"},
        allowGetters = true)
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;



    @OneToMany(mappedBy = "user")
    List<Task> tasks = new ArrayList<>();

    private String password;
    @Column(unique = true)
    private String email;


    public User() {}

    public User(String password, String email) {

        this.setPassword(password);
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}