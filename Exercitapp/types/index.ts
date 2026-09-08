export interface Exercise {
  id: string;
  name: string;
  description: string;
  duration: number; // seconds
}

export interface SessionData {
  date: string;
  completedRounds: number;
  totalMinutes: number;
  timestamp: number;
}

export interface UserData {
  totalSessions: number;
  totalMinutes: number;
  badges: string[];
  lastSessionDate: string | null;
}

export interface Badge {
  id: string;
  name: string;
  description: string;
  icon: string;
  requirement: (data: UserData) => boolean;
}
