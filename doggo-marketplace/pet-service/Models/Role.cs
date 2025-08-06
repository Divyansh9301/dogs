using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
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

        // Navigation properties
        public ICollection<User> Users { get; set; } = new List<User>();
    }
} 