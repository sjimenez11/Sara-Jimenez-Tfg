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
        <div className="container mt-5 d-flex justify-content-center">
            <div className="form-container">
                <h2 className="text-center">Iniciar Sesión</h2>
                <form onSubmit={handleLogin} className="mt-4">
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
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
                    {error && <p className="text-danger mt-3">{error}</p>}
                    <div className="d-flex justify-content-center mt-3">
                        <button type="submit" className="btn btn-primary">Iniciar Sesión</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Login;
