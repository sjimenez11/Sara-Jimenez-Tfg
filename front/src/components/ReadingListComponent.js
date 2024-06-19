import React, { useEffect, useState } from 'react'
import ReadingListService from '../services/ReadingListService';

export const ReadingListComponent = () => {
    const [readingLists, setReadingLists] = useState([]);
    const userId = localStorage.getItem('userId'); 

    useEffect(() => {
        const fetchReadingLists = async () => {
            try {
                const response = await ReadingListService.getAllReadingListsByUserId(userId); 
                setReadingLists(response.data); 
            } catch (error) {
                console.error('Error al obtener listas de lectura: ', error);
            }
        };

        if (userId) {
            fetchReadingLists();
        } else {
            console.error('No se encontró el userId en el almacenamiento local.');
        }
    }, [userId]);

    return (
        <div>
            <h2>Mis Listas de Lectura</h2>
            {readingLists.length > 0 ? (
                <ul>
                    {readingLists.map((list) => (
                        <li key={list.id}>
                            <h3>{list.name}</h3>
                            <p>{list.description}</p>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No hay listas de lectura disponibles.</p>
            )}
        </div>
    );
}


export default ReadingListComponent;