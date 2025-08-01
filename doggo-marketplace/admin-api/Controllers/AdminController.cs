using AdminApi.DTOs;
using AdminApi.Models;
using AdminApi.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace AdminApi.Controllers
{
    [ApiController]
    [Route("api/admin")]
    [AllowAnonymous]
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
                var admin = await _adminService.LoginAsync(request);
                if (admin == null)
                {
                    return BadRequest(new { error = "Invalid credentials" });
                }

                var token = _adminService.GenerateJwtToken(admin);
                return Ok(new { token, user = admin });
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("dashboard/stats")]
        [Authorize(Roles = "ADMIN")]
        public async Task<IActionResult> GetDashboardStats()
        {
            try
            {
                var stats = await _adminService.GetDashboardStatsAsync();
                return Ok(stats);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("users")]
        [Authorize(Roles = "ADMIN")]
        public async Task<IActionResult> GetAllUsers()
        {
            try
            {
                var users = await _adminService.GetAllUsersAsync();
                return Ok(users);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("pets")]
        [Authorize(Roles = "ADMIN")]
        public async Task<IActionResult> GetAllPets()
        {
            try
            {
                var pets = await _adminService.GetAllPetsAsync();
                return Ok(pets);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpGet("orders")]
        [Authorize(Roles = "ADMIN")]
        public async Task<IActionResult> GetAllOrders()
        {
            try
            {
                var orders = await _adminService.GetAllOrdersAsync();
                return Ok(orders);
            }
            catch (Exception ex)
            {
                return BadRequest(new { error = ex.Message });
            }
        }

        [HttpDelete("pets/{id}")]
        [Authorize(Roles = "ADMIN")]
        public async Task<IActionResult> DeletePet(long id)
        {
            try
            {
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
        [Authorize(Roles = "ADMIN")]
        public async Task<IActionResult> DeleteUser(long id)
        {
            try
            {
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