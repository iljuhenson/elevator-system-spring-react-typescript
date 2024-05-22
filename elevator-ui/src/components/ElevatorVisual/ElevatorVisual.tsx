import "./ElevatorVisual.css"
import {Elevator} from "../../types/Elevator";
import {ReactElement, useState} from "react";

interface ElevatorVisualProps {
  elevatorsStatus: Array<Elevator>;
  choseUpdateCurrentFloorAndId: (newUpdateElevatorId: number, newUpdateCurrentFloor: number) => void;
  updateMode: boolean;
  updateCurrentFloor: null | number;
  updateElevatorId: null | number;
  updateElevatorsStatus: Function;
  toggleUpdateMode: Function;
}

function ElevatorVisual({ elevatorsStatus, updateElevatorId, updateMode, choseUpdateCurrentFloorAndId, updateCurrentFloor, updateElevatorsStatus, toggleUpdateMode}: ElevatorVisualProps) {
  const [hoveredElevatorId, setHoveredElevatorId] = useState<number | null>(null);
  const [hoveredElevatorFloor, setHoveredElevatorFloor] = useState<number | null>(null);

  const mouseHoverOn = (elevatorId: number, floor: number) => {
    if (updateMode) {
      setHoveredElevatorFloor(floor);
      setHoveredElevatorId(elevatorId);
    }
  }

  const mouseHoverOff = () => {
    if (!updateMode || hoveredElevatorId !== null) {
      setHoveredElevatorFloor(null);
      setHoveredElevatorId(null);
    }
  }

  const sendUpdateRequest = async (newDestinationFloor: number) => {
    await fetch(`/api/elevators/update/elevator/${updateElevatorId}/floor/${updateCurrentFloor}/destination/${newDestinationFloor}`, {
      method: "POST",
    });

    await updateElevatorsStatus();
    toggleUpdateMode();
    mouseHoverOff();
  }

    return (
      <>
        {elevatorsStatus.map(elevator => {
          const floors: Array<ReactElement> = [];

          for (let i = 29; i >= 0; --i) {

            if (elevator.currentFloor === i) {
              floors.push(<div onClick={() => updateCurrentFloor === null && updateMode ? choseUpdateCurrentFloorAndId(elevator.id, i) : updateCurrentFloor !== i && updateMode ? sendUpdateRequest(i) : false} onMouseEnter={() => mouseHoverOn(elevator.id, i)} onMouseLeave={() => mouseHoverOff()} key={i}
                  className={`elevator-visual-current-floor-outline elevator-visual-section-floor-main-color ${hoveredElevatorId === elevator.id && hoveredElevatorFloor === i && updateCurrentFloor !== i ? "elevator-visual-hovered-floor" : ""} ${updateCurrentFloor === i ? "elevator-visual-section-floor-pickup-color" : ""}`}>
                  <div className="elevator-visual-current-floor-gap elevator-visual-current-floor-gap-color">
                    <div className="elevator-visual-current-floor-inside elevator-visual-section-floor-main-color">

                    </div>
                  </div>
                </div>
              )
            } else if(elevator.destinationFloors.filter(user => user.pickupFloor === i && !user.pickedUp).length !== 0) {
              floors.push(<div onClick={() => updateCurrentFloor === null && updateMode ? choseUpdateCurrentFloorAndId(elevator.id, i) : updateCurrentFloor !== i && updateMode ? sendUpdateRequest(i) : false} onMouseEnter={() => mouseHoverOn(elevator.id, i)} onMouseLeave={() => mouseHoverOff()} key={i}
                className={`elevator-visual-current-floor-outline ${hoveredElevatorId === elevator.id && hoveredElevatorFloor === i && updateCurrentFloor !== i ? "elevator-visual-hovered-floor" : ""} ${updateCurrentFloor === i ? "elevator-visual-section-floor-pickup-color" : ""}`}>
                <div className="elevator-visual-current-floor-gap elevator-visual-section-floor-pickup-color">
                  <div className="elevator-visual-current-floor-inside">

                  </div>
                </div>
              </div>)
            } else if(elevator.destinationFloors.filter(user => user.destinationFloor === i && user.pickedUp).length !== 0) {
              floors.push(<div onClick={() => updateCurrentFloor === null && updateMode ? choseUpdateCurrentFloorAndId(elevator.id, i) : updateCurrentFloor !== i && updateMode ? sendUpdateRequest(i) : false} onMouseEnter={() => mouseHoverOn(elevator.id, i)} onMouseLeave={() => mouseHoverOff()} key={i}
                className={`elevator-visual-current-floor-outline ${hoveredElevatorId === elevator.id && hoveredElevatorFloor === i && updateCurrentFloor !== i ? "elevator-visual-hovered-floor" : ""} ${updateCurrentFloor === i ? "elevator-visual-section-floor-pickup-color" : ""}`}>
                <div className="elevator-visual-current-floor-gap elevator-visual-section-floor-destination-active-color">
                  <div className="elevator-visual-current-floor-inside">

                  </div>
                </div>
              </div>)

            } else if(elevator.destinationFloors.filter(user => user.destinationFloor === i && !user.pickedUp).length !== 0) {
              floors.push(<div onClick={() => updateCurrentFloor === null && updateMode ? choseUpdateCurrentFloorAndId(elevator.id, i) : updateCurrentFloor !== i && updateMode ? sendUpdateRequest(i) : false} onMouseEnter={() => mouseHoverOn(elevator.id, i)} onMouseLeave={() => mouseHoverOff()} key={i}
                className={`elevator-visual-current-floor-outline ${hoveredElevatorId === elevator.id && hoveredElevatorFloor === i && updateCurrentFloor !== i ? "elevator-visual-hovered-floor" : ""} ${updateCurrentFloor === i ? "elevator-visual-section-floor-pickup-color" : ""}`}>
                <div className="elevator-visual-current-floor-gap elevator-visual-section-floor-destination-inactive-color">
                  <div className="elevator-visual-current-floor-inside">

                  </div>
                </div>
              </div>)

            } else {
              floors.push(
                <div onClick={() => updateCurrentFloor === null && updateMode ? choseUpdateCurrentFloorAndId(elevator.id, i) : updateCurrentFloor !== i && updateMode ? sendUpdateRequest(i) : false} onMouseEnter={() => mouseHoverOn(elevator.id, i)} onMouseLeave={() => mouseHoverOff()} key={i}
                  className={`elevator-visual-current-floor-outline ${hoveredElevatorId === elevator.id && hoveredElevatorFloor === i && updateCurrentFloor !== i ? "elevator-visual-hovered-floor" : ""} ${updateCurrentFloor === i ? "elevator-visual-section-floor-pickup-color" : ""}`}>
                  <div className="elevator-visual-current-floor-gap">
                    <div className="elevator-visual-current-floor-inside elevator-visual-section-floor-main-color">

                    </div>
                  </div>
                </div>
              )
            }
          }

          return <div key={elevator.id} className={`elevator-visual-floors-container ${updateElevatorId !== null && updateElevatorId === elevator.id ? "elevator-visual-selected-elevator" : ""}`}><p className="elevator-visual-index">{elevator.id}</p>{floors}</div>
        })}
      </>
    );
}

export default ElevatorVisual;