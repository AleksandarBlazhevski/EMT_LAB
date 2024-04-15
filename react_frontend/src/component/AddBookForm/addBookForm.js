import React from "react";
import {useHistory} from "react-router-dom";

const BookForm = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: props.categories[0],
        authorId: 1,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const authorId = formData.authorId;
        const availableCopies = formData.availableCopies;
        props.onAddBook(name, category, authorId, availableCopies);
        history.push("/books")
    }
    return(
        <div>
            <form onSubmit={onFormSubmit}>
                <div className={"form-group"}>
                    <label htmlFor={"name"}>Book name</label>
                    <input type={"text"}
                           className={"form-control"}
                           id={"name"}
                           name={"name"}
                           required
                           placeholder={"Book name..."}
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

export default BookForm;