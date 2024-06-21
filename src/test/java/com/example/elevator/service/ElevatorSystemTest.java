package com.example.elevator.service;

import com.example.elevator.service.DO.Elevator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorSystemTest {

    @Test
    void resetElevatorStates() {
        ElevatorSystem elevatorSystem = new ElevatorSystem();
        elevatorSystem.updateElevatorsAmount(2);
        elevatorSystem.setElevators(new ArrayList<>(Arrays.asList(new Elevator(0, 4, 1), new Elevator(1, 6, -1))));
        elevatorSystem.resetElevatorStates();
        List<Elevator> elevators = elevatorSystem.getElevators();
        elevators.forEach(elevator -> {
            assertEquals(elevator.getCurrentFloor(), 0);
            assertEquals(elevator.getDirection(), 0);
            assertEquals(elevator.getDestinationFloors().size(), 0);
        });
    }
}