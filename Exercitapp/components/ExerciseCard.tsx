import React from 'react';
import { View, Text, TouchableOpacity } from 'react-native';

interface ExerciseCardProps {
  name: string;
  description: string;
  duration: number;
  isActive?: boolean;
  isRest?: boolean;
}

export const ExerciseCard: React.FC<ExerciseCardProps> = ({
  name,
  description,
  duration,
  isActive = false,
  isRest = false,
}) => {
  return (
    <View
      className={`p-4 rounded-xl mb-3 ${
        isActive ? 'bg-dark-600 border-2 border-accent' : 'bg-dark-700'
      } ${isRest ? 'opacity-50' : ''}`}
    >
      <Text className={`text-lg font-bold ${isActive ? 'text-accent' : 'text-white'}`}>
        {name}
      </Text>
      <Text className="text-gray-400 text-sm mt-1">{description}</Text>
      <Text className="text-gray-500 text-xs mt-2">⏱️ {duration} segundos</Text>
    </View>
  );
};

export const BadgeCard: React.FC<{ icon: string; name: string; description: string; locked?: boolean }> = ({
  icon,
  name,
  description,
  locked = false,
}) => {
  return (
    <View
      className={`p-3 rounded-lg mb-2 flex-row items-center ${
        locked ? 'bg-dark-700 opacity-50' : 'bg-dark-600'
      }`}
    >
      <Text className="text-3xl mr-3">{locked ? '🔒' : icon}</Text>
      <View className="flex-1">
        <Text className={`font-bold ${locked ? 'text-gray-500' : 'text-white'}`}>{name}</Text>
        <Text className="text-gray-400 text-xs">{description}</Text>
      </View>
    </View>
  );
};
