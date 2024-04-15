import './App.css';
import React, {Component} from "react";
import Books from "../Books/books";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import bookRepository from "../../repository/bookRepository";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import AddBookForm from "../AddBookForm/addBookForm";
import authorRepository from "../../repository/authorRepository";
import EditBookForm from "../EditBookForm/editBookForm";

class App extends Component{
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }
    render() {
        return(
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route exact path={"/books/add"} render={() => <AddBookForm
                            categories={this.state.categories}
                            authors={this.state.authors}
                            onAddBook={this.addBook}/>
                        }/>
                        <Route exact path={"/books/edit/:id"} render={() => <EditBookForm
                            categories={this.state.categories}
                            authors={this.state.authors}
                            selectedBook={this.state.selectedBook}
                            onEditBook={this.editBook}
                        />
                        }/>
                        <Route exact path={["/", "/books"]} render={() => <Books
                            books={this.state.books}
                            onDelete={this.deleteBooks}
                            onRent={this.rentBook}
                            onSelectBook={this.getBookById}/>
                        }/>

                        <Route exact path={"/categories"} render={() => <Categories categories={this.state.categories}/>}/>

                    </div>
                </main>
            </Router>
        )
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }
    loadBooks = () =>{
        bookRepository.fetchBooks()
            .then((data) =>{
                this.setState({
                    books: data.data
                })
            });
    }
    loadCategories = () =>{
        bookRepository.fetchCategories()
            .then((data) =>{
                this.setState({
                    categories: data.data
                })
            });
    }
    loadAuthors = () => {
        authorRepository.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            })
    }
    deleteBooks = (id) => {
        bookRepository.deleteBook(id).then(() => this.loadBooks())
    }
    addBook = (name, category, authorId, availableCopies) => {
        bookRepository.addBook(name, category, authorId, availableCopies)
            .then(() => this.loadBooks()
            )}
    rentBook = (id) => {
        bookRepository.rentBook(id)
            .then(() => this.loadBooks())
    }
    editBook = (id, name, category, authorId, availableCopies)=> {
        bookRepository.editBook(id, name, category, authorId, availableCopies)
            .then(() => this.loadBooks())
    }
    getBookById = (id) => {
        console.log("ASDASD")
        bookRepository.getBookById(id).then((data) => {
            this.setState({
                selectedBook : data.data
            })
            console.log(this.state.selectedBook)
        })
    }
}

export default App;
