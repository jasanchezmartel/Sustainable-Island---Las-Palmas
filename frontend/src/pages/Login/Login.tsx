import { useContext, useState } from 'react'
import { Input } from '../../components/Input/Input'
import { AuthContext } from '../../context/AuthContext'
import { Submit } from '../../components/Submit/Submit'


export function Login() {
    const [error, setError] = useState<string | null>(null)
    const { userData, setUserData } = useContext(AuthContext)

    function postLogin(e: React.SubmitEvent) {
        e.preventDefault()

        const eMail = e.target.email.value
        const userName = e.target.username.value
        const passWord = e.target.password.value

        const body = new URLSearchParams()

        body.append('email', eMail)
        body.append('username', userName)
        body.append('password', passWord)

        const url = 'http://localhost:5173/login'

        fetch(url, { method: 'POST', body: body })
            .then((res) => res.json())
            .then((data) => {
                setUserData(data)
                setError('')
            })
            .catch((error) => {
                console.error('Error loggin in: ', error);
                setError('Try again')

            })
    }

    console.log('UserData: ', userData);

    return (
        <>
            {userData && (
                <b>Welcome {userData.user.email} {userData.user.username} {userData.user.password}</b>
            )}
            {error && <b>error</b>}
            <form onSubmit={(e) => postLogin(e)}>
                <Input type="email" name='email' label='Email' />
                <Input type="username" name='username' label='Username' />
                <Input type="password" name='password' label='Password' />
                <Submit value='Login'></Submit>
            </form>
        </>
    )

}