package com.sustainableisland.seazen.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 255)
  private String name;

  @NotBlank
  @Size(max = 255)
  private String surname;

  private Integer age;

  @Size(max = 255)
  private String gender;

  @Size(max = 255)
  private String address;

  @Size(max = 255)
  private String phoneNumber;

  @NotBlank
  @Size(max = 255)
  @Email
  private String email;

  @NotBlank
  @Size(max = 255)
  private String password;

  @Size(max = 255)
  private String animal;

  private Integer tasks;

  private Integer tasksInProgress;

  private Integer tasksDone;

  public User() {
  }

  public User(String name, String surname, String email, String password) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAnimal() {
    return animal;
  }

  public void setAnimal(String animal) {
    this.animal = animal;
  }

  public Integer getTasks() {
    return tasks;
  }

  public void setTasks(Integer tasks) {
    this.tasks = tasks;
  }

  public Integer getTasksInProgress() {
    return tasksInProgress;
  }

  public void setTasksInProgress(Integer tasksInProgress) {
    this.tasksInProgress = tasksInProgress;
  }

  public Integer getTasksDone() {
    return tasksDone;
  }

  public void setTasksDone(Integer tasksDone) {
    this.tasksDone = tasksDone;
  }
}
