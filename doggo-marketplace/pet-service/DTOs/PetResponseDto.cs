using PetService.Models;

namespace PetService.DTOs
{
    public class PetResponseDto
    {
        public int PetId { get; set; }
        public string PetName { get; set; } = string.Empty;
        public int PetAge { get; set; }
        public PetGender Gender { get; set; }
        public string PetPhoto { get; set; } = string.Empty;
        public int? FatherBreedId { get; set; }
        public string? FatherBreedName { get; set; }
        public int? MotherBreedId { get; set; }
        public string? MotherBreedName { get; set; }
        public int SellerId { get; set; }
        public string? SellerName { get; set; }
        public bool IsVaccinated { get; set; }
        public float Price { get; set; }
        public string? Description { get; set; }
        public bool IsAvailable { get; set; }
        public DateTime CreatedAt { get; set; }
        public string? CrossBreed { get; set; } // If father and mother breeds are different
    }
} 