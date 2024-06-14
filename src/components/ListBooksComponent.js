import {useState, useEffect} from 'react';
import BookService from '../services/BookService';

export const ListBooksComponent = () => {
    const [books, setBooks] = useState([]);

    useEffect(() =>{
        //llamamos al método getAllBooks para que después los datos de la respuesta se metan en setBooks
        BookService.getAllBooks().then(response =>{
            setBooks(response.data);
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }, [])

  return (
    <div className='container'>
        <h2 className='text-center'>Books</h2>
        <table className='table table-bordered table-striped'>
            <thead>
                <th>Id</th>
                <th>Título</th>
                <th>Autor</th>
            </thead>
            <tbody>
                {
                    books.map(
                        libro =>
                            <tr key={libro.id}>
                               <td>{libro.title}</td> 
                               <td>{libro.author}</td> 
                            </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListBooksComponent;
