package jcliew.sb_kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityEvent {
    private String activityId;
    private String eventId;
    private String eventName;
    private long timestamp;
}
