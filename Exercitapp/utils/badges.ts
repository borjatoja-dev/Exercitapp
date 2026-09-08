import { UserData, Badge } from '../types';

export const badges: Badge[] = [
  {
    id: 'first_session',
    name: 'Primeira Sesión',
    description: 'Completaches a túa primeira sesión de adestramento',
    icon: '🎉',
    requirement: (data) => data.totalSessions >= 1,
  },
  {
    id: 'five_sessions',
    name: 'Constante',
    description: '5 sesións completadas',
    icon: '🔥',
    requirement: (data) => data.totalSessions >= 5,
  },
  {
    id: 'ten_sessions',
    name: 'Dedicado',
    description: '10 sesións completadas',
    icon: '💪',
    requirement: (data) => data.totalSessions >= 10,
  },
  {
    id: 'hundred_minutes',
    name: '100 Minutos',
    description: 'Acumulaches 100 minutos de adestramento',
    icon: '⏱️',
    requirement: (data) => data.totalMinutes >= 100,
  },
  {
    id: 'three_hundred_minutes',
    name: 'Meta 300',
    description: 'Acumulaches 300 minutos - Obxectivo cumplido!',
    icon: '🏆',
    requirement: (data) => data.totalMinutes >= 300,
  },
  {
    id: 'week_streak',
    name: 'Semana Completa',
    description: 'Adestraches 7 días seguidos',
    icon: '📅',
    requirement: (data) => {
      // This would need more sophisticated tracking
      return false; // Simplified for now
    },
  },
];

export const checkAndUnlockBadges = (userData: UserData): string[] => {
  const newBadges: string[] = [];
  
  badges.forEach((badge) => {
    if (!userData.badges.includes(badge.id) && badge.requirement(userData)) {
      newBadges.push(badge.id);
    }
  });
  
  return newBadges;
};

export const getUnlockedBadges = (userData: UserData): Badge[] => {
  return badges.filter((badge) => userData.badges.includes(badge.id));
};

export const getNextBadge = (userData: UserData): Badge | null => {
  const lockedBadges = badges.filter((badge) => !userData.badges.includes(badge.id));
  return lockedBadges.length > 0 ? lockedBadges[0] : null;
};
