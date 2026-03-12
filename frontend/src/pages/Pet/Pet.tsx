import { Slider } from '../../components/Slider/Slider'
import sad_turtle from '../../assets/img/sad_turtle.png'
import sad_seal from '../../assets/img/sad_seal.png'
import sad_dolphin from '../../assets/img/sad_dolphin.png'



export function Pet() {
    const images = [
        {img: sad_turtle, id: 1, name: "Heinz"},
        {img: sad_seal, id: 2 , name: "Tim" },
        {img: sad_dolphin, id: 3, name: "Kasper"}
    ]

    return (

        <Slider images={images}></Slider>

    )
}