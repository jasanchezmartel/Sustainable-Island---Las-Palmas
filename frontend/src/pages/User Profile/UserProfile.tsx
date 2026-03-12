import style from "../../pages/User Profile/userProfile.module.scss";

export const UserProfile = ({status = 90}) => {
  console.log({status})

  let img = '';

  if(status < 39) {
    img = 'sad-seal.png';
  } else if (status < 79) {
    img = 'neutral-seal.png';
  } else {
    img = 'happy-seal.png';
  }


  return (
    <div className={style.userProfile}>
      <h1>User Profile</h1>
      <p>This is the user profile page.</p>
      <div>
        <img src={img} />
      </div>
      <div className={style.statusBar}>
        <div className={style.progressBar} style={{width: `${status}%`}}></div>

      </div>
    </div>
  );
};