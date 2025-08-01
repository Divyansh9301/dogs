import { create } from 'zustand'
import { persist } from 'zustand/middleware'
import { adminAuthService, userAuthService } from '../services/authService'

export const useAuthStore = create(
  persist(
    (set, get) => ({
      // User authentication state
      user: null,
      userToken: null,
      isUserAuthenticated: false,
      
      // Admin authentication state
      admin: null,
      adminToken: null,
      isAdminAuthenticated: false,
      
      // User authentication actions
      userLogin: async (email, password) => {
        try {
          const result = await userAuthService.login(email, password)
          set({
            user: result.user,
            userToken: result.token,
            isUserAuthenticated: true
          })
          return { success: true }
        } catch (error) {
          return { success: false, error: error.message }
        }
      },
      
      userLogout: () => {
        userAuthService.logout()
        set({
          user: null,
          userToken: null,
          isUserAuthenticated: false
        })
      },
      
      userRegister: async (userData) => {
        try {
          const result = await userAuthService.register(userData)
          return { success: true, data: result }
        } catch (error) {
          return { success: false, error: error.message }
        }
      },
      
      updateUser: (userData) => set((state) => ({
        user: { ...state.user, ...userData }
      })),
      
      // Admin authentication actions
      adminLogin: async (email, password) => {
        try {
          const result = await adminAuthService.login(email, password)
          set({
            admin: result.user,
            adminToken: result.token,
            isAdminAuthenticated: true
          })
          return { success: true }
        } catch (error) {
          return { success: false, error: error.message }
        }
      },
      
      adminLogout: () => {
        adminAuthService.logout()
        set({
          admin: null,
          adminToken: null,
          isAdminAuthenticated: false
        })
      },
      
      adminRegister: async (adminData) => {
        try {
          const result = await adminAuthService.register(adminData)
          return { success: true, data: result }
        } catch (error) {
          return { success: false, error: error.message }
        }
      },
      
      updateAdmin: (adminData) => set((state) => ({
        admin: { ...state.admin, ...adminData }
      })),
      
      // Initialize auth state from localStorage
      initializeAuth: () => {
        const user = userAuthService.getUser()
        const admin = adminAuthService.getAdminUser()
        
        set({
          user: user,
          userToken: localStorage.getItem('user_token'),
          isUserAuthenticated: userAuthService.isUserAuthenticated(),
          admin: admin,
          adminToken: localStorage.getItem('admin_token'),
          isAdminAuthenticated: adminAuthService.isAdminAuthenticated()
        })
      },
      
      // Check if any user is authenticated
      isAuthenticated: () => {
        const state = get()
        return state.isUserAuthenticated || state.isAdminAuthenticated
      },
      
      // Get current user (either regular user or admin)
      getCurrentUser: () => {
        const state = get()
        return state.isAdminAuthenticated ? state.admin : state.user
      },
      
      // Get current user type
      getUserType: () => {
        const state = get()
        if (state.isAdminAuthenticated) return 'admin'
        if (state.isUserAuthenticated) return 'user'
        return null
      }
    }),
    {
      name: 'auth-storage',
      partialize: (state) => ({
        user: state.user,
        admin: state.admin,
        isUserAuthenticated: state.isUserAuthenticated,
        isAdminAuthenticated: state.isAdminAuthenticated
      })
    }
  )
) 