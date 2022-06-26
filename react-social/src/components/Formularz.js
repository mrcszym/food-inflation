import { Component } from 'react';

class Formularz extends Component {

    static defaultProps = {
        products: [{name: 'Chleb', trueName: 'chleb_1000g'}, 
        {name: 'Bułka kajzerka', trueName: 'bulka kajzerka 50g'},
        {name: 'Piwo żywiec puszka', trueName: 'piwo zywiec puszka'},
        {name: 'Parówki berlinki opak. 250g', trueName: 'parowki berlinki opak. 250g'}
    ],
        dateStart: ['2015-01', '2015-02', '2015-03', '2015-04', '2015-05', '2015-06', '2015-07',
            '2015-08', '2015-09', '2015-10', '2015-11', '2015-12',
            '2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07',
            '2016-08', '2016-09', '2016-10', '2016-11', '2016-12',
            '2017-01', '2017-02', '2017-03', '2017-04', '2017-05', '2017-06', '2017-07',
            '2017-08', '2017-09', '2017-10', '2017-11', '2017-12',
            '2018-01', '2018-02', '2018-03', '2018-04', '2018-05', '2018-06', '2018-07',
            '2018-08', '2018-09', '2018-10', '2018-11', '2018-12',
            '2019-01', '2019-02', '2019-03', '2019-04', '2019-05', '2019-06', '2019-07',
            '2019-08', '2019-09', '2019-10', '2019-11', '2019-12',
            '2020-01', '2020-02', '2020-03', '2020-04', '2020-05', '2020-06', '2020-07',
            '2020-08', '2020-09', '2020-10', '2020-11', '2020-12',
            '2021-01', '2021-02', '2021-03', '2021-04', '2021-05', '2021-06', '2021-07',
            '2021-08', '2021-09', '2021-10', '2021-11', '2021-12',
            '2022-01', '2022-02', '2022-03', '2022-04', '2022-05'],
        dateEnd: ['2015-01', '2015-02', '2015-03', '2015-04', '2015-05', '2015-06', '2015-07',
            '2015-08', '2015-09', '2015-10', '2015-11', '2015-12',
            '2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07',
            '2016-08', '2016-09', '2016-10', '2016-11', '2016-12',
            '2017-01', '2017-02', '2017-03', '2017-04', '2017-05', '2017-06', '2017-07',
            '2017-08', '2017-09', '2017-10', '2017-11', '2017-12',
            '2018-01', '2018-02', '2018-03', '2018-04', '2018-05', '2018-06', '2018-07',
            '2018-08', '2018-09', '2018-10', '2018-11', '2018-12',
            '2019-01', '2019-02', '2019-03', '2019-04', '2019-05', '2019-06', '2019-07',
            '2019-08', '2019-09', '2019-10', '2019-11', '2019-12',
            '2020-01', '2020-02', '2020-03', '2020-04', '2020-05', '2020-06', '2020-07',
            '2020-08', '2020-09', '2020-10', '2020-11', '2020-12',
            '2021-01', '2021-02', '2021-03', '2021-04', '2021-05', '2021-06', '2021-07',
            '2021-08', '2021-09', '2021-10', '2021-11', '2021-12',
            '2022-01', '2022-02', '2022-03', '2022-04', '2022-05']
    }

    handleSubmit(e) {
        this.setState({
            newDane: {
                product: this.refs.product.value,
                dateStart: this.refs.datyStart.value,
                dateEnd: this.refs.datyKoniec.value
            }
        }, function () {
            this.props.addDane(this.state.newDane);
        })
        e.preventDefault();
    }

    render() {
        let dateStartOptions = this.props.dateStart.map(dataS => {
            return <option key={dataS} value={dataS}>{dataS}</option>
        });
        let dateEndOptions = this.props.dateEnd.map(dataE => {
            return <option key={dataE} value={dataE}>{dataE}</option>
        });
        let productsOptions = this.props.products.map(product => {
            return <option key={product.trueName} value={product.trueName}>{product.name}</option>
        });
        return (
            <div>
                <form onSubmit={this.handleSubmit.bind(this)}>
                    <div>
                        <label>Wybierz produkt:</label>
                        <select ref="product">
                            {productsOptions}
                        </select>
                    </div>
                    <div>
                        <label>Data początkowa</label>
                        <select ref="datyStart">
                            {dateStartOptions}
                        </select>
                    </div>
                    <div>
                        <label>Data końcowa</label>
                        <select ref="datyKoniec">
                            {dateEndOptions}
                        </select>
                    </div>
                    <input type="submit" value="Pokaż wykres!" />
                </form>
            </div>
        )
    }
}

export default Formularz;