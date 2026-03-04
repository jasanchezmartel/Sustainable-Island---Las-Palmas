import style from './input.module.scss'


interface inputPorps{
    label: string 
    type: string 
    name: string 
    value?: string 
}

export function Input ({label, type, name}: inputPorps) {

    return(
        <>
        <label className={style.inputStyle}>
            {label}
            <input type={type} name={name} placeholder={`Indtast ${name}`}></input>
        </label>
        </>
    )
}