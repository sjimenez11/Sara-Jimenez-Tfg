import React, { useEffect, useState } from 'react';
import BookService from '../services/BookService';
import { Link } from 'react-router-dom';

export const ListBooksComponent = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        BookService.getAllBooks()
            .then(response => {
                setBooks(response.data);
            })
            .catch(error => {
                console.error('Error al obtener los libros:', error);
            });
    }, []);

    return (
        <div className='container'>
            <h2 className='text-center'>Libros</h2>
            <div className='row'>
                {books.map(book => (
                    <div className='col-md-6 mb-4' key={book.id}>
                        <div className='card'>
                            <div className='card-body'>
                                <h2 className='card-title'>{book.title}</h2>
                                <h5 className='card-subtitle mb-2 text-muted'>{book.author}</h5>
                                <p className='card-text'>{book.synopsis}</p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="d-flex justify-content-center mt-3">
            <Link to='/add-book' className='btn btn-primary mb-2'>Agregar un libro</Link>
            </div>
        </div>
    );
};

export default ListBooksComponent;
