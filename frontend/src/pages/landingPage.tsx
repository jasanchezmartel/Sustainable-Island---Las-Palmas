import style from './landingPage.module.scss';
import AppLogo from '../assets/AppLogo.png';

export function LandingPage() {
    return (
        <div className={style.landingPage}>

            <div className={style.logoCard}>
                <div className={style.logoCircle}>
                    <img 
                        src={AppLogo} 
                        alt="OceanGuard logo"
                        style={{ width: '54px', height: '54px', objectFit: 'contain', display: 'block' }}
                    />
                </div>
                <div>
                    <span className={style.logoName}>Welcome to SeaZen</span>
                    <span className={style.logoTagline}> The ocean cleanup app</span>
                </div>
            </div>

            <div className={style.textCard}>
                <h1>Small actions. Massive impact.</h1>
                <p>Join thousands of ocean guardians completing weekly cleanup challenges. Track your impact, care for your sea pet, and help turn the tide on plastic pollution.</p>
            </div>

            <button className={style.Button}>Join us right now!</button>
        </div>
    );
}