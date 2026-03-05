export interface UserData {
    acess_token: string 
    user: UserClass
    created: string 
}

export interface UserClass {
    id: string 
    email: string 
    username: string 
    password: string 
}