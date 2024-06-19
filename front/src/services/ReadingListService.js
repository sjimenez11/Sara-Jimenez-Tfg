import axios from 'axios'

const BOOK_BASE_REST_API_URL = "http://localhost:8080/api/readingList"

class ReadingListService{

    getAllReadingListsByUserId(userId){
        return axios.get(BOOK_BASE_REST_API_URL + "/" + userId);
    }

    saveReadingList(readingList){
        return axios.post(BOOK_BASE_REST_API_URL, readingList)
    }

    addBooksToReadingList(){
        
    }
}

export default new ReadingListService();