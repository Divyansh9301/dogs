import { useState } from 'react'
import { Search, Heart, Info } from 'lucide-react'

const Breeds = () => {
  const [searchTerm, setSearchTerm] = useState('')
  const [selectedSize, setSelectedSize] = useState('')

  const breeds = [
    {
      id: 1,
      name: 'Golden Retriever',
      category: 'Sporting',
      size: 'Large',
      lifespan: '10-12 years',
      temperament: 'Friendly, Intelligent, Devoted',
      description: 'Golden Retrievers are known for their friendly and tolerant attitudes. They are great family dogs and are very intelligent.',
      image: 'https://images.unsplash.com/photo-1552053831-71594a27632d?w=400'
    },
    {
      id: 2,
      name: 'Labrador Retriever',
      category: 'Sporting',
      size: 'Large',
      lifespan: '10-12 years',
      temperament: 'Friendly, Active, Outgoing',
      description: 'Labradors are one of the most popular dog breeds. They are excellent family pets and working dogs.',
      image: 'https://images.unsplash.com/photo-1546527868-ccb7ee7dfa6a?w=400'
    },
    {
      id: 3,
      name: 'German Shepherd',
      category: 'Herding',
      size: 'Large',
      lifespan: '7-10 years',
      temperament: 'Loyal, Courageous, Confident',
      description: 'German Shepherds are intelligent and capable working dogs. They are often used in police and military work.',
      image: 'https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400'
    },
    {
      id: 4,
      name: 'Bulldog',
      category: 'Non-Sporting',
      size: 'Medium',
      lifespan: '8-10 years',
      temperament: 'Friendly, Calm, Willful',
      description: 'Bulldogs are known for their wrinkled face and pushed-in nose. They are gentle and affectionate.',
      image: 'https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=400'
    },
    {
      id: 5,
      name: 'Beagle',
      category: 'Hound',
      size: 'Medium',
      lifespan: '12-15 years',
      temperament: 'Friendly, Curious, Merry',
      description: 'Beagles are small to medium-sized dogs known for their excellent sense of smell and tracking abilities.',
      image: 'https://images.unsplash.com/photo-1507146426996-ef05306b995a?w=400'
    },
    {
      id: 6,
      name: 'Poodle',
      category: 'Non-Sporting',
      size: 'Medium',
      lifespan: '12-15 years',
      temperament: 'Active, Proud, Very Smart',
      description: 'Poodles are highly intelligent and come in three sizes: standard, miniature, and toy.',
      image: 'https://images.unsplash.com/photo-1591946614720-90a587da4a36?w=400'
    }
  ]

  const sizes = ['Small', 'Medium', 'Large']

  const filteredBreeds = breeds.filter(breed => {
    const matchesSearch = breed.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         breed.category.toLowerCase().includes(searchTerm.toLowerCase())
    const matchesSize = !selectedSize || breed.size === selectedSize
    return matchesSearch && matchesSize
  })

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
      {/* Header */}
      <div className="bg-white dark:bg-gray-800 shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
          <h1 className="text-3xl font-bold text-gray-900 dark:text-white mb-4">
            Dog Breeds
          </h1>
          <p className="text-gray-600 dark:text-gray-400">
            Explore different dog breeds and learn about their characteristics, temperament, and care requirements.
          </p>
        </div>
      </div>

      {/* Search and Filters */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-6 mb-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div className="relative">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="text"
                placeholder="Search breeds..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="input-field pl-10"
              />
            </div>
            <select
              value={selectedSize}
              onChange={(e) => setSelectedSize(e.target.value)}
              className="input-field"
            >
              <option value="">All Sizes</option>
              {sizes.map(size => (
                <option key={size} value={size}>{size}</option>
              ))}
            </select>
            <button className="btn-primary">
              Search
            </button>
          </div>
        </div>

        {/* Breeds Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredBreeds.map(breed => (
            <div key={breed.id} className="card overflow-hidden">
              <div className="relative">
                <img
                  src={breed.image}
                  alt={breed.name}
                  className="w-full h-48 object-cover"
                />
                <button className="absolute top-2 right-2 p-2 bg-white dark:bg-gray-800 rounded-full shadow-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors">
                  <Heart className="w-5 h-5 text-red-500" />
                </button>
              </div>
              
              <div className="p-6">
                <h3 className="text-xl font-semibold text-gray-900 dark:text-white mb-2">
                  {breed.name}
                </h3>
                
                <div className="space-y-2 mb-4">
                  <div className="flex justify-between text-sm">
                    <span className="text-gray-600 dark:text-gray-400">Category:</span>
                    <span className="text-gray-900 dark:text-white font-medium">{breed.category}</span>
                  </div>
                  <div className="flex justify-between text-sm">
                    <span className="text-gray-600 dark:text-gray-400">Size:</span>
                    <span className="text-gray-900 dark:text-white font-medium">{breed.size}</span>
                  </div>
                  <div className="flex justify-between text-sm">
                    <span className="text-gray-600 dark:text-gray-400">Lifespan:</span>
                    <span className="text-gray-900 dark:text-white font-medium">{breed.lifespan}</span>
                  </div>
                </div>
                
                <div className="mb-4">
                  <h4 className="text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                    Temperament
                  </h4>
                  <p className="text-sm text-gray-600 dark:text-gray-400">
                    {breed.temperament}
                  </p>
                </div>
                
                <div className="mb-4">
                  <h4 className="text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                    Description
                  </h4>
                  <p className="text-sm text-gray-600 dark:text-gray-400 line-clamp-3">
                    {breed.description}
                  </p>
                </div>
                
                <button className="w-full btn-secondary text-sm">
                  <Info className="w-4 h-4 inline mr-2" />
                  Learn More
                </button>
              </div>
            </div>
          ))}
        </div>

        {filteredBreeds.length === 0 && (
          <div className="text-center py-12">
            <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">
              No breeds found
            </h3>
            <p className="text-gray-600 dark:text-gray-400">
              Try adjusting your search criteria
            </p>
          </div>
        )}
      </div>
    </div>
  )
}

export default Breeds 