import { useContext, useState } from 'react'
import { Input } from '../../components/Input/Input'
import { AuthContext } from '../../context/AuthContext'
import { Submit } from '../../components/Submit/Submit'
import { Logo } from '../../components/Logo/Logo'
import style from '../Login/Login.module.scss'
import { Link } from 'react-router'

export function Signup() {
    const [error, setError] = useState<string | null>(null)
    const { userData, setUserData } = useContext(AuthContext)

    function postSignup(e: React.SubmitEvent) {
        e.preventDefault()

        const email = e.target.email.value
        const username = e.target.username.value
        const password = e.target.password.value
        const body = new URLSearchParams()

        body.append('email', email)
        body.append('username', username)
        body.append('password', password)

        const url = 'http://localhost:3000/signup'

        fetch(url, { method: 'POST', body: body })
            .then((res) => res.json())
            .then((data) => {
                setUserData(data)
                setError(null)
            })
            .catch((error) => {
                console.error('Error signing up:', error)
                setError('Try again')
            })
    }

    return (
        <>
            <Logo></Logo>

            {userData && (
                <b className={style.header}> Create Account {userData.user.email} </b>
            )}
            {error && <b>{error}</b>}
            <form className={style.loginStyle} onSubmit={postSignup}>
                <Input type="email" label="email" />
                <Input type="text" label="username" />
                <Input type="password" label="password" />
                <Link to="/choosepet">
                    <div>
                        <Submit value="Create Account" />
                    </div>
                </Link>

            </form>
        </>
    )
}