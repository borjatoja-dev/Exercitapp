import React from 'react';
import { Text, View } from 'react-native';

interface TimerProps {
  seconds: number;
  isWorkPhase: boolean;
  label?: string;
}

export const Timer: React.FC<TimerProps> = ({ seconds, isWorkPhase, label }) => {
  const minutes = Math.floor(seconds / 60);
  const remainingSeconds = seconds % 60;
  const timeDisplay = `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;

  return (
    <View className="items-center justify-center my-8">
      {label && (
        <Text className={`text-2xl font-bold mb-4 ${isWorkPhase ? 'text-accent' : 'text-gray-400'}`}>
          {label}
        </Text>
      )}
      <Text className={`text-7xl font-bold ${isWorkPhase ? 'text-accent' : 'text-gray-500'}`}>
        {timeDisplay}
      </Text>
    </View>
  );
};
