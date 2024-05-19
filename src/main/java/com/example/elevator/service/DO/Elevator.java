package com.example.elevator.service.DO;

import java.util.*;
import java.util.stream.Collectors;

public class Elevator {
    private int id;
    private List<ElevatorUser> destinationFloors = new ArrayList<>();
    private int currentFloor;
    private int direction;

    public Elevator(int id, int currentFloor, int direction) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = direction;
        destinationFloors = new ArrayList<ElevatorUser>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Elevator() {
    }

    public Elevator(int id, List<ElevatorUser> destinationFloors, int currentFloor, int direction) {
        this.id = id;
        this.destinationFloors = destinationFloors;
        this.currentFloor = currentFloor;
        this.direction = direction;
    }

    public List<ElevatorUser> getDestinationFloors() {
        return destinationFloors;
    }

    public void setDestinationFloors(List<ElevatorUser> destinationFloors) {
        this.destinationFloors = destinationFloors;
    }

    public void addStop(ElevatorUser user) {
        destinationFloors.add(user);
    }

    public void step() {
        if(destinationFloors.isEmpty()) {
            direction = 0;
            return;
        }

        int lowestFloor = findLowestFloor();
        int highestFloor = findHighestFloor();

        if(direction == 0) {
            direction = (int) Math.signum(lowestFloor - currentFloor);
        }

        currentFloor += direction;
        destinationFloors.forEach(elevatorUser -> {
            if (!elevatorUser.isPickedUp() && elevatorUser.getPickupFloor() == currentFloor) {
                elevatorUser.pickUp();
            }
        });

        destinationFloors = destinationFloors.stream().filter(user -> !(user.isPickedUp() && user.getDestinationFloor() == currentFloor)).collect(Collectors.toList());

        lowestFloor = findLowestFloor();
        highestFloor = findHighestFloor();

        if (currentFloor >= highestFloor) {
            direction = -1;
        } else if (currentFloor <= lowestFloor) {
            direction = 1;
        }
    }

    public int findLowestFloor() {
        if(destinationFloors.isEmpty()) {
            return -1;
        }
        return Collections.min(destinationFloors, (u1, u2) -> u1.calculateActiveDestination() - u2.calculateActiveDestination()).calculateActiveDestination();
    }

    public int findHighestFloor() {
        if(destinationFloors.isEmpty()) {
            return -1;
        }
        return Collections.max(destinationFloors, (u1, u2) -> u1.calculateActiveDestination() - u2.calculateActiveDestination()).calculateActiveDestination();
    }

    public int findDistanceFromFloor(int floor, int afterPickupDirection) {
        int lastDestinationFloor = findLowestFloor();
        int firstDestinationFloor = findHighestFloor();

        if (firstDestinationFloor == -1) {
            return Math.abs(currentFloor - floor);
        } else if (afterPickupDirection == direction && currentFloor - floor == 0) {
            return 0;
        } else if (afterPickupDirection == direction && direction * (currentFloor - floor) > 0) {
            return Math.abs(lastDestinationFloor - firstDestinationFloor + Math.min(floor, currentFloor) - firstDestinationFloor + lastDestinationFloor - Math.max(floor, currentFloor));
        } else if (afterPickupDirection == direction && direction * (currentFloor - floor) < 0) {
            return Math.abs(floor - currentFloor);
        } else if (afterPickupDirection != direction && direction * (currentFloor - floor) > 0) {
            return (currentFloor > floor) ? lastDestinationFloor - currentFloor + lastDestinationFloor - floor : currentFloor - firstDestinationFloor + floor - firstDestinationFloor;
        } else if (afterPickupDirection != direction && direction * (currentFloor - floor) < 0) {
            return (currentFloor > floor) ? currentFloor - firstDestinationFloor + floor - firstDestinationFloor : lastDestinationFloor - currentFloor + lastDestinationFloor - floor;
        } else {
            // fallback worst case scenario is going back and forth for 1 time
            return Math.abs(2 * (lastDestinationFloor - firstDestinationFloor));
        }
    }

}
