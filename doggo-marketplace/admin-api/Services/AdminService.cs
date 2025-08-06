using AdminApi.Data;
using AdminApi.DTOs;
using AdminApi.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;
using BC = BCrypt.Net.BCrypt;

namespace AdminApi.Services
{
    public class AdminService
    {
        private readonly ApplicationDbContext _context;
        private readonly IConfiguration _configuration;

        public AdminService(ApplicationDbContext context, IConfiguration configuration)
        {
            _context = context;
            _configuration = configuration;
        }

        public async Task<AdminResponse?> LoginAsync(AdminLoginRequest request)
        {
            var admin = await _context.Users
                .Include(u => u.Role)
                .FirstOrDefaultAsync(u => u.Email == request.Email && u.Role.RoleName == "Admin");

            if (admin == null || !VerifyPassword(request.Password, admin.Password))
            {
                return null;
            }

            return new AdminResponse
            {
                Id = admin.UserId,
                Email = admin.Email,
                Name = admin.Username,
                Role = admin.Role?.RoleName ?? "Admin"
            };
        }

        public string GenerateJwtToken(AdminResponse admin)
        {
            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Jwt:Key"] ?? "your-secret-key-here"));
            var credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            var claims = new[]
            {
                new Claim(ClaimTypes.NameIdentifier, admin.Id.ToString()),
                new Claim(ClaimTypes.Email, admin.Email),
                new Claim(ClaimTypes.Name, admin.Name),
                new Claim(ClaimTypes.Role, admin.Role)
            };

            var token = new JwtSecurityToken(
                issuer: _configuration["Jwt:Issuer"],
                audience: _configuration["Jwt:Audience"],
                claims: claims,
                expires: DateTime.UtcNow.AddHours(24),
                signingCredentials: credentials
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }

        private bool VerifyPassword(string inputPassword, string hashedPassword)
        {
            try
            {
                // For demo purposes, check if it's the demo password
                if (inputPassword == "password123" && hashedPassword == "password123")
                {
                    return true;
                }
                
                // In production, use BCrypt verification
                return BC.Verify(inputPassword, hashedPassword);
            }
            catch
            {
                return false;
            }
        }

        public async Task<List<User>> GetAllUsersAsync()
        {
            return await _context.Users.ToListAsync();
        }

        public async Task<List<Pet>> GetAllPetsAsync()
        {
            return await _context.Pets
                .Include(p => p.Seller)
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .ToListAsync();
        }

        public async Task<List<BookPet>> GetAllOrdersAsync()
        {
            return await _context.BookPets
                .Include(o => o.Buyer)
                .Include(o => o.Pet)
                .ToListAsync();
        }

        public async Task<bool> DeletePetAsync(long petId)
        {
            var pet = await _context.Pets.FindAsync((int)petId);
            if (pet == null)
                return false;

            _context.Pets.Remove(pet);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> DeleteUserAsync(long userId)
        {
            var user = await _context.Users
                .Include(u => u.Role)
                .FirstOrDefaultAsync(u => u.UserId == (int)userId);
            
            if (user == null || user.Role?.RoleName == "Admin")
                return false;

            _context.Users.Remove(user);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<Dictionary<string, int>> GetDashboardStatsAsync()
        {
            var totalUsers = await _context.Users.CountAsync();
            var totalPets = await _context.Pets.CountAsync();
            var totalOrders = await _context.BookPets.CountAsync();
            var totalRevenue = await _context.BookPets
                .Where(o => o.Status == "COMPLETED")
                .SumAsync(o => (int)o.Price);

            return new Dictionary<string, int>
            {
                ["totalUsers"] = totalUsers,
                ["totalPets"] = totalPets,
                ["totalOrders"] = totalOrders,
                ["totalRevenue"] = totalRevenue
            };
        }
    }
} 