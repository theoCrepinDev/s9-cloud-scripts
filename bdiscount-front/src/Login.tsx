import { useState } from "react";
import "./App.css";

export function Login(props: { login: (form: { email: string; password: string }) => void }) {
    const [loginFrom, setLoginFrom] = useState({
        email: '',
        password: ''
    });

    function handleEmailChange(event: any)  {
        setLoginFrom({ ...loginFrom, email: event.target.value })
    }

    function handlePasswordChange(event: any)  {
        setLoginFrom({ ...loginFrom, password: event.target.value })
    }
    

  return (

    <>
    <div>
      <h1>Login</h1>
      <div className="form">
        <label >
            Email: 
            <input type="text" onChange={handleEmailChange} />
        </label>
        <label >
            Password: 
            <input type="password" onChange={handlePasswordChange} />
        </label>
        <button onClick={() => props.login(loginFrom)}>Login</button>
      </div>
    </div>
    
    </>
  );
}
