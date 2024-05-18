import './App.css'
import ElevatorAdmin from "./components/ElevatorAdmin/ElevatorAdmin.tsx";
import ElevatorVisual from "./components/ElevatorVisual/ElevatorVisual.tsx";
import ElevatorControl from "./components/ElevatorControl/ElevatorControl.tsx";

function App() {
  return (
      <div className="app-background">
        <div className="app-main-panel">
          <div className="elevator-control-section">
            <ElevatorControl />
          </div>
          <div className="elevator-visual-section">
            <ElevatorVisual />
          </div>
          <div className="elevator-admin-section">
            <ElevatorAdmin />
          </div>
        </div>
      </div>
  )
}

export default App
