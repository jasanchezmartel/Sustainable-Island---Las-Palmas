import { useEffect, useState } from "react"
import { AuthContext } from "./AuthContext"
import type { UserData } from "../types/userType"

interface AuthContextProviderInterface {
    children: React.ReactNode
}

export const AuthContextProvider = ({ children }: AuthContextProviderInterface) => {
    const [userData, setUserData] = useState<UserData | null>(null)

    useEffect(() =>{
        function getLocalUserstate() {
            if (localStorage.getItem('userData')) {
                const json = JSON.parse(localStorage.getItem('userData')!)
                setUserData(json)
            }
        }
        getLocalUserstate()
    }, [])

    useEffect(() => {
        if (userData !== null) localStorage.setItem('userData', JSON.stringify(userData))
    }, [userData])

    const logout = () => {
        if (localStorage.getItem('userData')) localStorage.removeItem('userData')

            setUserData(null)
    }

    return(
        <AuthContext.Provider value={{userData, setUserData, logout}}>{children}</AuthContext.Provider>
    )
}