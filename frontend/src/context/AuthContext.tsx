import type React from "react"
import { createContext, type SetStateAction } from "react"
import type { UserData } from "../types/userType"


interface AuthContextProps {
    userData: UserData | null
    setUserData: React.Dispatch<SetStateAction<UserData | null>>
    logout: () => void
}

export const AuthContext = createContext<AuthContextProps>(undefined!)