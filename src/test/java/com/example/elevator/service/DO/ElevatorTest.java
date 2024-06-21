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
    void findDistanceFromFloorElevatorGoesUpButUserGoesDown2() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(11, 2))));
        assertEquals(15, elevator.findDistanceFromFloor(2, -1));
    }

    @Test
    void findDistanceFromFloorElevatorGoesUpButUserGoesDown3() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(11, 2))));
        assertEquals(3, elevator.findDistanceFromFloor(8, -1));
    }


    @Test
    void findDistanceFromFloorElevatorGoesUpButUseGoesDownReverse() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(-1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(2, 8))));
        assertEquals(7, elevator.findDistanceFromFloor(6, -1));
    }

    @Test
    void findDistanceFromFloorElevatorGoesDownPickupFloorIsBellow() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(-1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(2, 8))));
        assertEquals(2, elevator.findDistanceFromFloor(3, 1));
    }

    @Test
    void findDistanceFromFloorBelow() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(8);
        elevator.setDirection(1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(13, 8, false), new ElevatorUser(5, 3, true))));
        assertEquals(16, elevator.findDistanceFromFloor(2, -1));
    }

    @Test
    void elevatorReset() {
        Elevator elevator = new Elevator();
        elevator.setCurrentFloor(5);
        elevator.setDirection(1);
        elevator.setDestinationFloors(new ArrayList<ElevatorUser>(Arrays.asList(new ElevatorUser(13, 8, false), new ElevatorUser(5, 3, true))));
        elevator.reset();
        assertEquals(elevator.getCurrentFloor(), 0);
        assertEquals(elevator.getDirection(), 0);
        assertEquals(elevator.getDestinationFloors().size(), 0);
    }
}