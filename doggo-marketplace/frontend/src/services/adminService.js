import { adminApi } from './api'

export const adminService = {
  // Get dashboard statistics
  async getDashboardStats() {
    try {
      const response = await adminApi.get('/api/admin/dashboard/stats')
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch dashboard stats')
    }
  },

  // Get all users
  async getAllUsers() {
    try {
      const response = await adminApi.get('/api/admin/users')
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch users')
    }
  },

  // Get all pets
  async getAllPets() {
    try {
      const response = await adminApi.get('/api/admin/pets')
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch pets')
    }
  },

  // Get all orders
  async getAllOrders() {
    try {
      const response = await adminApi.get('/api/admin/orders')
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch orders')
    }
  },

  // Delete pet (admin only)
  async deletePet(petId) {
    try {
      await adminApi.delete(`/api/admin/pets/${petId}`)
      return true
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to delete pet')
    }
  },

  // Delete user (admin only)
  async deleteUser(userId) {
    try {
      await adminApi.delete(`/api/admin/users/${userId}`)
      return true
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to delete user')
    }
  }
} 