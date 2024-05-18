package com.example.elevator.service.DO;

public class ElevatorUser {
    private int pickupFloor;
    private int destinationFloor;
    private boolean isPickedUp = false;

    public ElevatorUser() {
    }

    public ElevatorUser(int pickupFloor, int destinationFloor) {
        this.pickupFloor = pickupFloor;
    }

    public int getPickupFloor() {
        return pickupFloor;
    }

    public void setPickupFloor(int pickupFloor) {
        this.pickupFloor = pickupFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void pickUp() {
        isPickedUp = true;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getActiveDestination() {
        return this.isPickedUp ? this.destinationFloor : this.pickupFloor;
    }
}
