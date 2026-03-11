import style from './Button.module.scss'

type ButtonProps = {
    as?: React.ElementType
    children?: React.ReactNode
}& React.ComponentPropsWithoutRef<any>

export function Button({ as: Component = 'button', children, ...props}: ButtonProps) {
    return (
        // If style.btn is null or undefined, use '' instead.
        <Component className= {style.btn ?? ''} {...props}>
            {children}
        </Component>
    )
}