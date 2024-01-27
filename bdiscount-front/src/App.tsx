import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Register } from './Register';
import { Home } from './Home';

function App() {

  const backUrl = 'localhost:31380/authentication/api/auth/'



  return (
    <>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Home url={backUrl} />} />
      <Route path='/register' element={<Register url={backUrl + 'register'} />} />
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
