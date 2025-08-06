using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
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

        // Navigation properties
        public ICollection<City> Cities { get; set; } = new List<City>();
    }
} 