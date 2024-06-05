import axios from "axios";

const BOOK_BASE_REST_API_URL = "http://localhost:8080/api/books";

class BookService{

    getAllBooks(){
        return axios.get(BOOK_BASE_REST_API_URL);
    }

}

export default new BookService();