import { useNavigate } from "react-router-dom";
import UserService from "../services/UserService";
import { useState } from "react";

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');    
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await UserService.login(email, password);
            console.log(response.data); 
            
            const userId = response.data.info.id;

            localStorage.setItem('userId', userId);

            navigate('/books');
        } catch (error) {
            console.error('Error al iniciar sesión: ', error);
            setError('Credenciales incorrectas. Por favor, intenta nuevamente.');
        }
    };

    return (
        <div>
            <h2>Iniciar Sesión</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Email: </label>
                    <input type='text' value={email} onChange={(e) => setEmail(e.target.value)} required />
                </div>
                <div>
                    <label>Contraseña: </label>
                    <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <button type='submit'>Iniciar Sesión</button>
            </form>
        </div>
    );
};

export default Login;