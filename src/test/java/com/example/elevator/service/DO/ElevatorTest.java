package com.example.elevator.service.DO;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @Test
    void findDistanceFromFloorStationary() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        assertEquals(4, elevator.findDistanceFromFloor(9, -1));
    }

    @Test
    void findDistanceFromFloorElevatorGoesUpButUseGoesDown() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(11, 2))));
        assertEquals(8, elevator.findDistanceFromFloor(9, -1));
    }

    @Test
    void findDistanceFromFloorElevatorGoesUpButUseGoesDown2() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(11, 2))));
        assertEquals(15, elevator.findDistanceFromFloor(2, -1));
    }

    @Test
    void findDistanceFromFloorElevatorGoesUpButUseGoesDownReverse() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(-1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(2, 8))));
        assertEquals(7, elevator.findDistanceFromFloor(6, -1));
    }

}