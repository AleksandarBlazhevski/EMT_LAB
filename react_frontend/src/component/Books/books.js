import React from "react";


const books = (props) => {
    return(
      <div className={"container mm-4 mt-5"}>
          <div className={"row"}>
              <div className={"row"}>
                  <table className={"table table-striped"}>
                      <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Author</th>
                            <th scope={"col"}>Category</th>
                            <th scope={"col"}>Available copies</th>
                            <th scope={"col"}>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                      {props.books.map((item) =>{
                          return (
                              <tr>
                                  <td>{item.name}</td>
                                  <td>{item.author.name}</td>
                                  <td>{item.category}</td>
                                  <td>{item.availableCopies}</td>
                                  <td className={"text-right"}>
                                      <button
                                          title={"Delete"}
                                          className={"btn btn-danger"}
                                          onClick={() => props.onDelete(item.id)}>
                                          Delete
                                      </button>
                                      <a
                                          title={"Edit"}
                                          onClick={() => props.onSelectBook(item.id)}
                                          href={`/books/edit/${item.id}`}
                                          className={"btn btn-secondary"}>
                                          Edit
                                      </a>
                                      <button
                                          title={"Rent"}
                                          className={"btn btn-info"}
                                          onClick={() => props.onRent(item.id)}>
                                          Rent
                                      </button>
                                  </td>
                              </tr>
                          )
                      })}
                      </tbody>
                  </table>
                  <a
                      href={"/books/add"}
                      title={"Add"}
                      className={"btn btn-secondary"}>
                      Add book
                  </a>
              </div>
          </div>
      </div>
    );
}

export default books;