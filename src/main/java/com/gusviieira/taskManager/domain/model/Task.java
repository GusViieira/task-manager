package com.gusviieira.taskManager.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Task extends PanacheEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int status;

    @Column
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate limitDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && status == task.status && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(limitDate, task.limitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, limitDate);
    }
}
