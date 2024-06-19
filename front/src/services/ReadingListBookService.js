import axios from 'axios'

const BOOK_BASE_REST_API_URL = "http://localhost:8080/api/books"

class ReadingListBookService{

    saveReadingListBook(readingList){
        return axios.post(BOOK_BASE_REST_API_URL + "/create", readingList)
    }
}

export default new ReadingListBookService();