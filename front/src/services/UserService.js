import axios from 'axios'

const BOOK_BASE_REST_API_URL = "http://localhost:8080/api/users"

class UserService{
    saveUser(user){
        return axios.post(BOOK_BASE_REST_API_URL + "/register", user)
    }

    login(email, password) {
        return axios.post(BOOK_BASE_REST_API_URL + "/login", { email, password });
    }
    
}

export default new UserService();