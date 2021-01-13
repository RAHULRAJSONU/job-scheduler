package io.github.rahulrajsonu.scheduledjob.jobs;

import io.github.rahulrajsonu.scheduledjob.task.Task;
import io.github.rahulrajsonu.scheduledjob.task.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class Processor {

    @Autowired
    TaskRepository repository;

    public void process(){

        Map<String, List<Task>> mappedTask = repository.getMappedTask();
        mappedTask.entrySet().forEach(entry->{
            String owner = entry.getKey();
            List<Task> taskList = entry.getValue();
            Random random = new Random();
            for (Task task : taskList) {
                if(task.getStatus().name().equals("Pending")){
                    repository.setRetryCount(owner,task.getName(),0);
                }
//                System.out.println(String.format("Got new mappedTask %s to process, | status %s",task.getName(),(!task.getStatus().name().equals("Completed"))));
                boolean status = (!task.getStatus().name().equals("Completed"));
                if(status && random.nextBoolean() && task.getRetryCount() < 7){
                    log.info(String.format("Processing task for owner: %s | mappedTask: %s",owner,task.getName()));
                    repository.setTaskStatus(owner,task.getName(),Task.Milestone.Completed);
                    log.info(task.toString());
                }else if((!task.getStatus().name().equals("Completed")) && task.getRetryCount() < 7){
                    repository.setTaskStatus(owner,task.getName(),Task.Milestone.Started);
                    repository.setRetryCount(owner,task.getName(),task.getRetryCount()+1);
                }else if(task.getRetryCount() >= 7){
                    repository.setTaskStatus(owner,task.getName(),Task.Milestone.Failed);
                }
            }
        });
    }
}
