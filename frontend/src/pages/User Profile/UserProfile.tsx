import style from "../../pages/User Profile/userProfile.module.scss";
import sad_seal from '../../assets/img/sad_seal.png';
import medium_seal from '../../assets/img/medium_seal.png';
import happy_seal from '../../assets/img/happy_seal.png';
import { Button } from "../../components/Button/Button";
import { Link } from "react-router";
import AppLogo from '../../assets/AppLogo.png';
import BackBtn from '../../assets/BackBtn.png';import { NavBar } from "../../components/NavBar/NavBar";
;

export const UserProfile = ({status = 90}) => {
  console.log({status})

  let img = '';

  if(status < 39) {
    img = sad_seal;
  } else if (status < 79) {
    img = medium_seal;
  } else {
    img = happy_seal;
  }


  return (
    <div> 
      
        <div className={style.topBar}>
            <img
                src={AppLogo}
                alt="SeaZen logo"
                className={style.logo}
            />

            <img
                src={BackBtn}
                alt="Back button"
                className={style.backBtn}
            />
            </div>
    
    <div className={style.userProfile}>
      <h1>Batman</h1>
      <div>
        <img height={275} src={img} />
      </div>
      <div className={style.statusBar}>
        <div className={style.progressBar} style={{width: `${status}%`}}></div>



      </div>
      
      <Link to="/missions" >
        <div className={style.buttonContainer}>
          <Button>Do some Task!</Button>
        </div>
      </Link>

    </div>
    <NavBar />
    </div>
    
  );
};