package com.example.gate;

import com.example.gate.event.CommonEvent;
import com.example.gate.event.GateCreated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;

@Entity
public class Gate {
    @Id
    @GeneratedValue
    private Long id;
    private String fromSpace;
    private String toSpace;


    @PostPersist
    public void publishGateCreatedEvent() {
        GateCreated gateCreated = new GateCreated();
        gateCreated.setGateId(this.id);
        gateCreated.setFromSpaceId(this.fromSpace);
        gateCreated.setToSpaceId(this.toSpace);
        CommonEvent event = new CommonEvent(GateCreated.class.getSimpleName(),gateCreated);
        event.publish();
        System.out.println("gateCreated event published.");

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromSpace() {
        return fromSpace;
    }

    public void setFromSpace(String fromSpace) {
        this.fromSpace = fromSpace;
    }

    public String getToSpace() {
        return toSpace;
    }

    public void setToSpace(String toSpace) {
        this.toSpace = toSpace;
    }
}
