import style from './Submit.module.scss'

interface submitProps {
    value: string
}

export function Submit ({ value}: submitProps) {
    return(
        <>
        <input className={style.submitButton} type={'submit'} value={value}/>
        </>
    )
}