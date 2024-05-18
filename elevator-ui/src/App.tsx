import './App.css'
import ElevatorAdmin from "./components/ElevatorAdmin/ElevatorAdmin.tsx";
import ElevatorVisual from "./components/ElevatorVisual/ElevatorVisual.tsx";
import ElevatorControl from "./components/ElevatorControl/ElevatorControl.tsx";
import {useEffect, useState} from "react";
import {Elevator} from "./types/Elevator";

function App() {
  const [elevatorsStatus, setElevatorsStatus] = useState<Array<Elevator>>([]);

  const updateElevatorsStatus = async () => {
    const response = await fetch("/api/elevators/status");
    const newElevatorsStatus: Array<Elevator> = await response.json();
    setElevatorsStatus(newElevatorsStatus);
  }

  useEffect(() => {
    updateElevatorsStatus();
  })

  return (
      <div className="app-background">
        <div className="app-main-panel">
          <div className="elevator-control-section">
            <ElevatorControl />
          </div>
          <div className="elevator-visual-section">
            <ElevatorVisual elevatorsStatus={elevatorsStatus} />
          </div>
          <div className="elevator-admin-section">
            <ElevatorAdmin updateElevatorsStatus={updateElevatorsStatus}/>
          </div>
        </div>
      </div>
  )
}

export default App
