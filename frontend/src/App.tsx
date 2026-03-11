import { BrowserRouter, Route, Routes } from "react-router"
import { Login } from "./pages/Login/Login"
import { LandingPage } from "./pages/landingPage"
import { Signup } from "./pages/Signup/Signup"
import { NavBar } from "./components/NavBar/NavBar"
import { Pet } from './pages/Pet/Pet'


function App() {
  return (
    <>

      <BrowserRouter>
        <Routes>
          <Route path='/choosepet' element={<Pet />} />
          <Route path='/navbar' element={<NavBar />} />
          <Route path='/' element={<LandingPage />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<Signup />} />
          <Route path='/choosepet/' element={<Pet />} />
        </Routes>
      </BrowserRouter>

    </>
  )
}

export default App
