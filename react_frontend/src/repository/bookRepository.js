import axios from '../custom-axios/axios';

const bookService = {
    fetchBooks : () => {
        return axios.get("/books");
    },
    fetchCategories: () =>{
        return axios.get("/books/categories")
    },
    deleteBook : (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook : (name, category, authorId, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies
        })
    },
    rentBook : (id) => {
        return axios.post(`/books/rent/${id}`)
    },
    editBook : (id, name, category, authorId, availableCopies) => {
        console.log("id: " + id + "name: " + name + "category: " + category + "authorId " + authorId + "copies: " + availableCopies)
        return axios.post(`/books/update/${id}`, {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies
        })
    },
    getBookById : (id) => {
        return axios.get(`/books/${id}`);
    }
}


export default bookService;