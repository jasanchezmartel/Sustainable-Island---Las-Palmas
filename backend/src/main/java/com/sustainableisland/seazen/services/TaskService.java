package com.sustainableisland.seazen.services;

import com.sustainableisland.seazen.dtos.TaskDTO;
import com.sustainableisland.seazen.models.Task;
import com.sustainableisland.seazen.models.User;
import com.sustainableisland.seazen.repositories.TaskRepository;
import com.sustainableisland.seazen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::convertToDTO);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    public Optional<TaskDTO> updateTask(Long id, TaskDTO taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setName(taskDetails.getName());
            task.setWaste(taskDetails.getWaste());
            task.setAmount(taskDetails.getAmount());
            task.setCompleted(taskDetails.isCompleted());
            return convertToDTO(taskRepository.save(task));
        });
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return true;
        }).orElse(false);
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setUserId(task.getUser() != null ? task.getUser().getId() : null);
        dto.setName(task.getName());
        dto.setWaste(task.getWaste());
        dto.setAmount(task.getAmount());
        dto.setCompleted(task.isCompleted());
        return dto;
    }

    private Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setWaste(dto.getWaste());
        task.setAmount(dto.getAmount());
        task.setCompleted(dto.isCompleted());
        
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            task.setUser(user);
        }
        
        return task;
    }
}
