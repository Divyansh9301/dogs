import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { Heart, MapPin, DollarSign, Star, Shield, Calendar, Phone, Mail, CreditCard } from 'lucide-react'
import { useAuthStore } from '../stores/authStore'
import toast from 'react-hot-toast'

const PetDetails = () => {
  const { id } = useParams()
  const navigate = useNavigate()
  const { isAuthenticated } = useAuthStore()
  const [pet, setPet] = useState(null)
  const [loading, setLoading] = useState(true)
  const [showBooking, setShowBooking] = useState(false)
  const [bookingLoading, setBookingLoading] = useState(false)

  useEffect(() => {
    // Mock API call - replace with actual API
    const fetchPet = async () => {
      setLoading(true)
      try {
        await new Promise(resolve => setTimeout(resolve, 1000))
        
        // Mock pet data
        const mockPet = {
          id: parseInt(id),
          name: 'Buddy',
          breed: 'Golden Retriever',
          age: 12,
          gender: 'MALE',
          price: 120000,
          image: 'https://images.unsplash.com/photo-1552053831-71594a27632d?w=800',
          location: 'New York, NY',
          vaccinated: true,
          rating: 4.8,
          description: 'Buddy is a friendly and energetic Golden Retriever who loves to play fetch and go for walks. He is well-trained and gets along great with children and other pets.',
          fatherBreed: 'Golden Retriever',
          motherBreed: 'Golden Retriever',
          medicalHistory: 'All vaccinations up to date. No known health issues.',
          seller: {
            name: 'John Smith',
            email: 'john@example.com',
            phone: '+1 (555) 123-4567',
            rating: 4.9
          }
        }
        
        setPet(mockPet)
      } catch (error) {
        toast.error('Failed to load pet details')
      } finally {
        setLoading(false)
      }
    }

    fetchPet()
  }, [id])

  const formatPrice = (price) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 0,
    }).format(price / 100)
  }

  const handleBooking = async () => {
    if (!isAuthenticated) {
      toast.error('Please log in to book this pet')
      navigate('/login')
      return
    }

    setBookingLoading(true)
    try {
      await new Promise(resolve => setTimeout(resolve, 2000))
      toast.success('Booking successful! We will contact you soon.')
      setShowBooking(false)
    } catch (error) {
      toast.error('Booking failed. Please try again.')
    } finally {
      setBookingLoading(false)
    }
  }

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-900">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    )
  }

  if (!pet) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-900">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-4">
            Pet not found
          </h2>
          <button
            onClick={() => navigate('/')}
            className="btn-primary"
          >
            Go Home
          </button>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 py-12">
      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          {/* Left Column - Images and Basic Info */}
          <div>
            <div className="card overflow-hidden">
              <img
                src={pet.image}
                alt={pet.name}
                className="w-full h-96 object-cover"
              />
              <div className="p-6">
                <div className="flex justify-between items-start mb-4">
                  <div>
                    <h1 className="text-3xl font-bold text-gray-900 dark:text-white mb-2">
                      {pet.name}
                    </h1>
                    <p className="text-xl text-gray-600 dark:text-gray-400">
                      {pet.breed}
                    </p>
                  </div>
                  <button className="p-2 bg-white dark:bg-gray-800 rounded-full shadow-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors">
                    <Heart className="w-6 h-6 text-red-500" />
                  </button>
                </div>

                <div className="grid grid-cols-2 gap-4 mb-6">
                  <div className="text-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
                    <div className="text-2xl font-bold text-blue-600">{pet.age}</div>
                    <div className="text-sm text-gray-600 dark:text-gray-400">Months Old</div>
                  </div>
                  <div className="text-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
                    <div className="text-2xl font-bold text-blue-600 capitalize">{pet.gender.toLowerCase()}</div>
                    <div className="text-sm text-gray-600 dark:text-gray-400">Gender</div>
                  </div>
                </div>

                <div className="flex items-center space-x-4 mb-6">
                  <div className="flex items-center space-x-1">
                    <Star className="w-5 h-5 text-yellow-400 fill-current" />
                    <span className="text-lg font-semibold text-gray-900 dark:text-white">
                      {pet.rating}
                    </span>
                  </div>
                  <div className="flex items-center space-x-2">
                    <MapPin className="w-5 h-5 text-gray-400" />
                    <span className="text-gray-600 dark:text-gray-400">{pet.location}</span>
                  </div>
                </div>

                {pet.vaccinated && (
                  <div className="flex items-center space-x-2 mb-6 p-3 bg-green-50 dark:bg-green-900/20 rounded-lg">
                    <Shield className="w-5 h-5 text-green-600" />
                    <span className="text-green-800 dark:text-green-200 font-medium">
                      Vaccinated
                    </span>
                  </div>
                )}

                <div className="space-y-4">
                  <div>
                    <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">
                      Description
                    </h3>
                    <p className="text-gray-600 dark:text-gray-400">
                      {pet.description}
                    </p>
                  </div>

                  <div>
                    <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">
                      Medical History
                    </h3>
                    <p className="text-gray-600 dark:text-gray-400">
                      {pet.medicalHistory}
                    </p>
                  </div>

                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <h4 className="text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                        Father's Breed
                      </h4>
                      <p className="text-gray-600 dark:text-gray-400">{pet.fatherBreed}</p>
                    </div>
                    <div>
                      <h4 className="text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                        Mother's Breed
                      </h4>
                      <p className="text-gray-600 dark:text-gray-400">{pet.motherBreed}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Right Column - Price and Booking */}
          <div className="space-y-6">
            {/* Price Card */}
            <div className="card p-6">
              <div className="flex items-center justify-between mb-4">
                <h2 className="text-2xl font-bold text-gray-900 dark:text-white">
                  {formatPrice(pet.price)}
                </h2>
                <div className="text-sm text-gray-600 dark:text-gray-400">
                  Free shipping
                </div>
              </div>

              <button
                onClick={() => setShowBooking(true)}
                className="w-full btn-primary text-lg py-3 mb-4"
              >
                Book This Pet
              </button>

              <div className="text-center">
                <p className="text-sm text-gray-600 dark:text-gray-400">
                  Contact seller for more details
                </p>
              </div>
            </div>

            {/* Seller Information */}
            <div className="card p-6">
              <h3 className="text-lg font-semibold text-gray-900 dark:text-white mb-4">
                Seller Information
              </h3>
              
              <div className="space-y-3">
                <div className="flex items-center space-x-3">
                  <div className="w-10 h-10 bg-blue-600 rounded-full flex items-center justify-center">
                    <span className="text-white font-semibold">
                      {pet.seller.name.charAt(0)}
                    </span>
                  </div>
                  <div>
                    <div className="font-medium text-gray-900 dark:text-white">
                      {pet.seller.name}
                    </div>
                    <div className="flex items-center space-x-1">
                      <Star className="w-4 h-4 text-yellow-400 fill-current" />
                      <span className="text-sm text-gray-600 dark:text-gray-400">
                        {pet.seller.rating} rating
                      </span>
                    </div>
                  </div>
                </div>

                <div className="space-y-2">
                  <div className="flex items-center space-x-2 text-sm">
                    <Mail className="w-4 h-4 text-gray-400" />
                    <span className="text-gray-600 dark:text-gray-400">{pet.seller.email}</span>
                  </div>
                  <div className="flex items-center space-x-2 text-sm">
                    <Phone className="w-4 h-4 text-gray-400" />
                    <span className="text-gray-600 dark:text-gray-400">{pet.seller.phone}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Booking Modal */}
        {showBooking && (
          <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white dark:bg-gray-800 rounded-lg p-6 max-w-md w-full mx-4">
              <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-4">
                Book {pet.name}
              </h3>
              
              <div className="space-y-4">
                <div className="flex justify-between items-center p-3 bg-gray-50 dark:bg-gray-700 rounded-lg">
                  <span className="text-gray-600 dark:text-gray-400">Price:</span>
                  <span className="font-bold text-gray-900 dark:text-white">
                    {formatPrice(pet.price)}
                  </span>
                </div>
                
                <div className="flex justify-between items-center p-3 bg-gray-50 dark:bg-gray-700 rounded-lg">
                  <span className="text-gray-600 dark:text-gray-400">Processing Fee:</span>
                  <span className="font-bold text-gray-900 dark:text-white">$50.00</span>
                </div>
                
                <div className="border-t pt-3">
                  <div className="flex justify-between items-center">
                    <span className="text-lg font-semibold text-gray-900 dark:text-white">Total:</span>
                    <span className="text-lg font-bold text-blue-600">
                      {formatPrice(pet.price + 5000)}
                    </span>
                  </div>
                </div>
              </div>

              <div className="flex space-x-3 mt-6">
                <button
                  onClick={() => setShowBooking(false)}
                  className="flex-1 btn-secondary"
                >
                  Cancel
                </button>
                <button
                  onClick={handleBooking}
                  disabled={bookingLoading}
                  className="flex-1 btn-primary"
                >
                  {bookingLoading ? (
                    <div className="flex items-center space-x-2">
                      <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
                      <span>Processing...</span>
                    </div>
                  ) : (
                    <div className="flex items-center space-x-2">
                      <CreditCard className="w-4 h-4" />
                      <span>Pay Now</span>
                    </div>
                  )}
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

export default PetDetails 