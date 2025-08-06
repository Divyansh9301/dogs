using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AdminApi.Models
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
        [Column("email_id")]
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
    }

    [Table("Role")]
    public class Role
    {
        [Key]
        [Column("roleid")]
        public int RoleId { get; set; }

        [Required]
        [StringLength(20)]
        [Column("rolename")]
        public string RoleName { get; set; } = string.Empty;
    }

    [Table("City")]
    public class City
    {
        [Key]
        [Column("cityid")]
        public int CityId { get; set; }

        [Required]
        [StringLength(50)]
        [Column("cityname")]
        public string CityName { get; set; } = string.Empty;

        [Required]
        [Column("stid")]
        public int StateId { get; set; }

        public State? State { get; set; }
    }

    [Table("State")]
    public class State
    {
        [Key]
        [Column("stid")]
        public int StateId { get; set; }

        [Required]
        [StringLength(100)]
        [Column("stname")]
        public string StateName { get; set; } = string.Empty;
    }
} 