package io.github.rahulrajsonu.scheduledjob.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskRepository {

    Map<String, List<Task>> mappedTask;

    TaskRepository(){
        mappedTask = new HashMap<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().name("RAHUL-Task1").status(Task.Milestone.valueOf("Pending")).retryCount(0).build());
        tasks.add(Task.builder().name("RAHUL-Task2").status(Task.Milestone.valueOf("Pending")).retryCount(0).build());
        mappedTask.put("Rahul",tasks);
        mappedTask.put("Sumit", Arrays.asList(
                Task.builder().name("SUMIT-Task1").status(Task.Milestone.valueOf("Pending")).retryCount(0).build(),
                Task.builder().name("SUMIT-Task2").status(Task.Milestone.valueOf("Pending")).retryCount(0).build(),
                Task.builder().name("SUMIT-Task3").status(Task.Milestone.valueOf("Pending")).retryCount(0).build()
        ));
        mappedTask.put("Rohit",Arrays.asList(
                Task.builder().name("ROHIT-Task1").status(Task.Milestone.valueOf("Pending")).retryCount(0).build()
        ));
    }

    public Map<String, List<Task>> getMappedTask(){
        return mappedTask;
    }

    public void setTaskStatus(String owner,String task, Task.Milestone status){
        List<Task> tasks = mappedTask.get(owner);
        for (Task t : tasks) {
            if(task.equals(t.getName())){
                t.setStatus(status);
            }
        }
    }

    public void setRetryCount(String owner, String task, Integer count){
        List<Task> tasks = mappedTask.get(owner);
        for (Task t : tasks) {
            if(task.equals(t.getName())){
                t.setRetryCount(count);
            }
        }
    }
}
