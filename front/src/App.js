
import './App.css';
import ListBooksComponent from './components/ListBooksComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AddBookComponent from './components/AddBookComponent';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
          <Route exact path="/" element={<ListBooksComponent/>}></Route>
          <Route path="/books" element={<ListBooksComponent/>}></Route>
          <Route path="/add-book" element={<AddBookComponent/>}></Route>
          <Route path="/edit-book/:id" element={<AddBookComponent/>}></Route>
        </Routes>
        <FooterComponent/>
      </BrowserRouter>
    </div>
  );
}

export default App;
