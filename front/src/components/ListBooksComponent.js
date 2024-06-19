import React, { useEffect, useState } from 'react'
import BookService from '../services/BookService';
import { Link } from 'react-router-dom';

export const ListBooksComponent = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        BookService.getAllBooks().then(response => {
            setBooks(response.data);
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }, []);

    return (
        <div className='container'>
            <h2 className='text-center'>Libros</h2>
            <Link to='/add-book' className='btn btn-primary mb-2'>Agregar un libro</Link>
            <table className='table table-bordered table-striped'> 
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Autor</th>
                        <th>Sinopsis</th>
                        <th>Actualizar</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        books.map(
                            book =>
                                <tr key={ book.id }>
                                    <td>{ book.title }</td>
                                    <td>{ book.author }</td>
                                    <td>{ book.synopsis }</td>
                                    <td><Link to={ `/edit-book/${book.id}` } className='btn btn-primary mb-2'>Actualizar</Link></td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListBooksComponent;