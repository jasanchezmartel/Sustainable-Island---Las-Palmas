import style from './NavBar.module.scss'
import { NavLink } from 'react-router'
import menu from '../../assets/img/Menu.png'
import paw from '../../assets/img/paw.png'
import task from '../../assets/img/task.png'

export function NavBar() {

    return (
        <nav className={style.navbarStyle}>
            <ul>
                <li>
                    <NavLink to='/'>
                        <img className={style.navImg} src={paw} alt="" /> </NavLink>
                </li>
                <li>
                    <NavLink to='/'>
                        <img className={style.navImg} src={menu} alt="" /> </NavLink>
                </li>
                <li>
                    <NavLink to='/'>
                        <img className={style.navImg} src={task} alt="" /> </NavLink>
                </li>
            </ul>
        </nav >
    )
}
