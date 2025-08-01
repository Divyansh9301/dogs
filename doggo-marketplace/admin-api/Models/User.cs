using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AdminApi.Models
{
    public class User
    {
        [Key]
        public long Id { get; set; }

        [Required]
        [EmailAddress]
        [StringLength(128)]
        public string Email { get; set; } = string.Empty;

        [Required]
        [StringLength(60)]
        public string Password { get; set; } = string.Empty;

        [Required]
        [StringLength(100)]
        public string Name { get; set; } = string.Empty;

        [Required]
        public UserRole Role { get; set; } = UserRole.USER;

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
    }

    public enum UserRole
    {
        USER,
        ADMIN
    }
} 