using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
    [Table("Breed")]
    public class Breed
    {
        [Key]
        [Column("bid")]
        public int BreedId { get; set; }

        [Required]
        [StringLength(100)]
        [Column("bname")]
        public string BreedName { get; set; } = string.Empty;

        // Navigation properties
        public ICollection<Pet> FatherBreedPets { get; set; } = new List<Pet>();
        public ICollection<Pet> MotherBreedPets { get; set; } = new List<Pet>();
    }
} 