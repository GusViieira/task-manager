package com.gusviieira.taskManager.rest;

import com.gusviieira.taskManager.domain.model.Task;
import com.gusviieira.taskManager.domain.repository.TaskRepository;
import com.gusviieira.taskManager.rest.dto.CreateTaskRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/task")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskRepository repository;

    @Inject
    public TaskResource(TaskRepository repository) {
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response insertTask(CreateTaskRequest taskRequest){
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setLimitDate(taskRequest.getLimitDate());
        repository.persist(task);

        List<Task> listaAtualizada = Task.listAll();

        return Response.ok(listaAtualizada).build();
    }

    @GET
    public Response listAllTasks(){
        PanacheQuery<Task> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateTask(@PathParam("id") Long id, CreateTaskRequest taskRequest){
        Task task = repository.findById(id);
        if(task != null){
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setStatus(taskRequest.getStatus());
            task.setLimitDate(taskRequest.getLimitDate());
            List<Task> listaAtualizada = task.listAll();
            return Response.ok(listaAtualizada).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteTask(@PathParam("id") Long id){
        Task task = repository.findById(id);
        if(task != null){
            repository.delete(task);
            List<Task> listaAtualizada = task.listAll();
            return Response.ok(listaAtualizada).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
