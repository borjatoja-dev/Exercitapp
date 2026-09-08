import AsyncStorage from '@react-native-async-storage/async-storage';
import { UserData, SessionData } from '../types';

const USER_DATA_KEY = '@exercitapp:userdata';
const SESSIONS_KEY = '@exercitapp:sessions';

export const defaultUserData: UserData = {
  totalSessions: 0,
  totalMinutes: 0,
  badges: [],
  lastSessionDate: null,
};

export const saveUserData = async (data: UserData): Promise<void> => {
  try {
    await AsyncStorage.setItem(USER_DATA_KEY, JSON.stringify(data));
  } catch (error) {
    console.error('Error saving user data:', error);
  }
};

export const loadUserData = async (): Promise<UserData> => {
  try {
    const data = await AsyncStorage.getItem(USER_DATA_KEY);
    return data ? JSON.parse(data) : defaultUserData;
  } catch (error) {
    console.error('Error loading user data:', error);
    return defaultUserData;
  }
};

export const saveSession = async (session: SessionData): Promise<void> => {
  try {
    const sessionsData = await AsyncStorage.getItem(SESSIONS_KEY);
    const sessions: SessionData[] = sessionsData ? JSON.parse(sessionsData) : [];
    sessions.push(session);
    await AsyncStorage.setItem(SESSIONS_KEY, JSON.stringify(sessions));
  } catch (error) {
    console.error('Error saving session:', error);
  }
};

export const loadSessions = async (): Promise<SessionData[]> => {
  try {
    const data = await AsyncStorage.getItem(SESSIONS_KEY);
    return data ? JSON.parse(data) : [];
  } catch (error) {
    console.error('Error loading sessions:', error);
    return [];
  }
};

export const updateUserDataAfterSession = async (minutes: number): Promise<UserData> => {
  const currentData = await loadUserData();
  const today = new Date().toISOString().split('T')[0];
  
  const updatedData: UserData = {
    totalSessions: currentData.totalSessions + 1,
    totalMinutes: currentData.totalMinutes + minutes,
    badges: [...currentData.badges],
    lastSessionDate: today,
  };

  await saveUserData(updatedData);
  return updatedData;
};

export const getDayOfWeek = (dateString: string): string => {
  const date = new Date(dateString);
  const days = ['Domingo', 'Luns', 'Martes', 'Mércores', 'Xoves', 'Venres', 'Sábado'];
  return days[date.getDay()];
};
