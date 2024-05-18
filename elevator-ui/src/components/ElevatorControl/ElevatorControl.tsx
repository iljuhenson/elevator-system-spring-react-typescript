import "./ElevatorControl.css"

function ElevatorControl() {
    return (
        <>
            {Array.from(Array(30).keys()).reverse().map(floorIdx => {
                return (<div key={floorIdx} className="floor-control">
                    <div className="floor-label"><p className="floot-label-text">{floorIdx}</p></div>
                    <div className="pickup-buttons-container">
                        <div className="pickup-up"></div>
                        <div className="pickup-down"></div>
                    </div>
                </div>)
            })}
        </>
    );
}

export default ElevatorControl;