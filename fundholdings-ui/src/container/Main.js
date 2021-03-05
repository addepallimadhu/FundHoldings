import React from 'react'
import Holding from './Holding';


export default class Main extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      funds: [],
      selectedfund : "",
      amccode : ""
    };
    this.handleChange = this.handleChange.bind(this);
  }
    
    componentDidMount(){
        fetch("http://localhost:8080/api/fund/")
        .then(resp => resp.json())
        .then(funds => {
          this.setState({
            funds: funds
          })
        })
    } 

    handleChange(event) {
      
  //  console.log(event.target.value);
   // selectedfund = () => this.setState({selectedfund: event.target.value});
   //var selectedfund = event.target.value;
   //console.log(this.state.selectedfund);
   /* this.setState({
     selectedfund : event.target.value
  })*/
  var amccode = this.state.funds.filter( function(item){return (item.SchemeNAVName === event.target.value);} )
              
  this.setState( {
    amccode : amccode[0].amcCode
  }
  );

 console.log(amccode);
 console.log(this.state.amccode);
  // alert("Value Selected");
    }

    render(){
        
      console.log("Inside render")
      function  test(obj)
        {
    //        console.log(obj.amcCode)
         //   console.log(obj.amccode);
          if (obj.amcCode !== null)
          {
          //  console.log(obj.SchemeNAVName);
          //  console.log(obj.amcCode);
          return  <option>{obj.SchemeNAVName}</option>
          }
    
        }    
        
 
     //   var f = ""

       //  f = JSON.stringify(this.state.funds)
        
     //   console.log(this.props)

       // console.log(this.state)

     //   this.state.holdings.map(test)

        return (
          <div>
          <select id="cars" name="cars" onChange={this.handleChange}> 
        {this.state.funds.map(test)} 
          </select> 
          < Holding key={this.state.amccode} fund={this.state.amccode}/> 
                          </div> 
        )
    }
}
      //  < Holding fund={this.state.amccode}/> 

       /*      var holdings = [];
           
        fetch("http://localhost:8080/api/holding/MULTIPL14A?holdingdate=2020-10-31")
        .then(resp => resp.json()
        .then ( data => holdings=data)
        .then(() => console.log(holdings)))
     */