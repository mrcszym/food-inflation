import React, { Component } from 'react';
import './Home.css';

class Home extends Component {
    render() {
        return (
            <div className="home-container">
                <div className="container">
                    {/* <div className="graf-bg-container">
                        <div className="graf-layout">
                        </div>
                    </div> */}
                    <h1 className="home-title">Go on and compare average food products prices to inflation!</h1>
                </div>
            </div>
        )
    }
}

export default Home;