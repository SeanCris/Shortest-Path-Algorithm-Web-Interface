import { ReactComponent as Logo } from './logo.svg';
import React, {useState} from 'react';
import './App.css';


export default function App() {

    const [circles, setCircles] = useState([]);
    const [text, setText] = useState([]);
    const width = 800;
    const height = 500;
    const circleRadius = 30;
    const initialMousePosition = {x: width / 2, y: height / 2};



    const [mousePosition, setMousePosition] = useState(initialMousePosition);

    const handleMouseMove = (event) => {

      const {clientX, clientY} = event;
      
      setMousePosition({x: clientX, y: clientY});
      
    };


    

    const clickevents = (event) => {


      let newText = (
        <text
          key={text.length + 1}
          textAnchor = "middle"
          fill="#132A2D"
          fontSize="30"
          fontWeight="bold"
          x={mousePosition.x}
          y={mousePosition.y + 10}
  
        >
          {text.length}
        </text>
      );

     

      
      let allTexts = [...text, newText];
        

      setText(allTexts);

  

      let newCircle = (
        <circle
          key={circles.length + 1}
          cx={mousePosition.x}
          cy={mousePosition.y}
          r={circleRadius}
          stroke="#A8F39C"
          strokeWidth="10"
          fill="#42F026"
        />
      );
      

      let allCircles = [...circles, newCircle];
  

      setCircles(allCircles);


    };


    
    return (

        <container>
                <svg  width = {width} height = {height} onClick = {clickevents}  onMouseMove = {handleMouseMove}>
                        {circles}
                        {text}
                        <circle
                            
                            cx = {mousePosition.x}
                            cy = {mousePosition.y}
                            r = {circleRadius}
                            fill = "none"
                            stroke="#59D0E1"
                            strokeWidth="10"

                        />
                </svg>

                <h1 fill = "red">
                    <text 
                      textAnchor='center'
                    >
                    Shortest Path Algorithm - TTU
                    </text>
                </h1>

        </container>
    );
};




