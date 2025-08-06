using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
    [Table("Pet")]
    public class Pet
    {
        [Key]
        [Column("p_id")]
        public int PetId { get; set; }

        [Required]
        [StringLength(100)]
        [Column("p_name")]
        public string PetName { get; set; } = string.Empty;

        [Required]
        [Column("p_age")]
        public int PetAge { get; set; }

        [Required]
        [Column("gender")]
        public PetGender Gender { get; set; }

        [Required]
        [StringLength(225)]
        [Column("p_photo")]
        public string PetPhoto { get; set; } = string.Empty;

        [Column("fid")]
        public int? FatherBreedId { get; set; }

        [Column("mid")]
        public int? MotherBreedId { get; set; }

        [Required]
        [Column("seller_id")]
        public int SellerId { get; set; }

        [Required]
        [Column("is_vaccinated")]
        public bool IsVaccinated { get; set; } = false;

        [Required]
        [Column("price")]
        public float Price { get; set; }

        [Column("description")]
        public string? Description { get; set; }

        [Column("is_available")]
        public bool IsAvailable { get; set; } = true;

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        // Navigation properties
        [ForeignKey("FatherBreedId")]
        public Breed? FatherBreed { get; set; }

        [ForeignKey("MotherBreedId")]
        public Breed? MotherBreed { get; set; }

        [ForeignKey("SellerId")]
        public User? Seller { get; set; }

        public ICollection<BookPet> BookPets { get; set; } = new List<BookPet>();
    }

    public enum PetGender
    {
        Male,
        Female
    }
} 