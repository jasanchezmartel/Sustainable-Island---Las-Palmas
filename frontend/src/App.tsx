import { BrowserRouter, Route, Routes } from "react-router"
import { Login } from "./pages/Login/Login"
import { LandingPage } from "./pages/landingPage/landingPage"
import { Signup } from "./pages/Signup/Signup"
import { NavBar } from "./components/NavBar/NavBar"

function App() {
  return (
    <>
   
      <BrowserRouter>
        <Routes>
       <Route path='/navbar' element={<NavBar/>}></Route>
          <Route path='/' element={<LandingPage />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<Signup />} />
        </Routes>
      </BrowserRouter>

    </>
  )
}

export default App
