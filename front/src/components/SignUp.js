import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UserService from '../services/UserService';

export const SignUp = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [repeatPassword, setRepeatPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSignUp = async (e) => {
        e.preventDefault();

        if (password !== repeatPassword) {
            setError('Las contraseñas no coinciden.');
            return;
        }

        try {
            const user = { username, password };
            const response = await UserService.saveUser(user);

            console.log(response.data);

            navigate('/login');
        } catch (error) {
            console.error('Error al registrar usuario: ', error);
            setError('Error al registrar usuario. Por favor, intenta nuevamente.');
        }
    };

    return (
        <div className="container mt-5 d-flex justify-content-center">
            <div className="form-container">
                <h2 className="text-center">Registro</h2>
                <form onSubmit={handleSignUp} className="mt-4">
                    <div className="form-group">
                        <label>Usuario:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Contraseña:</label>
                        <input
                            type="password"
                            className="form-control"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group mt-3">
                        <label>Repite contraseña:</label>
                        <input
                            type="password"
                            className="form-control"
                            value={repeatPassword}
                            onChange={(e) => setRepeatPassword(e.target.value)}
                            required
                        />
                    </div>
                    {error && <p className="text-danger mt-3">{error}</p>}
                    <div className="d-flex justify-content-center mt-3">
                        <button type="submit" className="btn btn-primary">Registrarse</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default SignUp;
