import { useEffect } from 'react'
import { Routes, Route } from 'react-router-dom'
import { useThemeStore } from './stores/themeStore'
import { useAuthStore } from './stores/authStore'
import Navbar from './components/Navbar'
import Home from './pages/Home'
import Login from './pages/Login'
import Register from './pages/Register'
import Breeds from './pages/Breeds'
import AddPet from './pages/AddPet'
import PetDetails from './pages/PetDetails'
import Profile from './pages/Profile'
import AdminDashboard from './pages/AdminDashboard'

function App() {
  const { theme } = useThemeStore()
  const { initializeAuth } = useAuthStore()

  useEffect(() => {
    // Initialize auth state from localStorage
    initializeAuth()
  }, [initializeAuth])

  return (
    <div className={`min-h-screen ${theme === 'dark' ? 'dark' : ''}`}>
      <div className="bg-gray-50 dark:bg-gray-900 min-h-screen">
        <Navbar />
        <main className="pt-16">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/breeds" element={<Breeds />} />
            <Route path="/add-pet" element={<AddPet />} />
            <Route path="/pet/:id" element={<PetDetails />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/admin" element={<AdminDashboard />} />
          </Routes>
        </main>
      </div>
    </div>
  )
}

export default App 