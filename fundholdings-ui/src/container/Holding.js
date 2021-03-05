import React from 'react'


export default class Holding extends React.Component {
  constructor(props) {
    console.log("Inside Constructor")
    super(props);
    this.state = {
        holdings: []
      };
    }

     componentDidMount(){

      console.log("Inside Component Mount")
      
      
        fetch(`http://localhost:8080/api/holding/${this.props.fund}?holdingdate=2020-10-31`)
        .then(resp => resp.json())
        .then(holdings => {
          this.setState({
            holdings: holdings
          })
        })
        
    } 

    render(){
        function  test(obj)
        {
          //  console.log(obj.instrumentname)
          return  <tr><td >{obj.instrumentname}</td><td >{obj.isin}</td><td >{obj.quantity}</td><td >{obj.marketvalue}</td><td >{obj.netAssetsPercentage}</td></tr>
    
        }
        
  
                 console.log("Inside the render Function");

       return (
          
         <div>   
        <table>
          <thead><tr><td>Instrument Name</td><td>ISIN</td><td>Quantity</td><td>Market Value in Lacs</td><td >% of Net Assets</td></tr></thead>
        <tbody>{this.state.holdings.map(test)}</tbody>
          </table>
        </div>
          
        
             
        )
        
    }
}
      