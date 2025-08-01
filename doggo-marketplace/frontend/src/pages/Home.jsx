import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { Search, Heart, MapPin, DollarSign, Star } from 'lucide-react'
import PetCard from '../components/PetCard'

const Home = () => {
  const [searchTerm, setSearchTerm] = useState('')
  const [selectedBreed, setSelectedBreed] = useState('')
  const [pets, setPets] = useState([])
  const [loading, setLoading] = useState(true)

  // Mock data for demonstration
  useEffect(() => {
    const mockPets = [
      {
        id: 1,
        name: 'Buddy',
        breed: 'Golden Retriever',
        age: 12,
        gender: 'MALE',
        price: 120000,
        image: 'https://images.unsplash.com/photo-1552053831-71594a27632d?w=400',
        location: 'New York, NY',
        vaccinated: true,
        rating: 4.8
      },
      {
        id: 2,
        name: 'Luna',
        breed: 'Husky',
        age: 8,
        gender: 'FEMALE',
        price: 150000,
        image: 'https://images.unsplash.com/photo-1547407139-3c921a66005c?w=400',
        location: 'Los Angeles, CA',
        vaccinated: true,
        rating: 4.9
      },
      {
        id: 3,
        name: 'Max',
        breed: 'German Shepherd',
        age: 18,
        gender: 'MALE',
        price: 180000,
        image: 'https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400',
        location: 'Chicago, IL',
        vaccinated: true,
        rating: 4.7
      },
      {
        id: 4,
        name: 'Bella',
        breed: 'Labrador',
        age: 6,
        gender: 'FEMALE',
        price: 100000,
        image: 'https://images.unsplash.com/photo-1546527868-ccb7ee7dfa6a?w=400',
        location: 'Miami, FL',
        vaccinated: true,
        rating: 4.6
      }
    ]
    setPets(mockPets)
    setLoading(false)
  }, [])

  const filteredPets = pets.filter(pet => {
    const matchesSearch = pet.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         pet.breed.toLowerCase().includes(searchTerm.toLowerCase())
    const matchesBreed = !selectedBreed || pet.breed === selectedBreed
    return matchesSearch && matchesBreed
  })

  const breeds = ['Golden Retriever', 'Husky', 'German Shepherd', 'Labrador', 'Bulldog', 'Poodle']

  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <section className="bg-gradient-to-r from-blue-600 to-purple-600 text-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl md:text-6xl font-bold mb-6">
            Find Your Perfect Companion
          </h1>
          <p className="text-xl md:text-2xl mb-8 text-blue-100">
            Discover and adopt the most adorable dogs from trusted breeders
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Link to="/add-pet" className="btn-primary text-lg px-8 py-3">
              Sell Your Pet
            </Link>
            <Link to="/breeds" className="btn-secondary text-lg px-8 py-3">
              Explore Breeds
            </Link>
          </div>
        </div>
      </section>

      {/* Search Section */}
      <section className="py-12 bg-white dark:bg-gray-800">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="bg-white dark:bg-gray-700 rounded-lg shadow-lg p-6">
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div className="relative">
                <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                <input
                  type="text"
                  placeholder="Search pets..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="input-field pl-10"
                />
              </div>
              <select
                value={selectedBreed}
                onChange={(e) => setSelectedBreed(e.target.value)}
                className="input-field"
              >
                <option value="">All Breeds</option>
                {breeds.map(breed => (
                  <option key={breed} value={breed}>{breed}</option>
                ))}
              </select>
              <button className="btn-primary">
                Search
              </button>
            </div>
          </div>
        </div>
      </section>

      {/* Featured Pets */}
      <section className="py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h2 className="text-3xl font-bold text-gray-900 dark:text-white mb-8 text-center">
            Featured Pets
          </h2>
          
          {loading ? (
            <div className="flex justify-center">
              <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
              {filteredPets.map(pet => (
                <PetCard key={pet.id} pet={pet} />
              ))}
            </div>
          )}
        </div>
      </section>

      {/* Stats Section */}
      <section className="py-12 bg-gray-50 dark:bg-gray-900">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 text-center">
            <div>
              <div className="text-4xl font-bold text-blue-600 mb-2">500+</div>
              <div className="text-gray-600 dark:text-gray-400">Happy Pets Adopted</div>
            </div>
            <div>
              <div className="text-4xl font-bold text-blue-600 mb-2">50+</div>
              <div className="text-gray-600 dark:text-gray-400">Trusted Breeders</div>
            </div>
            <div>
              <div className="text-4xl font-bold text-blue-600 mb-2">1000+</div>
              <div className="text-gray-600 dark:text-gray-400">Happy Families</div>
            </div>
          </div>
        </div>
      </section>
    </div>
  )
}

export default Home 