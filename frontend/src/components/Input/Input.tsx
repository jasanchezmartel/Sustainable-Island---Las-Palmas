import style from './input.module.scss'


interface inputPorps{
    label: string 
    type: string 
    value?: string 
}

export function Input ({label, type}: inputPorps) {

    return(
        <>
        <label className={style.inputStyle}>
            {label}
            <input type={type} id={label}></input>
        </label>
        </>
    )
}