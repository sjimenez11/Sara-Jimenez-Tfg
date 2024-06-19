import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import ReadingListService from '../services/ReadingListService';

export const AddReadingListComponent = () => {

  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  const saveReadingList = (e) => {
    e.preventDefault();
    const userId = localStorage.getItem('userId');
    const readingList = {userId, name, description};
    ReadingListService.saveReadingList(readingList).then((response) => {
      console.log(response.data);
      navigate('/readingList')
    }).catch(error => {
      console.log(error);
    });
  }

  return (
    
    <div className='container'>
      <div className='row'>
        <div className='card col-md-6 offset-md-3'>
          <h2>Crear lista de lectura</h2>
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label className='form-label'>Nombre</label>
                <input
                  type='text'
                  placeholder='Ingrese un nombre'
                  className='form-control'
                  value={ name }
                  onChange={ (e) => setName(e.target.value) }
                />
              </div><div className='form-group mb-2'>
                <label className='form-label'>Descripción</label>
                <input
                  type='text'
                  placeholder='Ingrese una descripcións'
                  className='form-control'
                  value={ description }
                  onChange={ (e) => setDescription(e.target.value) }
                />
              </div>
              <button className='btn btn-success' onClick={ (e) => saveReadingList(e) }>Guardar</button>
              &nbsp;&nbsp;
              <Link to='/readingList' className='btn btn-danger'>Cancelar</Link>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
  
}

export default AddReadingListComponent;