import { useContext, useState } from 'react'
import { Input } from '../../components/Input/Input'
import { AuthContext } from '../../context/AuthContext'
import { Submit } from '../../components/Submit/Submit'
import logo from '../../assets/img/logo.png'
import style from '../Login/Login.module.scss'

export function Signup() {
    const [error, setError] = useState<string | null>(null)
    const { userData, setUserData } = useContext(AuthContext)

    function postSignup(e: React.SubmitEvent) {
        e.preventDefault()

        const email = e.target.Email.value
        const username = e.target.username.value
        const password = e.target.password.v
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
            <img src={logo} alt="turtle-logo" className={style.logo} />

            {userData && (
                <b className={style.header}> Create Account {userData.user.email} </b>
            )}
            {error && <b>{error}</b>}
            <form className={style.loginStyle} onSubmit={postSignup}>
                <Input type="email" label="Email" />
                <Input type="text" label="Username" />
                <Input type="password" label="Password" />
                <div>
                    <Submit value="Create Account" />
                </div>

            </form>
        </>
    )
}