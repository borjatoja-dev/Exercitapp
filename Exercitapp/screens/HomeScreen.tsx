import React, { useState, useEffect } from 'react';
import { View, Text, TouchableOpacity, ScrollView, SafeAreaView } from 'react-native';
import { UserData } from '../types';
import { ProgressBar } from '../components/ProgressBar';
import { BadgeCard } from '../components/ExerciseCard';
import { WorkoutScreen } from './WorkoutScreen';
import { exercises, TOTAL_ROUNDS, getMotivationalMessage, calculateSessionMinutes } from '../utils/exercises';
import { loadUserData, getDayOfWeek } from '../utils/storage';
import { getUnlockedBadges } from '../utils/badges';

export const HomeScreen: React.FC = () => {
  const [isWorkoutActive, setIsWorkoutActive] = useState(false);
  const [userData, setUserData] = useState<UserData>({
    totalSessions: 0,
    totalMinutes: 0,
    badges: [],
    lastSessionDate: null,
  });
  const [motivationalMessage, setMotivationalMessage] = useState('');

  useEffect(() => {
    loadData();
    setMotivationalMessage(getMotivationalMessage());
  }, []);

  const loadData = async () => {
    const data = await loadUserData();
    setUserData(data);
  };

  const handleWorkoutComplete = async () => {
    await loadData();
    setIsWorkoutActive(false);
  };

  const estimatedMinutes = calculateSessionMinutes(TOTAL_ROUNDS);
  const unlockedBadges = getUnlockedBadges(userData);
  const today = new Date().toLocaleDateString('gl-ES', { weekday: 'long' });

  if (isWorkoutActive) {
    return <WorkoutScreen onComplete={handleWorkoutComplete} />;
  }

  return (
    <SafeAreaView className="flex-1 bg-dark-800">
      <ScrollView className="flex-1 p-6">
        {/* Header */}
        <View className="mb-8 mt-4">
          <Text className="text-3xl font-bold text-white mb-2">Exercitapp</Text>
          <Text className="text-gray-400 text-lg">{today.charAt(0).toUpperCase() + today.slice(1)}</Text>
        </View>

        {/* Motivational Message */}
        <View className="bg-dark-700 p-5 rounded-xl mb-6">
          <Text className="text-accent text-xl font-bold text-center">
            {motivationalMessage}
          </Text>
          <Text className="text-gray-400 text-center mt-2">
            Tempo estimado: {estimatedMinutes} minutos
          </Text>
        </View>

        {/* Stats */}
        <View className="flex-row justify-between mb-6">
          <View className="bg-dark-700 p-4 rounded-xl flex-1 mr-2">
            <Text className="text-gray-400 text-xs">Sesións</Text>
            <Text className="text-2xl font-bold text-white">{userData.totalSessions}</Text>
          </View>
          <View className="bg-dark-700 p-4 rounded-xl flex-1 ml-2">
            <Text className="text-gray-400 text-xs">Minutos Totais</Text>
            <Text className="text-2xl font-bold text-white">{userData.totalMinutes}</Text>
          </View>
        </View>

        {/* Progress Bar */}
        <ProgressBar current={userData.totalMinutes} total={300} />

        {/* Exercises Preview */}
        <View className="mb-6">
          <Text className="text-white text-lg font-bold mb-4">Circuíto de hoxe</Text>
          {exercises.map((exercise, index) => (
            <View
              key={exercise.id}
              className="bg-dark-700 p-3 rounded-lg mb-2 flex-row items-center"
            >
              <View className="w-8 h-8 bg-dark-600 rounded-full items-center justify-center mr-3">
                <Text className="text-accent font-bold">{index + 1}</Text>
              </View>
              <View className="flex-1">
                <Text className="text-white font-semibold">{exercise.name}</Text>
                <Text className="text-gray-500 text-xs">45s traballo • 15s descanso</Text>
              </View>
            </View>
          ))}
        </View>

        {/* Badges Section */}
        <View className="mb-6">
          <Text className="text-white text-lg font-bold mb-4">As túas medallas</Text>
          {unlockedBadges.length === 0 ? (
            <View className="bg-dark-700 p-4 rounded-xl">
              <Text className="text-gray-400 text-center">
                Completa sesións para desbloquear medallas!
              </Text>
            </View>
          ) : (
            unlockedBadges.map((badge) => (
              <BadgeCard
                key={badge.id}
                icon={badge.icon}
                name={badge.name}
                description={badge.description}
                locked={false}
              />
            ))
          )}
        </View>

        {/* Start Button */}
        <TouchableOpacity
          onPress={() => setIsWorkoutActive(true)}
          className="bg-accent py-5 rounded-xl mb-8 items-center shadow-lg"
        >
          <Text className="text-dark-900 font-bold text-xl">
            🏋️ Comezar Adestramento
          </Text>
        </TouchableOpacity>

        {/* Info Footer */}
        <View className="mb-8">
          <Text className="text-gray-500 text-xs text-center">
            {TOTAL_ROUNDS} roldas • 5 exercicios • Sen material necesario
          </Text>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};
