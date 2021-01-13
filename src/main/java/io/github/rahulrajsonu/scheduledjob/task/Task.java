package io.github.rahulrajsonu.scheduledjob.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {
    private String name;
    private Milestone status;
    private Integer retryCount;

    public enum Milestone {
        Pending, Ready, Started, Failed, Completed
    }
}
