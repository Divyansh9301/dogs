import { marketplaceApi } from './api'

export const petService = {
  // Get all available pets
  async getAllPets() {
    try {
      const response = await marketplaceApi.get('/api/pets')
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch pets')
    }
  },

  // Get pet by ID
  async getPetById(id) {
    try {
      const response = await marketplaceApi.get(`/api/pets/${id}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch pet')
    }
  },

  // Create new pet listing
  async createPet(petData, sellerId) {
    try {
      const response = await marketplaceApi.post(`/api/pets?sellerId=${sellerId}`, petData)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to create pet listing')
    }
  },

  // Update pet listing
  async updatePet(id, petData, sellerId) {
    try {
      const response = await marketplaceApi.put(`/api/pets/${id}?sellerId=${sellerId}`, petData)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to update pet listing')
    }
  },

  // Delete pet listing
  async deletePet(id, sellerId) {
    try {
      await marketplaceApi.delete(`/api/pets/${id}?sellerId=${sellerId}`)
      return true
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to delete pet listing')
    }
  },

  // Search pets
  async searchPets(searchTerm) {
    try {
      const response = await marketplaceApi.get(`/api/pets/search?q=${encodeURIComponent(searchTerm)}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to search pets')
    }
  },

  // Get pets by breed
  async getPetsByBreed(breed) {
    try {
      const response = await marketplaceApi.get(`/api/pets/breed/${encodeURIComponent(breed)}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch pets by breed')
    }
  },

  // Get pets by seller
  async getPetsBySeller(sellerId) {
    try {
      const response = await marketplaceApi.get(`/api/pets/seller/${sellerId}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch pets by seller')
    }
  }
} 