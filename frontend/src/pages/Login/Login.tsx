import { useContext, useState } from 'react'
import { Input } from '../../components/Input/Input'
import { AuthContext } from '../../context/AuthContext'
import { Submit } from '../../components/Submit/Submit'
import {Logo} from '../../components/Logo/Logo'
import { Button } from '../../components/Button/Button'
import { useNavigate } from 'react-router'
import style from './Login.module.scss'


export function Login() {
    const [error, setError] = useState<string | null>(null)
    const { userData, setUserData } = useContext(AuthContext)
    const navigate = useNavigate()

    function postLogin(e: React.SubmitEvent) {
        e.preventDefault()


        const userName = e.target.username.value
        const passWord = e.target.password.value

        const body = new URLSearchParams()


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
            <Logo></Logo>
            {userData && (
                <b className={style.header}>Log In {userData.user.username} {userData.user.password}</b>
            )}
            {error && <b>error</b>}
            <form className={style.loginStyle} onSubmit={(e) => postLogin(e)}>
                <Input type="text" label='username' />
                <Input type="password" label='password' />
                <div>
                    <Submit value='Login'></Submit>
                </div>
                <div>
                    <Button onClick={() => navigate("/signup")}>Register</Button>
                </div>
            </form>
        </>
    )

}