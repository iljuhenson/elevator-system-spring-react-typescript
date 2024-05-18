package com.example.elevator.service.DO;

import java.util.*;

public class Elevator {
    private int id;
    private List<ElevatorUser> destinationFloors;
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

    public void addStop(ElevatorUser user) {
        destinationFloors.add(user);
    }

    public void step() {
        if(destinationFloors.isEmpty()) {
            direction = 0;
            return;
        }

        int lowestFloor = getLowestFloor();
        int highestFloor = getHighestFloor();

        if(direction == 0) {
            direction = (int) Math.signum(lowestFloor - currentFloor);
        }

        currentFloor += direction;
        destinationFloors.forEach(elevatorUser -> {
            if (!elevatorUser.isPickedUp() && elevatorUser.getPickupFloor() == currentFloor) {
                elevatorUser.pickUp();
            }
        });

        destinationFloors = destinationFloors.stream().filter(user -> user.isPickedUp() && user.getDestinationFloor() == currentFloor).toList();

        if (currentFloor > highestFloor) {
            direction = -1;
        } else if (currentFloor < lowestFloor) {
            direction = 1;
        }
    }

    public int getLowestFloor() {
        return Collections.min(destinationFloors, (u1, u2) -> u1.getActiveDestination() - u2.getActiveDestination()).getActiveDestination();
    }

    public int getHighestFloor() {
        return Collections.max(destinationFloors, (u1, u2) -> u1.getActiveDestination() - u2.getActiveDestination()).getActiveDestination();
    }

    public int findDistanceFromFloor(int floor, int afterPickupDirection) {
        int lastDestinationFloor = getLowestFloor();
        int firstDestinationFloor = getHighestFloor();

        if (afterPickupDirection == direction && currentFloor - floor == 0) {
            return 0;
        } else if (afterPickupDirection == direction && direction * (currentFloor - floor) > 0) {
            return lastDestinationFloor - firstDestinationFloor + Math.min(floor, currentFloor) - firstDestinationFloor + lastDestinationFloor - Math.max(floor, currentFloor);
        } else if (afterPickupDirection == direction && direction * (currentFloor - floor) < 0) {
            return Math.abs(floor - currentFloor);
        } else if (afterPickupDirection != direction && direction * (currentFloor - floor) > 0) {
            return (currentFloor > floor) ? lastDestinationFloor - currentFloor + lastDestinationFloor - floor : currentFloor - firstDestinationFloor + floor - firstDestinationFloor;
        } else if (afterPickupDirection != direction && direction * (currentFloor - floor) < 0) {
            return (currentFloor > floor) ? currentFloor - firstDestinationFloor + floor - firstDestinationFloor : lastDestinationFloor - currentFloor + lastDestinationFloor - floor;
        } else {
            // fallback worst case scenario is going back and forth for 1 time
            return 2 * (lastDestinationFloor - firstDestinationFloor);
        }
    }

}
