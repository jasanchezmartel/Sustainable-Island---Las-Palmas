import { BrowserRouter, Route, Routes } from "react-router-dom"
import { LandingPage } from "./pages/landingPage/landingPage"
import { MissionsPage } from "./pages/MissionsPage/missionsPage"

function App() {
  

  return (
    <BrowserRouter>
     <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/missionsPage" element={<MissionsPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
