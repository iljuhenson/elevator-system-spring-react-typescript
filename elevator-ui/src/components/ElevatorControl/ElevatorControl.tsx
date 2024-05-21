import "./ElevatorControl.css"

interface ElevatorControlProps {
    initPickupData: (floor: number, direction: number) => void;
    pickupFloor: null | number;
    direction: null | number;
    updateElevatorStatus: Function;
    resetPickupData: Function;
}

function ElevatorControl({ initPickupData, pickupFloor, direction, updateElevatorStatus, resetPickupData }: ElevatorControlProps) {
    const addFloor = async (requestPickupFloor: number, requestDirection: number, requestDestinationFloor: number) => {
        console.log(requestDestinationFloor);
        await fetch(`api/elevators/pickup/floor/${requestPickupFloor}/direction/${requestDirection}/destination/${requestDestinationFloor}`, {
            method: "POST",
        })
        await updateElevatorStatus();
        resetPickupData();
    }

    return (
      <>
          <button onClick={() => resetPickupData()} className={typeof pickupFloor === "number" ? "mini-button" : "invisible"}>
              Cancel
          </button>
          {Array.from(Array(30).keys()).reverse().map(floorIdx => {
              return (<div key={floorIdx} className="floor-control">
                  <div
                    onClick={() => typeof pickupFloor === "number" && direction === Math.sign(floorIdx - pickupFloor) ? addFloor(pickupFloor!, direction!, floorIdx) : false}
                    className={`floor-label ${typeof pickupFloor === "number" && direction === Math.sign(floorIdx - pickupFloor) ? "floor-label-active" : "floor-label-inactive"}`}>
                      <p className="floor-label-text">{floorIdx}</p></div>
                  <div className="pickup-buttons-container">
                      {floorIdx !== 29 ? <div onClick={() => initPickupData(floorIdx, 1)}
                                              className={`pickup ${typeof pickupFloor === "number" ? "pickup-up-inactive" : "pickup-up"}`}></div> :
                        <div></div>}
                      {floorIdx !== 0 ? <div onClick={() => initPickupData(floorIdx, -1)}
                                             className={`pickup ${typeof pickupFloor === "number" ? "pickup-down-inactive" : "pickup-down"}`}></div> :
                        <div></div>}
                  </div>
              </div>)
          })}
      </>
    );
}

export default ElevatorControl;