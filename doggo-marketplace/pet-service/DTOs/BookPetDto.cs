using System.ComponentModel.DataAnnotations;

namespace PetService.DTOs
{
    public class BookPetDto
    {
        [Required]
        public int PetId { get; set; }

        [Required]
        public int BuyerId { get; set; }

        [Required]
        [Range(0.01, float.MaxValue)]
        public float Price { get; set; }

        [Required]
        public DateOnly Date { get; set; }

        [Required]
        public TimeOnly Time { get; set; }
    }
} 