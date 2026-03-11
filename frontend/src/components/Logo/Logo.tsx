import style from './Logo.module.scss'
import logo from '../../assets/img/logo.png'

export function Logo() {

 return (
    <img src={logo} alt="turtle-logo" className={style.logo} />
 )   
}