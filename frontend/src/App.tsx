import { BrowserRouter, Route, Routes } from "react-router"
import { Login } from "./pages/Login/Login"
import { LandingPage } from "./pages/LandingPage/landingPage"
import { Signup } from "./pages/Signup/Signup"
import { NavBar } from "./components/NavBar/NavBar"
import { UserProfile } from "./pages/User Profile/userProfile"
import { MissionsPage } from "./pages/Missions/MissionsPage"

function App() {
  return (
    <>
   
      <BrowserRouter>
        <Routes>
       <Route path='/navbar' element={<NavBar/>}></Route>
          <Route path='/' element={<LandingPage />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<Signup />} />
          <Route path='/userProfile' element={<UserProfile />} />
          <Route path='/missions' element={<MissionsPage />} />
        </Routes>
      </BrowserRouter>

    </>
  )
}

export default App
