import style from './Slider.module.scss'
import { useState } from 'react'
import { Logo } from '../Logo/Logo'
import { Link } from 'react-router-dom'
import left from '../../assets/img/left.png'
import right from '../../assets/img/right.png'

interface ImageItem {
    img: string;
    id: number;
    name: string;
}

interface SliderProps {
    images: ImageItem[];
}

export function Slider({ images }: SliderProps) {
    const [currentIndex, setCurrentIndex] = useState(0);

    function nextSlide() {
        setCurrentIndex((prev) =>
            prev === images.length - 1 ? 0 : prev + 1
        );
    }

    function prevSlide() {
        setCurrentIndex((prev) =>
            prev === 0 ? images.length - 1 : prev - 1
        );
    }

    return (
        <section className={style.choosepet}>
            <Logo></Logo>
            <h1>What pet would you like to adopt?</h1>
                <p>{images[currentIndex].name}</p>
            <div className={style.slider}>
                <button onClick={prevSlide}><img src={left} alt="" /></button>
                <Link to={`/pets/${images[currentIndex].id}`}><img className={style.pets} src={images[currentIndex].img} alt="slider" /></Link>
                <button onClick={nextSlide}><img src={right} alt="" /></button>
            </div>
            <div className={style.startcontainer}>
                <Link className={style.start} to={`/pets/${images[currentIndex].id}`}>Start</Link>
            </div>
        </section>
    )
}