import { marketplaceApi } from './api'

export const orderService = {
  // Create order (buy pet)
  async createOrder(orderData, buyerId) {
    try {
      const response = await marketplaceApi.post(`/api/orders?buyerId=${buyerId}`, orderData)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to create order')
    }
  },

  // Get order by ID
  async getOrderById(id) {
    try {
      const response = await marketplaceApi.get(`/api/orders/${id}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch order')
    }
  },

  // Get orders by buyer
  async getOrdersByBuyer(buyerId) {
    try {
      const response = await marketplaceApi.get(`/api/orders/buyer/${buyerId}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch buyer orders')
    }
  },

  // Get orders by seller
  async getOrdersBySeller(sellerId) {
    try {
      const response = await marketplaceApi.get(`/api/orders/seller/${sellerId}`)
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to fetch seller orders')
    }
  },

  // Process payment
  async processPayment(orderId, transactionId) {
    try {
      const response = await marketplaceApi.post(`/api/orders/${orderId}/payment`, {
        transactionId
      })
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to process payment')
    }
  },

  // Update order status
  async updateOrderStatus(orderId, status) {
    try {
      const response = await marketplaceApi.put(`/api/orders/${orderId}/status`, {
        status
      })
      return response.data
    } catch (error) {
      throw new Error(error.response?.data?.error || 'Failed to update order status')
    }
  }
} 