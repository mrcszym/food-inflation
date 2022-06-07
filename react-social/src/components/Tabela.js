import { Component } from "react";

class Tabela extends Component {
    render() {
        let tableHeaders = this.props.productsList.map(header => {
            return (
                <th>{header.month}</th>
            )
        });

        let tableValues = this.props.productsList.map(item => {
            return (
                <td>{item.price} zł</td>
            )
        });

        return (
            <table>
                <tr>
                    <th>Month</th>
                    {tableHeaders}
                </tr>
                <tr>
                    <td>{this.props.productsList[0].name}</td>
                    {tableValues}
                </tr>
            </table>
        )
    }
}

export default Tabela