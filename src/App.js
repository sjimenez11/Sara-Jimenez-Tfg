import '../front/App.css';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListBooksComponent from './components/ListBooksComponent';

function App() {
  return (
    <div>
      <HeaderComponent/>
      <ListBooksComponent/>
      <FooterComponent/>
    </div>
  );
}

export default App;
