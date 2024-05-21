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
        if (floor > 29 || floor < 0 || destination> 29 || destination < 0) {
            throw new IllegalArgumentException("Invalid floor");
        }

        if (!(direction == 1 || direction == -1)) {
            throw new IllegalArgumentException("Invalid direction");
        }

        if (Math.signum(destination - floor) != direction) {
            throw new IllegalArgumentException("Invalid destination");
        }

        Elevator elevator = findClosestElevator(floor, direction);
        elevator.addStop(new ElevatorUser(floor, destination));
    }

    public void updateElevatorLocationAndDestinationById(int elevatorId, int newCurrentFloor, int destination) {
        if (newCurrentFloor > 29 || newCurrentFloor < 0 || destination > 29 || destination < 0) {
            throw new IllegalArgumentException("Invalid floors were given");
        }

        if (newCurrentFloor == destination) {
            throw new IllegalArgumentException("Destination and new current floor cannot be the same");
        }

        if (elevatorId < 0 || elevatorId > elevators.size() - 1) {
            throw new IllegalArgumentException("Invalid elevator id");
        }

        elevators.get(elevatorId).updateLocation(newCurrentFloor, destination);
    }

    // Method to simulate movement of elevators
    public void step() {
        elevators.forEach(Elevator::step);
    }

    // Method to get the status of all elevators
    public List<Elevator> status() {
        return elevators;
    }
    public void updateElevatorsAmount(int elevatorsAmount) {
        if(elevatorsAmount < 0 || elevatorsAmount > 16) {
            throw new IllegalArgumentException("elevatorsAmount must be between 0 and 16");
        }

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
