
import './App.css';
import ListBooksComponent from './components/BooksComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AddBookComponent from './components/AddBookComponent';
import SignUp from './components/SignUp';
import LoginComponent from './components/LoginComponent';
import ReadingListComponent from './components/ReadingListComponent';
import AddReadingListComponent from './components/AddReadingListComponent';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <HeaderComponent/>
        <div className='page-container'>
          <div className='wrap-content'>
            <Routes>
                <Route exact path="/" element={<ListBooksComponent/>}></Route>
                <Route path="/books" element={<ListBooksComponent/>}></Route>
                <Route path="/add-book" element={<AddBookComponent/>}></Route>
                <Route path="/edit-book/:id" element={<AddBookComponent/>}></Route>
                <Route path="/signup" element={<SignUp/>}></Route>
                <Route path="/login" element={<LoginComponent/>}></Route>
                <Route path="/readingList" element={<ReadingListComponent/>}></Route>
            </Routes>
          </div>
        </div>
        <FooterComponent/>
      </BrowserRouter>
    </div>
  );
}

export default App;
