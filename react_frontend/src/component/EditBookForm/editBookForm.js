import React from "react";
import {useHistory, useParams} from "react-router-dom";


const EditBookForm = (props) => {


    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        id: props.selectedBook.id,
        name: props.selectedBook.name,
        category: props.selectedBook.category,
        authorId: props.selectedBook.authorId,
        availableCopies: props.selectedBook.availableCopies
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const id = formData.id;
        const name = formData.name;
        const category = formData.category;
        const authorId = formData.authorId;
        const availableCopies = formData.availableCopies;
        props.onEditBook(id, name, category, authorId, availableCopies);
        history.push("/books")
    }
    return(
        <div>
            <h3>Edit book</h3>
            {console.log(props)}
            <form onSubmit={onFormSubmit}>
                <div className={"form-group"}>
                    <label htmlFor={"id"}>Book id</label>
                    <input type={"text"}
                           className={"form-control"}
                           id={"id"}
                           name={"id"}
                           required
                           onChange={handleChange}
                    />
                </div>
                <div className={"form-group"}>
                    <label htmlFor={"name"}>Book name</label>
                    <input type={"text"}
                           className={"form-control"}
                           id={"name"}
                           name={"name"}
                           required
                           placeholder={"Book name ..."}
                           onChange={handleChange}
                    />
                </div>
                <div className={"form-group"}>
                    <label htmlFor={"category"}>Category</label>
                    <select className={"form-control"}
                            id={"category"}
                            name={"category"}
                            required
                            onChange={handleChange}>
                        {props.categories.map((item) =>
                            <option value={item}>{item}</option>
                        )}
                    </select>
                </div>
                <div className={"form-group"}>
                    <label htmlFor={"author"}>Author</label>
                    <select className={"form-control"}
                            id={"author"}
                            name={"authorId"}
                            required
                            onChange={handleChange}>
                        {props.authors.map((item) =>
                            <option value={item.id}>{item.name}</option>
                        )}
                    </select>
                </div>
                <div className={"form-group"}>
                    <label htmlFor={"availableCopies"}>Available copies</label>
                    <input type={"number"}
                           className={"form-control"}
                           id={"availableCopies"}
                           name={"availableCopies"}
                           required
                           placeholder={"0"}
                           step={1}
                           onChange={handleChange}/>
                </div>
                <button id={"submit"} type={"submit"} className={"btn btn-primary"}>Submit</button>
            </form>
        </div>
    )
}

export default EditBookForm;