import React, { Component } from 'react';
<<<<<<< Updated upstream
import { showProduct } from '../util/APIUtils';
=======
>>>>>>> Stashed changes
import './Home.css';
import Formularz from '../components/Formularz'
import Tabela from '../components/Tabela';
import { showProduct } from '../util/APIUtils';

<<<<<<< Updated upstream
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
=======

class Home extends Component {
    constructor() {
        super();
        this.state = {
            dane: "",
            productsList: [
                { id: 1, month: "2022-01", name: "product", price: 4.20 },
                { id: 2, month: "2022-02", name: "product", price: 4.21 },
                { id: 3, month: "2022-03", name: "product", price: 4.22 },
                { id: 4, month: "2022-04", name: "product", price: 4.23 }
            ],
            inflationList: [
                { id: 1, month: "2022-01", inflValue: 21.37 },
                { id: 2, month: "2022-02", inflValue: 21.30 },
                { id: 3, month: "2022-03", inflValue: 21.31 },
                { id: 4, month: "2022-04", inflValue: 21.32 }
            ]
        }
    }

    async getData(productValue) {
        const daneProduktu = await showProduct(productValue);
        this.setState({ productsList: daneProduktu })
    }

    handleAddDane(noweDane) {
        this.setState({ dane: noweDane })
        this.getData(noweDane.product)
        //console.log(noweDane.product)
    }

>>>>>>> Stashed changes
    render() {
        return (
            <div className="home-container">
                <div className="container">
<<<<<<< Updated upstream
                    {/* <div className="graf-bg-container">
                        <div className="graf-layout">
                        </div>
                    </div> */}
                    <h1 className="home-title">Go on and compare average food products prices to inflation!</h1>
                    <div>
                        <button onClick={this.getData}>Klik</button>
                        <span>{this.state.product.name}</span>
                    </div>
=======
                    <h1>Current inflation in Poland is 13.9%</h1>
                    <Formularz addDane={this.handleAddDane.bind(this)} />
                    {console.log(this.state.productsList)}
                    <Tabela productsList={this.state.productsList} />
>>>>>>> Stashed changes
                </div>
                <Test/>
            </div>
        );
    }
}

export default Home;