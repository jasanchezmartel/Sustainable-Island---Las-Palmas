package com.sustainableisland.seazen.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "notifications")
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotBlank
  @Size(max = 255)
  private String message;

  private LocalDate dateNotification;

  public Notification() {
  }

  public Notification(User user, String message, LocalDate dateNotification) {
    this.user = user;
    this.message = message;
    this.dateNotification = dateNotification;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDate getDateNotification() {
    return dateNotification;
  }

  public void setDateNotification(LocalDate dateNotification) {
    this.dateNotification = dateNotification;
  }
}
