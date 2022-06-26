import { Component } from "react";
import './Tabela.css'

class Tabela extends Component {
    render() {
        let nazwa = this.props.dane.product;
        let renderTable = this.props.finalArray.map( item => {
            return (
                <tr>
                    <td>{item.month}</td>
                    <td>{item.price}zł</td>
                    <td>{item.infl}%</td>
                </tr>
            )
        });
        return (
            <table>
                <tr>
                    <th>Miesiąc</th>
                    <th>{nazwa}</th>
                    <th>Inflacja</th>
                </tr>
                {renderTable}
            </table>
        )
    }
}

export default Tabela