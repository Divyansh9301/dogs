using System.ComponentModel.DataAnnotations;
using PetService.Models;

namespace PetService.DTOs
{
    public class PetCreateDto
    {
        [Required]
        [StringLength(100)]
        public string PetName { get; set; } = string.Empty;

        [Required]
        [Range(1, 200)]
        public int PetAge { get; set; }

        [Required]
        public PetGender Gender { get; set; }

        [Required]
        public string PetPhoto { get; set; } = string.Empty;

        public int? FatherBreedId { get; set; }

        public int? MotherBreedId { get; set; }

        [Required]
        public int SellerId { get; set; }

        [Required]
        public bool IsVaccinated { get; set; }

        [Required]
        [Range(0.01, float.MaxValue)]
        public float Price { get; set; }

        public string? Description { get; set; }
    }
} 