using AdminApi.DTOs;
using AdminApi.Models;
using AdminApi.Services;
using Microsoft.AspNetCore.Mvc;

namespace AdminApi.Controllers
{
    [ApiController]
    [Route("api/admin")]
    public class AdminController : ControllerBase
    {
        private readonly AdminService _adminService;

        public AdminController(AdminService adminService)
        {
            _adminService = adminService;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] AdminLoginRequest request)
        {
            try
            {
                var admin = await _adminService.ValidateAdminAsync(request.Username, request.Password);
                if (admin == null)
                {
                    return BadRequest(new { error = "Invalid credentials" });
                }

                // Simply return user info without JWT token
                return Ok(new { 
                    message = "Login successful", 
                    user = admin,
                    // Store username in session or pass to frontend for subsequent requests
                    adminSession = admin.Email 
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("dashboard/stats")]
        public async Task<IActionResult> GetDashboardStats([FromQuery] string adminSession)
        {
            try
            {
                // Simple check: verify admin exists in database
                if (!await _adminService.IsValidAdminAsync(adminSession))
                {
                    return Unauthorized(new { error = "Invalid admin session" });
                }

                var stats = await _adminService.GetDashboardStatsAsync();
                return Ok(stats);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("users")]
        public async Task<IActionResult> GetAllUsers([FromQuery] string adminSession)
        {
            try
            {
                if (!await _adminService.IsValidAdminAsync(adminSession))
                {
                    return Unauthorized(new { error = "Invalid admin session" });
                }

                var users = await _adminService.GetAllUsersAsync();
                return Ok(users);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("pets")]
        public async Task<IActionResult> GetAllPets([FromQuery] string adminSession)
        {
            try
            {
                if (!await _adminService.IsValidAdminAsync(adminSession))
                {
                    return Unauthorized(new { error = "Invalid admin session" });
                }

                var pets = await _adminService.GetAllPetsAsync();
                return Ok(pets);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("orders")]
        public async Task<IActionResult> GetAllOrders([FromQuery] string adminSession)
        {
            try
            {
                if (!await _adminService.IsValidAdminAsync(adminSession))
                {
                    return Unauthorized(new { error = "Invalid admin session" });
                }

                var orders = await _adminService.GetAllOrdersAsync();
                return Ok(orders);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpDelete("pets/{id}")]
        public async Task<IActionResult> DeletePet(long id, [FromQuery] string adminSession)
        {
            try
            {
                if (!await _adminService.IsValidAdminAsync(adminSession))
                {
                    return Unauthorized(new { error = "Invalid admin session" });
                }

                var result = await _adminService.DeletePetAsync(id);
                if (!result)
                {
                    return NotFound(new { error = "Pet not found" });
                }
                return Ok(new { message = "Pet deleted successfully" });
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpDelete("users/{id}")]
        public async Task<IActionResult> DeleteUser(long id, [FromQuery] string adminSession)
        {
            try
            {
                if (!await _adminService.IsValidAdminAsync(adminSession))
                {
                    return Unauthorized(new { error = "Invalid admin session" });
                }

                var result = await _adminService.DeleteUserAsync(id);
                if (!result)
                {
                    return NotFound(new { error = "User not found or cannot be deleted" });
                }
                return Ok(new { message = "User deleted successfully" });
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }
    }
}
