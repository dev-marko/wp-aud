import React from "react";

const manufacturers = (props) => {

    return (
      <div className={"container mm-4 mt-5"}>
          {
              !props.manufacturers.length && (<div>loading...</div>)
          }
          <div className={"row"}>
              <div className={"table-responsive"}>
                  <table className={"table table-striped"}>
                      <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Address</th>
                        </tr>
                      </thead>
                      <tbody>
                      {props.manufacturers.map((item) => {
                          return (
                              <tr>
                                  <td>{item.name}</td>
                                  <td>{item.address}</td>
                              </tr>
                          )
                      })}
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
    );
}

export default manufacturers;