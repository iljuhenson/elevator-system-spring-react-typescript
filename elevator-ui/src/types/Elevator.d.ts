import { ElevatorUser } from "./ElevatorUser";

export interface Elevator {
  id: int;
  destinationFloors: Array<ElevatorUser>;
  currentFloor: number;
  direction: number;
}