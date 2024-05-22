import "./ElevatorAdmin.css"

interface ElevatorAdminProps {
  updateElevatorsStatus: Function;
  setElevatorsAmount: Function;
  elevatorsAmount: number;
  updateMode: boolean;
  toggleUpdateMode: Function;
}

function ElevatorAdmin({ updateElevatorsStatus, setElevatorsAmount, elevatorsAmount, updateMode, toggleUpdateMode }: ElevatorAdminProps) {
  const step = async () => {
    await fetch("api/elevators/step", {
      method: "POST",
    })
    await updateElevatorsStatus()
  }

  const changeServerElevatorsAmount = async (newElevatorsAmount: number) => {
    await fetch(`api/elevators/update/amount/${newElevatorsAmount}`, {
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
        <button onClick={() => toggleUpdateMode()} className={`admin-button rectangular-button ${updateMode ? "admin-cancel-button" : ""}`}>
          {updateMode ? "Cancel" : "Update"}
        </button>
      </div>
      <div className="admin-button-section">
        <p>{elevatorsAmount} elevators</p>
        <input className="elevator-admin-elevator-amount-dial" type="range" min="1" max="16" value={elevatorsAmount} onChange={(e) => setElevatorsAmount(e.target.value)}/>
        <button className="admin-button rectangular-button" onClick={() => changeServerElevatorsAmount(elevatorsAmount)}>Apply</button>
      </div>
    </>
  );
}

export default ElevatorAdmin;