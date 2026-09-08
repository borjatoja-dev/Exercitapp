import React, { useState, useEffect } from 'react';
import { View, Text, TouchableOpacity, ScrollView } from 'react-native';
import { UserData } from '../types';
import { Timer } from '../components/Timer';
import { ExerciseCard, BadgeCard } from '../components/ExerciseCard';
import { exercises, WORK_TIME, REST_TIME, TOTAL_ROUNDS, PREPARATION_TIME, getMotivationalMessage, calculateSessionMinutes } from '../utils/exercises';
import { updateUserDataAfterSession, loadUserData, getDayOfWeek } from '../utils/storage';
import { checkAndUnlockBadges, badges } from '../utils/badges';

type WorkoutPhase = 'preparation' | 'work' | 'rest' | 'round_complete' | 'session_complete';

interface WorkoutScreenProps {
  onComplete: () => void;
}

export const WorkoutScreen: React.FC<WorkoutScreenProps> = ({ onComplete }) => {
  const [phase, setPhase] = useState<WorkoutPhase>('preparation');
  const [currentRound, setCurrentRound] = useState(1);
  const [currentExerciseIndex, setCurrentExerciseIndex] = useState(0);
  const [timeLeft, setTimeLeft] = useState(PREPARATION_TIME);
  const [isPaused, setIsPaused] = useState(false);
  const [newBadges, setNewBadges] = useState<string[]>([]);

  useEffect(() => {
    let interval: ReturnType<typeof setInterval>;

    if (!isPaused && timeLeft > 0) {
      interval = setInterval(() => {
        setTimeLeft((prev) => prev - 1);
      }, 1000);
    } else if (timeLeft === 0) {
      handlePhaseTransition();
    }

    return () => clearInterval(interval);
  }, [timeLeft, isPaused, phase, currentRound, currentExerciseIndex]);

  const handlePhaseTransition = () => {
    switch (phase) {
      case 'preparation':
        setPhase('work');
        setTimeLeft(WORK_TIME);
        break;
      case 'work':
        if (currentExerciseIndex < exercises.length - 1) {
          setPhase('rest');
          setTimeLeft(REST_TIME);
        } else {
          // End of round
          if (currentRound < TOTAL_ROUNDS) {
            setPhase('round_complete');
            setTimeLeft(3); // Short pause between rounds
          } else {
            finishSession();
          }
        }
        break;
      case 'rest':
        setCurrentExerciseIndex((prev) => prev + 1);
        setPhase('work');
        setTimeLeft(WORK_TIME);
        break;
      case 'round_complete':
        setCurrentRound((prev) => prev + 1);
        setCurrentExerciseIndex(0);
        setPhase('work');
        setTimeLeft(WORK_TIME);
        break;
    }
  };

  const finishSession = async () => {
    setPhase('session_complete');
    const minutes = calculateSessionMinutes(TOTAL_ROUNDS);
    const updatedData = await updateUserDataAfterSession(minutes);
    const unlocked = checkAndUnlockBadges(updatedData);
    setNewBadges(unlocked);
    
    setTimeout(() => {
      onComplete();
    }, 4000);
  };

  const togglePause = () => {
    setIsPaused(!isPaused);
  };

  const getPhaseLabel = (): string => {
    switch (phase) {
      case 'preparation':
        return 'Prepárate!';
      case 'work':
        return exercises[currentExerciseIndex].name;
      case 'rest':
        return 'Descanso';
      case 'round_complete':
        return `Rolda ${currentRound} completada!`;
      case 'session_complete':
        return '🎉 Sesión Completada! 🎉';
      default:
        return '';
    }
  };

  const isWorkPhase = phase === 'work' || phase === 'preparation';

  if (phase === 'session_complete') {
    return (
      <View className="flex-1 bg-dark-800 items-center justify-center p-6">
        <Text className="text-4xl mb-4">🏆</Text>
        <Text className="text-2xl text-accent font-bold mb-4 text-center">
          Parabéns!
        </Text>
        <Text className="text-gray-300 text-center mb-6">
          Completaches {TOTAL_ROUNDS} roldas do circuíto.
        </Text>
        {newBadges.length > 0 && (
          <View className="bg-dark-700 p-4 rounded-xl w-full">
            <Text className="text-white font-bold mb-2">Novas Medallas:</Text>
            {newBadges.map((badgeId) => {
              const badge = badges.find((b) => b.id === badgeId);
              return badge ? (
                <BadgeCard
                  key={badge.id}
                  icon={badge.icon}
                  name={badge.name}
                  description={badge.description}
                  locked={false}
                />
              ) : null;
            })}
          </View>
        )}
      </View>
    );
  }

  const currentExercise = exercises[currentExerciseIndex];

  return (
    <ScrollView className="flex-1 bg-dark-800 p-6">
      <View className="items-center mb-6">
        <Text className="text-gray-400 text-sm mb-2">
          Rolda {currentRound} de {TOTAL_ROUNDS}
        </Text>
        <Text className={`text-2xl font-bold ${isWorkPhase ? 'text-accent' : 'text-gray-400'}`}>
          {getPhaseLabel()}
        </Text>
      </View>

      <Timer seconds={timeLeft} isWorkPhase={isWorkPhase} />

      {phase === 'work' && currentExercise && (
        <View className="mb-6">
          <ExerciseCard
            name={currentExercise.name}
            description={currentExercise.description}
            duration={WORK_TIME}
            isActive={true}
          />
        </View>
      )}

      {phase === 'rest' && (
        <View className="mb-6 bg-dark-700 p-4 rounded-xl">
          <Text className="text-gray-300 text-center">
            Aproveita para respirar e prepararte para o seguinte exercicio.
          </Text>
        </View>
      )}

      <View className="flex-row justify-center space-x-4 mt-8">
        <TouchableOpacity
          onPress={togglePause}
          className="bg-dark-600 px-8 py-4 rounded-xl"
        >
          <Text className="text-white font-bold text-lg">
            {isPaused ? '▶️ Continuar' : '⏸️ Pausa'}
          </Text>
        </TouchableOpacity>
      </View>

      {isPaused && (
        <View className="mt-6 bg-dark-700 p-4 rounded-xl">
          <Text className="text-gray-400 text-center text-sm">
            Adestramento en pausa. Toca "Continuar" para retomar.
          </Text>
        </View>
      )}
    </ScrollView>
  );
};
