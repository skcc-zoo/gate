package com.example.gate.controller;

import com.example.gate.Gate;
import com.example.gate.GateRepository;
import com.example.gate.event.CommonEvent;
import com.example.gate.event.PersonPassed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/gate")
@RestController
public class Controller {

    @Autowired
    GateRepository gateRepository;

    @GetMapping("/passed")
    public String passPerson(@RequestParam(name = "gate") Long gateId) {
        Optional<Gate> optionalGate = gateRepository.findById(gateId);
        if(optionalGate.isPresent()){
            Gate gate = optionalGate.get();
            PersonPassed personPassed = new PersonPassed();
            personPassed.setGateId(gate.getId());
            personPassed.setFromId(gate.getFromSpace());
            personPassed.setToId(gate.getToSpace());
            CommonEvent event = new CommonEvent(PersonPassed.class.getSimpleName(),personPassed);
            event.publish();
            System.out.println("personPassed event published.");
            return "success";
        }
        return "That gate does not exist.";
    }
}
