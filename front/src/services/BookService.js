import axios from 'axios'

const BOOK_BASE_REST_API_URL = "http://localhost:8080/api/books"

class BookService{

    getAllBooks(){
        return axios.get(BOOK_BASE_REST_API_URL);
    }

    saveBook(book){
        return axios.post(BOOK_BASE_REST_API_URL + "/create", book)
    }
    
    getBookById(bookId){
        return axios.get(BOOK_BASE_REST_API_URL + "/" + bookId)
    }
}

export default new BookService();