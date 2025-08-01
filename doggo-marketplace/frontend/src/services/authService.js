import { adminApi, marketplaceApi } from './api'

// Admin Authentication Service
export const adminAuthService = {
  // Admin Login
  async login(email, password) {
    try {
      const response = await adminApi.post('/api/admin/login', {
        email,
        password
      })
      
      const { token, user } = response.data
      localStorage.setItem('admin_token', token)
      localStorage.setItem('admin_user', JSON.stringify(user))
      
      return { success: true, user, token }
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Admin login failed')
    }
  },

  // Admin Logout
  logout() {
    localStorage.removeItem('admin_token')
    localStorage.removeItem('admin_user')
  },

  // Get admin user data
  getAdminUser() {
    const user = localStorage.getItem('admin_user')
    return user ? JSON.parse(user) : null
  },

  // Check if admin is authenticated
  isAdminAuthenticated() {
    return !!localStorage.getItem('admin_token')
  },

  // Admin registration (if needed)
  async register(adminData) {
    try {
      const response = await adminApi.post('/api/admin/register', adminData)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Admin registration failed')
    }
  }
}

// User Authentication Service
export const userAuthService = {
  // User Login
  async login(email, password) {
    try {
      const response = await marketplaceApi.post('/api/auth/login', {
        email,
        password
      })
      
      const { token, user } = response.data
      localStorage.setItem('user_token', token)
      localStorage.setItem('user_data', JSON.stringify(user))
      
      return { success: true, user, token }
    } catch (error) {
      throw new Error(error.response?.data?.message || 'User login failed')
    }
  },

  // User Logout
  logout() {
    localStorage.removeItem('user_token')
    localStorage.removeItem('user_data')
  },

  // Get user data
  getUser() {
    const user = localStorage.getItem('user_data')
    return user ? JSON.parse(user) : null
  },

  // Check if user is authenticated
  isUserAuthenticated() {
    return !!localStorage.getItem('user_token')
  },

  // User registration
  async register(userData) {
    try {
      const response = await marketplaceApi.post('/api/auth/register', userData)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'User registration failed')
    }
  },

  // Update user profile
  async updateProfile(userData) {
    try {
      const response = await marketplaceApi.put('/api/auth/profile', userData)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Profile update failed')
    }
  }
} 