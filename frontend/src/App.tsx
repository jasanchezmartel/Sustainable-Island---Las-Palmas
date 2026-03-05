import { BrowserRouter, Route, Routes } from "react-router"
import { Login } from "./pages/Login/Login"
import { LandingPage } from "./pages/landingPage"

function App() {
  

  return (
    <>
    <BrowserRouter>
    <Routes>
      <Route path='/login' element={<Login/>}/>
    </Routes>
    </BrowserRouter>
     <LandingPage />
    </>
  )
}

export default App
