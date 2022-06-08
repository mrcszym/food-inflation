import React, { Component } from 'react';
import './Home.css';
import Formularz from '../components/Formularz'
import Tabela from '../components/Tabela';
import { getInflation, getPriceWithDate } from '../util/APIUtils';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { json2xml, js2xml } from 'xml-js'
import { Buffer } from 'buffer'

class Home extends Component {
    constructor() {
        super();
        this.fileNames = {
            json: "states.json",
            csv: "states.csv",
            text: "states.txt"
        }
        this.state = {
            dane: "",
            productsList: [],
            inflationList: [],
            finalArray: [],
            fileType: "json",
            fileDownloadUrl: "",
            status: ""
        }
        this.changeFileType = this.changeFileType.bind(this);
        this.download = this.download.bind(this);
    }

    async createFinalArray(lista1, lista2) {
        const lista0 = [];
        lista1.map((key, index) => {
            lista0.push({ month: key.month, price: key.price, infl: lista2[index].value })
        });
        this.setState({ finalArray: lista0 })
    }

    async getData(nazwaProduktu, dataStartowa, dataKoncowa) {
        const daneProduktu = await getPriceWithDate(nazwaProduktu, dataStartowa, dataKoncowa);
        const daneInflacji = await getInflation(dataStartowa, dataKoncowa)
        this.setState({ productsList: daneProduktu, inflationList: daneInflacji })
        this.createFinalArray(daneProduktu, daneInflacji)
    }

    handleAddDane(noweDane) {
        this.setState({ dane: noweDane })
        this.getData(noweDane.product, noweDane.dateStart, noweDane.dateEnd)

        //console.log(noweDane)
    }

    changeFileType(event) {
        const value = event.target.value;
        this.setState({ fileType: value });
    }

    download(event) {
        //event.preventDefault();
        // Prepare the file
        const element = event.target;
        let output;
        let blob = new Blob();
        if (this.state.fileType === "json") {
            output = JSON.stringify(this.state.finalArray, null, 4);
            blob = new Blob([output], {
                type: "application/json",
            });
        } else if (this.state.fileType === "xml") {
            window.Buffer = Buffer
            var options = { compact: true, ignoreComment: true, spaces: 4 };
            //let pom = JSON.stringify(this.state.finalArray, null, 4);
            output = js2xml(this.state.finalArray, options);
            blob = new Blob([output], {
                type: "application/xml",
            });
        }
        element.setAttribute("href", URL.createObjectURL(blob));
        element.setAttribute("download", `downloadfile${this.state.fileType}.${this.state.fileType}`);
    }


    render() {
        return (
            <div className="home-container">
                <div className="container">
                    <h1>Obecna inflacja w Polce wynosi 13.9%</h1>
                    <Formularz addDane={this.handleAddDane.bind(this)} />
                    <ResponsiveContainer width='80%' height={500}>
                        <LineChart data={this.state.finalArray}>
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="month" />
                            <YAxis type="number" domain={[-4, 14]} />
                            <Tooltip />
                            <Legend />
                            <Line type="monotone" dataKey="price" name='Cena produktu' stroke="#0d0c0c" />
                            <Line type="monotone" dataKey="infl" name='Wartość inflacji' stroke="#f00a0a" />
                        </LineChart>
                    </ResponsiveContainer>
                    <br />
                    <label>Pobierz plik w formacie:</label>
                    <select name="fileType"
                        onChange={this.changeFileType} value={this.state.fileType}>
                        <option value="json">JSON</option>
                        <option value="xml">XML</option>
                    </select>
                    <br/>
                    <br/>
                    <a className='button-85' role='button' onClick={this.download}>
                        Download the file!
                    </a>
                    <br />
                    <br />
                    <br />
                    <Tabela finalArray={this.state.finalArray} dane={this.state.dane} />
                </div>
            </div>
        );
    }
}

export default Home;