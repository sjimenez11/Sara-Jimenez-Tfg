import React, { useEffect, useState } from 'react'
import BookService from '../services/BookService';
import { Link, useNavigate, useParams } from 'react-router-dom';

export const AddBookComponent = () => {

  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [synopsis, setSynopsis] = useState('');
  const navigate = useNavigate();
  const {id} = useParams()

  const saveBook = (e) => {
    e.preventDefault();
    const book = {title, author, synopsis};
    BookService.saveBook(book).then((response) => {
      console.log(response.data);
      navigate('/books')
    }).catch(error => {
      console.log(error);
    });
  }

  useEffect(() => {
    BookService.getBookById(id).then((response) => {
      setTitle(response.data.title);
      setAuthor(response.data.author);
      setSynopsis(response.data.synopsis);
    }).catch(error => {
    console.log(error);
    })
  })

  return (
    
    <div className='container'>
      <div className='row'>
        <div className='card col-md-6 offset-md-3'>
          <h2>Registro de clientes</h2>
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label className='form-label'>Titulo</label>
                <input
                  type='text'
                  placeholder='Ingrese titulo'
                  className='form-control'
                  value={ title }
                  onChange={ (e) => setTitle(e.target.value) }
                />
              </div><div className='form-group mb-2'>
                <label className='form-label'>Autor</label>
                <input
                  type='text'
                  placeholder='Ingrese autor'
                  className='form-control'
                  value={ author }
                  onChange={ (e) => setAuthor(e.target.value) }
                />
              </div>
              <div className='form-group mb-2'>
                <label className='form-label'>Sinopsis</label>
                <input
                  type='text'
                  placeholder='Ingrese sinopsis'
                  className='form-control'
                  value={ synopsis }
                  onChange={ (e) => setSynopsis(e.target.value) }
                />
              </div>
              <button className='btn btn-success' onClick={ (e) => saveBook(e) }>Guardar</button>
              &nbsp;&nbsp;
              <Link to='/books' className='btn btn-danger'>Cancelar</Link>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
  
}

export default AddBookComponent;