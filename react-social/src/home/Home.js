import React, { Component } from 'react';
import { showInflation, showProduct } from '../util/APIUtils';
import './Home.css';
import Test from './test';

class Home extends Component {
    state = {
        product: {
            id: 2137,
            name: "",
            month: "",
            price: 21.37
        },

        inflation: {
            id: 2137,
            month: "",
            value: ""
        }
    };

    getData = async (productValue) => {
        const dane = await showProduct(productValue);
        this.setState({ product: dane })
        console.log(dane)
    }

    getInflation = async (monthValue) => {
        const daneInfl = await showInflation(monthValue);
        this.setState({ inflation: daneInfl })
        console.log(daneInfl)
    }

    handleChange = selectedOption => {
        this.setState({
            selectedOption
        },
            console.log('Option selected:', this.state.selectedOption))
    }

    render() {
        return (
            <div className="home-container">
                <div className="container">
                    {/* <div className="graf-bg-container">
                        <div className="graf-layout">
                        </div>
                    </div> 
                    <h1 className="home-title">Go on and compare average food products prices to inflation!</h1>
                    <div>
                        <button onClick={this.getData}>Klik</button>
                        <span><br/>{this.state.product.name}</span>
                        <button onClick={() => this.getInflation('2022-04')}>Click here to see current inflation</button>
                        <span><br/>{this.state.inflation.value}</span>
                    </div>
                    */}
                    <h1>Current inflation in Poland is 13.9%</h1>
                    <div>
                        <label>Wybierz produkt, który chcesz sprawdzić:</label><br/>
                        <button onClick={() => this.getData("chleb 1000g")}>chleb</button>
                        <button onClick={() => this.getData("bulka kajzerka 50g")}>kajzerka</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default Home;