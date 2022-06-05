import React, { Component } from 'react';
import { showProduct } from '../util/APIUtils';
import './Home.css';
import Test from './test';

class Home extends Component {
        state={
            product: {
                id: 2137,
                name: "",
                month: "",
                price: 21.37
            }
        };

    getData = async () => {
        const dane = await showProduct();
        this.setState({product: dane})
        console.log(dane)
    }
    render() {
        return (
            <div className="home-container">
                <div className="container">
                    {/* <div className="graf-bg-container">
                        <div className="graf-layout">
                        </div>
                    </div> */}
                    <h1 className="home-title">Go on and compare average food products prices to inflation!</h1>
                    <div>
                        <button onClick={this.getData}>Klik</button>
                        <span>{this.state.product.name}</span>
                    </div>
                </div>
                <Test/>
            </div>
        )
    }
}

export default Home;