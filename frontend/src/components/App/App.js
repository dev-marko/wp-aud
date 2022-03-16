import './App.css';
import React from "react";
import {Component} from "react";
import Manufacturers from "../Manufacturers/manufacturers";
import EShopService from "../../repository/eshopRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            manufacturers : []
        }
    }

    render() {
        return (
            <div>
                <Manufacturers manufacturers = {this.state.manufacturers} />
            </div>
        );
    }

    // Mora nekako da se povika komponentata

    loadManufacturers = () => {
        EShopService.fetchManufacturers()
            .then((data) => {
                this.setState({
                    manufacturers : data.data
                })
            });
    }

    componentDidMount() {
        this.loadManufacturers();
    }


}

export default App;
