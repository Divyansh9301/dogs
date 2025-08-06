using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
    [Table("User")]
    public class User
    {
        [Key]
        [Column("userid")]
        public int UserId { get; set; }

        [Required]
        [StringLength(50)]
        [Column("username")]
        public string Username { get; set; } = string.Empty;

        [Required]
        [StringLength(50)]
        [Column("email")]
        public string Email { get; set; } = string.Empty;

        [Required]
        [StringLength(50)]
        [Column("contact_no")]
        public string ContactNo { get; set; } = string.Empty;

        [Required]
        [StringLength(50)]
        [Column("address")]
        public string Address { get; set; } = string.Empty;

        [Required]
        [Column("aadhar_no")]
        public long AadharNo { get; set; }

        [Required]
        [Column("cityid")]
        public int CityId { get; set; }

        [Required]
        [Column("roleid")]
        public int RoleId { get; set; }

        [Required]
        [StringLength(255)]
        [Column("password")]
        public string Password { get; set; } = string.Empty;

        [StringLength(255)]
        [Column("security_question")]
        public string? SecurityQuestion { get; set; }

        [StringLength(255)]
        [Column("security_answer")]
        public string? SecurityAnswer { get; set; }

        [StringLength(50)]
        [Column("first_name")]
        public string? FirstName { get; set; }

        [StringLength(50)]
        [Column("last_name")]
        public string? LastName { get; set; }

        [Column("is_active")]
        public bool IsActive { get; set; } = true;

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        // Navigation properties
        public City? City { get; set; }
        public Role? Role { get; set; }
        public ICollection<Pet> Pets { get; set; } = new List<Pet>();
    }
} 