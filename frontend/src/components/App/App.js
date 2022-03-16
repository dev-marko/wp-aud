import './App.css';
import React from "react";
import {Component} from "react";
import Manufacturers from "../Manufacturers/manufacturers";
import Categories from "../Categories/categories";
import Products from "../Products/ProductList/products";
import Header from "../Header/header";
import EShopService from "../../repository/eshopRepository";
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            manufacturers : [],
            products : [],
            categories : []
        }
    }

    render() {
        return (
            <Router>
                <main>
                    <Header/>
                    <div className={"container"}>
                        <Routes>
                            <Route path={"/manufacturers"} element={<Manufacturers manufacturers = {this.state.manufacturers}/>} exact />
                            <Route path={"/categories"} element={<Categories categories = {this.state.categories}/>} exact />
                            <Route path={"/products"} element={<Products products = {this.state.products}/>} exact />
                            <Route path="/" element={<Navigate replace to="/products" />} />
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    // Mora nekako da se povika komponentata
    loadManufacturers = () => {
        EShopService.fetchManufacturers()
            .then((data) => {
                this.setState({
                    manufacturers: data.data
                })
            });
    }

    loadCategories = () => {
        EShopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadProducts = () => {
        EShopService.fetchProducts()
            .then((data) => {
                this.setState({
                    products: data.data
                })
            });
    }

    // Mozeme i ako sakame da iskoristime prethodna sostojba

    // loadProducts = () => {
    //     EShopService.fetchProducts()
    //         .then((data) => {
    //             this.setState((prev) => {
    //                 products: prev.products.append(data.data)
    //             })
    //         });
    // }

    componentDidMount() {
        this.loadManufacturers();
        this.loadProducts();
        this.loadCategories();
    }


}

export default App;
