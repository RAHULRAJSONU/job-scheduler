package io.github.rahulrajsonu.scheduledjob;

import io.github.rahulrajsonu.scheduledjob.jobs.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {

    @Autowired
    Processor processor;

    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void checkHeartbeat(){
        processor.process();
    }
}
