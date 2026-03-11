import { useContext, useState } from 'react'
import { Input } from '../../components/Input/Input'
import { AuthContext } from '../../context/AuthContext'
import { Submit } from '../../components/Submit/Submit'
import logo from '../../assets/img/logo.png'
import style from './Login.module.scss'


export function Login() {
    const [error, setError] = useState<string | null>(null)
    const { userData, setUserData } = useContext(AuthContext)

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
            <img src={logo} alt="turtle-logo" className={style.logo} />
            {userData && (
                <b className={style.header}>Log In {userData.user.username} {userData.user.password}</b>
            )}
            {error && <b>error</b>}
            <form className={style.loginStyle} onSubmit={(e) => postLogin(e)}>
                <Input type="username" label='Username' />
                <Input type="password" label='Password' />
                <div>
                    <Submit value='Login'></Submit>
                </div>
                <div>
                    <a href="http://localhost:5173/signup">Register</a>
                </div>
            </form>
        </>
    )

}