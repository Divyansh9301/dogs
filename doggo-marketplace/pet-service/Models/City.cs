using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
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

        // Navigation properties
        [ForeignKey("StateId")]
        public State? State { get; set; }
        public ICollection<User> Users { get; set; } = new List<User>();
    }
} 