import { useState } from 'react';
import style from './missionsPage.module.scss';
import AppLogo from '../../assets/AppLogo.png';
import BackBtn from '../../assets/BackBtn.png';

interface Mission {
    id: number;
    title: string;
    description: string;
    goal: number;
    unit: string;
}

const MISSION_POOL: Mission[] = [
{ id: 1, title: 'Pick up plastic bottles', description: '', goal: 10, unit: 'bottles' },
{ id: 2, title: 'Remove plastic bags', description: '', goal: 15, unit: 'bags' },
{ id: 3, title: 'Collect cigarette butts', description: '', goal: 10, unit: 'butts' },
{ id: 4, title: 'Pick up aluminum cans', description: '', goal: 15, unit: 'cans' },
{ id: 5, title: 'Collect tiny plastic pieces', description: '', goal: 5, unit: 'pieces' },
{ id: 6, title: 'Remove trash from the shoreline', description: '', goal: 20, unit: 'items' },
{ id: 7, title: 'Collect plastic bottles from the beach', description: '', goal: 15, unit: 'items' },
{ id: 8, title: 'Pick up discarded fishing nets', description: '', goal: 5, unit: 'items' },
{ id: 9, title: 'Remove plastic bags from the water', description: '', goal: 12, unit: 'items' },
{ id: 10, title: 'Collect glass bottles along the coast.', description: '', goal: 10, unit: 'items' },
{ id: 11, title: 'Gather microplastics near the shoreline', description: '', goal: 40, unit: 'pieces' },
{ id: 12, title: 'Pick up food wrappers on the beach', description: '', goal: 25, unit: 'items' },
{ id: 13, title: 'Remove cigarette butts from the sand', description: '', goal: 50, unit: 'items' },
{ id: 14, title: 'Collect lost flip-flops and sandals', description: '', goal: 8, unit: 'items' },
{ id: 15, title: 'Remove metal cans from the shore', description: '', goal: 18, unit: 'items' },
];

function generateDailyMissions(amount: number): Mission[] {
    const shuffled = [...MISSION_POOL].sort(() => 0.5 - Math.random());
    return shuffled.slice(0, amount);
}

function MissionCard({ mission }: { mission: Mission }) {
    const [count, setCount] = useState(0);
    const [input, setInput] = useState('1');

    const amount = Math.max(1, parseInt(input) || 1);
    const percentage = Math.min((count / mission.goal) * 100, 100);
    const completed = count >= mission.goal;

    return (
        <div className={`${style.card} ${completed ? style.cardComplete : ''}`}>

            <div className={style.controls}>
                <button
                    className={style.btnMinus}
                    onClick={() => setCount(p => Math.max(p - amount, 0))}
                >-</button>

                <span className={style.countDisplay}>{count} / {mission.goal}</span>

                <button
                    className={style.btnPlus}
                    onClick={() => setCount(p => Math.min(p + amount, mission.goal))}
                >+</button>

                <h2 className={style.cardTitle}>{mission.title}</h2>
            </div>

            <div className={style.track}>
                <div
                    className={`${style.fill} ${completed ? style.fillComplete : ''}`}
                    style={{ width: `${percentage}%` }}
                >
                    <span className={style.percentage}>{Math.round(percentage)}%</span>
                </div>
            </div>

            {completed && <p className={style.successMsg}>Task complete!</p>}

        </div>
    );
}

export function MissionsPage() {

    const [missions] = useState<Mission[]>(() => {
        const today = new Date().toDateString();
        const storedDate = localStorage.getItem("missionDate");

        if (storedDate === today) {
            const stored = localStorage.getItem("dailyMissions");
            if (stored) return JSON.parse(stored);
        }

        const generated = generateDailyMissions(5);

        localStorage.setItem("dailyMissions", JSON.stringify(generated));
        localStorage.setItem("missionDate", today);

        return generated;
    });

    return (
        <div className={style.page}>
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

            <div className={style.header}>
                <h1 className={style.title}>Your Tasks</h1>
            </div>

            <div className={style.list}>
                {missions.map(mission => (
                    <MissionCard key={mission.id} mission={mission} />
                ))}
            </div>

        </div>
    );
}