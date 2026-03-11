package com.sustainableisland.seazen.dtos;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String animal;
    private Integer tasks;
    private Integer tasksInProgress;
    private Integer tasksDone;

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
