package com.sustainableisland.seazen.dtos;

public class AnimalDTO {
    private Long id;
    private Long userId;
    private Long animalStatusId;
    private String name;
    private String type;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getAnimalStatusId() { return animalStatusId; }
    public void setAnimalStatusId(Long animalStatusId) { this.animalStatusId = animalStatusId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
