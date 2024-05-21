package com.example.elevator.controller;

import com.example.elevator.controller.error.ErrorMessageObject;
import com.example.elevator.service.DO.Elevator;
import com.example.elevator.service.ElevatorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/elevators")
public class ElevatorSystemController {
    private final ElevatorSystem elevatorSystem;

    @Autowired
    public ElevatorSystemController(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }

    @PostMapping("/pickup/floor/{floor}/direction/{direction}/destination/{destination}")
    public void pickup(@PathVariable int floor, @PathVariable int direction, @PathVariable int destination) {
        elevatorSystem.pickup(floor, direction, destination);
    }

    @PostMapping("/step")
    public void step() {
        elevatorSystem.step();
    }

    @GetMapping("/status")
    public List<Elevator> status() {
        return elevatorSystem.status();
    }

    @PostMapping("/update/elevator/{elevatorId}/floor/{currentFloor}/destination/{destinationFloor}")
    public void update(@PathVariable int elevatorId, @PathVariable int currentFloor, @PathVariable int destinationFloor) {
        elevatorSystem.updateElevatorLocationAndDestinationById(elevatorId, currentFloor, destinationFloor);
    }

    @PostMapping("/update/amount/{elevatorsAmount}")
    public void updateElevatorsAmount(@PathVariable int elevatorsAmount) {
        elevatorSystem.updateElevatorsAmount(elevatorsAmount);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<ErrorMessageObject> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity<ErrorMessageObject>(
            new ErrorMessageObject(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }
}
