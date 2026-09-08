import { Exercise } from '../types';

export const exercises: Exercise[] = [
  {
    id: '1',
    name: 'Flexións na parede',
    description: 'De pé, apoia as mans na parede e baixa o corpo mantendo a espalda recta.',
    duration: 45,
  },
  {
    id: '2',
    name: 'Remo con botellas',
    description: 'Con dúas botellas nas mans, inclina o tronco e sobe os brazos cara aos lados.',
    duration: 45,
  },
  {
    id: '3',
    name: 'Sentadillas con cadeira',
    description: 'Sentado nunha cadeira, levántate e volve sentar controlando o movemento.',
    duration: 45,
  },
  {
    id: '4',
    name: 'Fondos de tríceps',
    description: 'Apoiado nunha cadeira ou sofá, baixa e sobe o corpo usando os tríceps.',
    duration: 45,
  },
  {
    id: '5',
    name: 'Plancha de xeonllos',
    description: 'En posición de plancha, apoia os xeonllos e mantén a posición.',
    duration: 45,
  },
];

export const WORK_TIME = 45; // seconds
export const REST_TIME = 15; // seconds
export const TOTAL_ROUNDS = 3;
export const PREPARATION_TIME = 5; // seconds

export const getMotivationalMessage = (): string => {
  const messages = [
    '💪 A forza está en ti!',
    '🔽 Cada repetición conta!',
    '🏠 A casa é o teu ximnasio!',
    '⚡ Súperate hoxe!',
    '🎯 Constancia é poder!',
    '🌟 O esforzo trae resultados!',
  ];
  return messages[Math.floor(Math.random() * messages.length)];
};

export const calculateSessionMinutes = (rounds: number): number => {
  // Each round: 5 exercises * 45s work + 4 rests * 15s = 225 + 60 = 285s = 4.75 min
  const minutesPerRound = (exercises.length * WORK_TIME + (exercises.length - 1) * REST_TIME) / 60;
  return Math.round(minutesPerRound * rounds);
};
