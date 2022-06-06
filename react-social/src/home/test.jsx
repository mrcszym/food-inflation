import React from 'react'
import {Line} from 'react-chartjs-2'
 
function Test() {
  
  return (
    <div>
      <Line
      data = {{
        labels: ['styczen', 'luty', 'marzec', 'kwiecien', 'maj'],
        datasets: [
          {
            label:'wartosc',
            data: [12.2, 10, 13, 4, 9.9]
          }
        ]
      }}
      height={400}
      width={600}
      options={{maintainAspectRatio: false,}}
      />     
    </div>
  )
}

export default Test