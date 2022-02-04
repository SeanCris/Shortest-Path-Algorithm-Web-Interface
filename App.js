import React, {useState} from 'react';
import ReactDOM from 'react-dom';
import './App.css';

const width = 960;
const height = 500;
const circleRadius = 30;
const initialMousePosition = {x: width / 2, y: height / 2};




  const App = () => { 

    const [mousePosition, setMousePosition] = useState(initialMousePosition);

    const handleMouseMove = (event) => {

      const {clientX, clientY} = event;
      
      setMousePosition({x: clientX, y: clientY});
      
    };

    const handleClick = (event) => {

      const {clientX, clientY} = event;

      console.log("You clicked at: ", clientX, clientY);

    }

    
    return (

    <svg width = {width} height = {height} onMouseMove = {handleMouseMove}>
          <circle
              cx = {mousePosition.x}
              cy = {mousePosition.y}
              r = {circleRadius}

              onClick = {handleClick}


              />
            </svg>

    );
};


export default App;
