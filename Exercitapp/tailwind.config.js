/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./App.{js,jsx,ts,tsx}",
    "./components/**/*.{js,jsx,ts,tsx}",
    "./screens/**/*.{js,jsx,ts,tsx}",
  ],
  presets: [require("nativewind/preset")],
  theme: {
    extend: {
      colors: {
        dark: {
          900: '#0a0a0a',
          800: '#121212',
          700: '#1e1e1e',
          600: '#2d2d2d',
        },
        accent: {
          DEFAULT: '#4ade80',
          hover: '#22c55e',
        }
      },
    },
  },
  plugins: [],
}

