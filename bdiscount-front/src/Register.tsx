import axios from "axios";
import { useState } from "react";

export function Register(props: { url: string }) {
  const [registerFrom, setRegisterForm] = useState({
    email: "",
    password: "",
    name: "",
    phone: "",
    address: "",
  });

  function handleEmailChange(event: any) {
    setRegisterForm({ ...registerFrom, email: event.target.value });
  }

  function handlePasswordChange(event: any) {
    setRegisterForm({ ...registerFrom, password: event.target.value });
  }

  function handleNameChange(event: any) {
    setRegisterForm({ ...registerFrom, name: event.target.value });
  }

  function handlePhoneChange(event: any) {
    setRegisterForm({ ...registerFrom, phone: event.target.value });
  }

  function handleAddressChange(event: any) {
    setRegisterForm({ ...registerFrom, address: event.target.value });
  }

  function register() {
    console.log(props.url);
    axios.post(props.url, registerFrom).then((res) => {
      console.log(res);
      window.location.href = "/";
    });
  }

  return (
    <>
      <div>
        <h1>Login</h1>
        <div className="form">
          <label>
            name:
            <input type="text" onChange={handleNameChange} />
          </label>
          <label>
            phone:
            <input type="text" onChange={handlePhoneChange} />
          </label>
          <label>
            Email:
            <input type="text" onChange={handleEmailChange} />
          </label>
          <label>
            Password:
            <input type="password" onChange={handlePasswordChange} />
          </label>
          <label>
            address:
            <input type="text" onChange={handleAddressChange} />
          </label>
          <button onClick={() => register()}>Register</button>
          <button onClick={() => (window.location.href = "/")}> login </button>
        </div>
      </div>
    </>
  );
}
