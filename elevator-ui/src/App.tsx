import './App.css'
import ElevatorAdmin from "./components/ElevatorAdmin/ElevatorAdmin.tsx";
import ElevatorVisual from "./components/ElevatorVisual/ElevatorVisual.tsx";
import ElevatorControl from "./components/ElevatorControl/ElevatorControl.tsx";
import {useEffect, useState} from "react";
import {Elevator} from "./types/Elevator";

function App() {
  const [elevatorsStatus, setElevatorsStatus] = useState<Array<Elevator>>([]);
  const [elevatorsAmount, setElevatorsAmount] = useState(1);
  const [pickupFloor, setPickupFloor] = useState<null | number>(null);
  const [direction, setDirection] = useState<null | number>(null);

  const initPickupData = (newFloor: number, newDirection: number) => {
    setDirection(newDirection);
    setPickupFloor(newFloor);
  }

  const resetPickupData = () => {
    setDirection(null);
    setPickupFloor(null);
  }

  const updateElevatorsStatus = async () => {
    const response = await fetch("/api/elevators/status");
    const newElevatorsStatus: Array<Elevator> = await response.json();
    setElevatorsStatus(newElevatorsStatus);
  }

  useEffect(() => {
    (async () => {
      await updateElevatorsStatus();
    })()
  }, [])

  useEffect(() => {
    setElevatorsAmount(elevatorsStatus.length);
  }, [elevatorsStatus]);

  return (
      <div className="app-background">
        <div className="app-main-panel">
          <div className="elevator-control-section">
            <ElevatorControl initPickupData={initPickupData} pickupFloor={pickupFloor} direction={direction} updateElevatorStatus={updateElevatorsStatus} resetPickupData={resetPickupData}/>
          </div>
          <div className="elevator-visual-section">
            <ElevatorVisual elevatorsStatus={elevatorsStatus} />
          </div>
          <div className="elevator-admin-section">
            <ElevatorAdmin updateElevatorsStatus={updateElevatorsStatus} elevatorsAmount={elevatorsAmount} setElevatorsAmount={setElevatorsAmount} />
          </div>
        </div>
      </div>
  )
}

export default App
