import React from 'react';
import { View, Text } from 'react-native';

interface ProgressBarProps {
  current: number;
  total: number;
}

export const ProgressBar: React.FC<ProgressBarProps> = ({ current, total }) => {
  const percentage = Math.min((current / total) * 100, 100);

  return (
    <View className="w-full my-4">
      <View className="flex-row justify-between mb-2">
        <Text className="text-gray-300 text-sm">Progreso cara aos 300 min</Text>
        <Text className="text-accent text-sm font-bold">{current} / {total} min</Text>
      </View>
      <View className="w-full h-4 bg-dark-600 rounded-full overflow-hidden">
        <View
          className="h-full bg-accent rounded-full"
          style={{ width: `${percentage}%` }}
        />
      </View>
      <Text className="text-gray-500 text-xs mt-1 text-right">
        {Math.round(percentage)}% completado
      </Text>
    </View>
  );
};
