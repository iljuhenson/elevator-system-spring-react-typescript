import "./ElevatorAdmin.css"

interface ElevatorAdminProps {
  updateElevatorsStatus: Function;
}

function ElevatorAdmin({ updateElevatorsStatus }: ElevatorAdminProps) {
  const step = async () => {
    await fetch("api/elevators/step", {
      method: "POST",
    })
    await updateElevatorsStatus()
  }

  return (
    <>
      <div className="admin-button-section">
        <button onClick={step} className="admin-button square-button">
          <p className="square-button-text">
            Step
          </p>
        </button>
        <button className="admin-button rectangular-button">
          Update
        </button>
      </div>
      <div className="admin-button-section">
        <p>Elevators</p>
        <input type="range" min="1" max="16"/>
      </div>
    </>
  );
}

export default ElevatorAdmin;