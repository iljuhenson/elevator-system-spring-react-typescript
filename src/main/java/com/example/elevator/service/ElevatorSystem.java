package com.example.elevator.service;

import com.example.elevator.service.DO.Elevator;
import com.example.elevator.service.DO.ElevatorUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ElevatorSystem {
    private List<Elevator> elevators;
    private int elevatorsAmount = 16;

    public ElevatorSystem() {
        elevators = new ArrayList<>();

        for (int i = 0; i < elevatorsAmount; i++) {
            elevators.add(new Elevator(i, 0, 0));
        }
    }

    // Method to handle elevator calls
    public void pickup(int floor, int direction, int destination) {
        Elevator elevator = findClosestElevator(floor, direction);
        elevator.addStop(new ElevatorUser(floor, destination));
    }

    // Method to update elevator state
//    public void addDestination(int elevatorId, int currentFloor, int destinationFloor) {
//        elevators.get(elevatorId).addStop(destinationFloor);
//    }

    // Method to simulate movement of elevators
    public void step() {
        elevators.forEach(Elevator::step);
    }

    // Method to get the status of all elevators
    public List<Elevator> status() {
        return elevators;
    }

    public void updateElevatorsAmount(int elevatorsAmount) {
        this.elevatorsAmount = elevatorsAmount;

        elevators = new ArrayList<>();
        for (int i = 0; i < this.elevatorsAmount; i++) {
            elevators.add(new Elevator(i, 0, 0));
        }
    }

    public Elevator findClosestElevator(int floor, int direction) {
        return Collections.min(elevators, Comparator.comparingInt(e -> e.findDistanceFromFloor(floor, direction)));
    }
}
