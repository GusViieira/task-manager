package com.gusviieira.taskManager.domain.repository;

import com.gusviieira.taskManager.domain.model.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {
}
