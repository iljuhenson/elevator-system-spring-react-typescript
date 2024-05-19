import "./ElevatorVisual.css"
import {Elevator} from "../../types/Elevator";
import {ReactElement} from "react";

interface ElevatorVisualProps {
    elevatorsStatus: Array<Elevator>;
}

function ElevatorVisual({ elevatorsStatus }: ElevatorVisualProps) {

    return (
      <>
        {elevatorsStatus.map(elevator => {
          const floors: Array<ReactElement> = [];

          for (let i = 29; i >= 0; --i) {
            if (elevator.currentFloor === i) {
              floors.push(<div key={i}
                  className="elevator-visual-current-floor-outline elevator-visual-section-floor-main-color">
                  <div className="elevator-visual-current-floor-gap elevator-visual-current-floor-gap-color">
                    <div className="elevator-visual-current-floor-inside elevator-visual-section-floor-main-color">

                    </div>
                  </div>
                </div>
              )
            } else if(elevator.destinationFloors.filter(user => user.pickupFloor === i && !user.pickedUp).length !== 0) {
              floors.push(<div key={i}
                className="elevator-visual-current-floor-outline elevator-visual-section-floor-pickup-color">
                <div className="elevator-visual-current-floor-gap">
                  <div className="elevator-visual-current-floor-inside">

                  </div>
                </div>
              </div>)
            } else if(elevator.destinationFloors.filter(user => user.destinationFloor === i && user.pickedUp).length !== 0) {
              floors.push(<div key={i}
                className="elevator-visual-current-floor-outline elevator-visual-section-floor-destination-active-color">
                <div className="elevator-visual-current-floor-gap">
                  <div className="elevator-visual-current-floor-inside">

                  </div>
                </div>
              </div>)

            } else if(elevator.destinationFloors.filter(user => user.destinationFloor === i && !user.pickedUp).length !== 0) {
              console.log(elevator.destinationFloors[0]);
              floors.push(<div key={i}
                className="elevator-visual-current-floor-outline elevator-visual-section-floor-destination-inactive-color">
                <div className="elevator-visual-current-floor-gap">
                  <div className="elevator-visual-current-floor-inside">

                  </div>
                </div>
              </div>)

            } else {
              floors.push(
                <div key={i}
                  className="elevator-visual-current-floor-outline elevator-visual-section-floor-main-color">
                  <div className="elevator-visual-current-floor-gap">
                    <div className="elevator-visual-current-floor-inside">

                    </div>
                  </div>
                </div>
              )
            }
          }

          return <div key={elevator.id} className="elevator-visual-floors-container">{floors}</div>
        })}
      </>
    );
}

export default ElevatorVisual;