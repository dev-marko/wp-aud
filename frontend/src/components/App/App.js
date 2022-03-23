import './App.css';
import React from "react";
import {Component} from "react";
import Manufacturers from "../Manufacturers/manufacturers";
import Categories from "../Categories/categories";
import Products from "../Products/ProductList/products";
import Header from "../Header/header";
import ProductAdd from "../Products/ProductAdd/productAdd";
import ProductEdit from "../Products/ProductEdit/productEdit";
import EShopService from "../../repository/eshopRepository";
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            manufacturers: [],
            products: [],
            categories: [],
            selectedProduct: {}
        }
    }

    render() {
        return (
            <Router>
                <main>
                    <Header/>
                    <div className={"container"}>
                        <Routes>
                            <Route path={"/manufacturers"}
                                   element={<Manufacturers manufacturers={this.state.manufacturers}/>} exact/>
                            <Route path={"/categories"}
                                   element={<Categories categories={this.state.categories}/>}
                                   exact/>
                            <Route path={"/products/add"}
                                   element={<ProductAdd categories={this.state.categories}
                                                        manufacturers={this.state.manufacturers}
                                                        onAddProduct={this.addProduct}/>} exact/>
                            <Route path={"/products/edit/:id"}
                                   element={<ProductEdit categories={this.state.categories}
                                                         manufacturers={this.state.manufacturers}
                                                         onEditProduct={this.editProduct}
                                                         product={this.state.selectedProduct}/>} exact/>
                            <Route path={"/products"}
                                   element={<Products products={this.state.products}
                                                      onDelete={this.deleteProduct}
                                                      onEdit={this.getProduct}/>}
                                   exact/>
                            <Route path="/" element={<Navigate replace to="/products"/>}/>
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

    deleteProduct = (id) => {
        EShopService.deleteProduct(id)
            .then(() => {
                this.loadProducts();
            })
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

    addProduct = (name, price, quantity, category, manufacturer) => {
        EShopService.addProduct(name, price, quantity, category, manufacturer)
            .then(() => {
                this.loadProducts();
            });
    }

    getProduct = (id) => {
        EShopService.getProduct(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                })
            });
    }

    editProduct = (id, name, price, quantity, category, manufacturer) => {
        EShopService.editProduct(id, name, price, quantity, category, manufacturer)
            .then(() => {
                this.loadProducts();
            });
    }

    componentDidMount() {
        this.loadManufacturers();
        this.loadProducts();
        this.loadCategories();
    }


}

export default App;
