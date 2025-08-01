import { Link } from 'react-router-dom'
import { Heart, MapPin, DollarSign, Star, Shield } from 'lucide-react'

const PetCard = ({ pet }) => {
  const formatPrice = (price) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 0,
    }).format(price / 100)
  }

  return (
    <div className="card overflow-hidden hover:shadow-lg transition-shadow duration-300">
      <div className="relative">
        <img
          src={pet.image}
          alt={pet.name}
          className="w-full h-48 object-cover"
        />
        <button className="absolute top-2 right-2 p-2 bg-white dark:bg-gray-800 rounded-full shadow-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors">
          <Heart className="w-5 h-5 text-red-500" />
        </button>
        {pet.vaccinated && (
          <div className="absolute top-2 left-2 flex items-center space-x-1 bg-green-500 text-white px-2 py-1 rounded-full text-xs">
            <Shield className="w-3 h-3" />
            <span>Vaccinated</span>
          </div>
        )}
      </div>
      
      <div className="p-4">
        <div className="flex justify-between items-start mb-2">
          <h3 className="text-lg font-semibold text-gray-900 dark:text-white">
            {pet.name}
          </h3>
          <div className="flex items-center space-x-1">
            <Star className="w-4 h-4 text-yellow-400 fill-current" />
            <span className="text-sm text-gray-600 dark:text-gray-400">
              {pet.rating}
            </span>
          </div>
        </div>
        
        <p className="text-gray-600 dark:text-gray-400 mb-2">
          {pet.breed}
        </p>
        
        <div className="flex items-center space-x-4 text-sm text-gray-500 dark:text-gray-400 mb-3">
          <span>{pet.age} months old</span>
          <span className="capitalize">{pet.gender.toLowerCase()}</span>
        </div>
        
        <div className="flex items-center space-x-2 text-sm text-gray-500 dark:text-gray-400 mb-3">
          <MapPin className="w-4 h-4" />
          <span>{pet.location}</span>
        </div>
        
        <div className="flex justify-between items-center">
          <div className="flex items-center space-x-1">
            <DollarSign className="w-4 h-4 text-green-600" />
            <span className="text-lg font-bold text-green-600">
              {formatPrice(pet.price)}
            </span>
          </div>
          <Link
            to={`/pet/${pet.id}`}
            className="btn-primary text-sm px-4 py-2"
          >
            View Details
          </Link>
        </div>
      </div>
    </div>
  )
}

export default PetCard 