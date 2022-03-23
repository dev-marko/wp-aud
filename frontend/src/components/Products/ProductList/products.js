import React, {Component} from "react";
import ReactPaginate from "react-paginate";
import {Link} from "react-router-dom";
import ProductTerm from "../ProductTerm/productTerm";


class Products extends Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 2
        }
    }

    render() {

        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.products.length / this.state.size);
        const products = this.getProductsPage(offset, nextPageOffset);

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Price</th>
                                <th scope={"col"}>Quantity</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Manufacturer</th>
                            </tr>
                            </thead>
                            <tbody>
                            {products}
                            </tbody>
                        </table>
                    </div>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn w-100 btn-dark"} to={"/products/add"}>Add new product</Link>
                            </div>
                        </div>
                    </div>
                    <ReactPaginate previousLabel={"Previous"}
                                   previousLinkClassName={"page-link"}
                                   nextLabel={"Next"}
                                   nextLinkClassName={"page-link"}
                                   breakLabel={<a href="/#">...</a>}
                                   breakClassName={"page-item"}
                                   pageClassName={"mx-1 page-item"}
                                   pageLinkClassName={"page-link"}
                                   pageCount={pageCount}
                                   marginPagesDisplayed={2}
                                   pageRangeDisplayed={5}
                                   onPageChange={this.handlePageClick}
                                   containerClassName={"pagination m-4 justify-content-center"}
                                   activeClassName={"active"}/>
                </div>
            </div>
        );

    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getProductsPage = (offset, nextPageOffset) => {
        return this.props.products.map((term) => {
            return (
                <ProductTerm
                    term={term}
                    onDelete={this.props.onDelete}
                    onEdit={this.props.onEdit}/>
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

}

export default Products;