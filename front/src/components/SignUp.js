import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import UserService from '../services/UserService';


export const SignUp = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [repeatPassword, setRepeatPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSingUp = async (e) => {
        e.preventDefault();

         if (password !== repeatPassword) {
            setError('Las contraseñas no coinciden.');
            return;
        }

        try{
            const user = {username, password, repeatPassword};
            const response = await UserService.saveUser(user);

            console.log(response.data);

            navigate('/login');
        }catch(error){
            console.error('Error al registrar usuario: ', error);
            setError('Error al registrar usuario. Por favor, intenta nuevamente.');
        }
    }

    return (
        <div>
            <h2>Registro</h2>
            <form onSubmit={handleSingUp}>
                <div>
                    <label>Usuario: </label>
                    <input type='text' value={username} onChange={(e) => setUsername(e.target.value)} required></input>
                </div>
                <div>
                    <label>Contraseña: </label>
                    <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} required></input>
                </div>
                <div>
                    <label>Repite contraseña: </label>
                    <input type='password' value={repeatPassword} onChange={(e) => setRepeatPassword(e.target.value)} required></input>
                </div>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <button type='submit'>Registrarse</button>
            </form>
        </div>
    )
}


export default SignUp;