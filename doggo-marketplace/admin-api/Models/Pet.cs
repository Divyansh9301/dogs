using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AdminApi.Models
{
    public class Pet
    {
        [Key]
        public long Id { get; set; }

        [Required]
        [StringLength(64)]
        public string Name { get; set; } = string.Empty;

        [Required]
        [StringLength(64)]
        public string Breed { get; set; } = string.Empty;

        [Column("father_breed")]
        [StringLength(64)]
        public string? FatherBreed { get; set; }

        [Column("mother_breed")]
        [StringLength(64)]
        public string? MotherBreed { get; set; }

        [Required]
        [Column("age_months")]
        public int AgeMonths { get; set; }

        [Required]
        public Gender Gender { get; set; }

        [Required]
        public bool Vaccinated { get; set; }

        [Required]
        [Column("price_cents")]
        public int PriceCents { get; set; }

        [Column("seller_id")]
        public long? SellerId { get; set; }

        [ForeignKey("SellerId")]
        public User? Seller { get; set; }

        [Required]
        [Column("sold_out")]
        public bool SoldOut { get; set; } = false;

        [Column("img_url")]
        [StringLength(256)]
        public string? ImgUrl { get; set; }

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        [Column("medical_history")]
        public string? MedicalHistory { get; set; }

        public string? Description { get; set; }

        public string? Location { get; set; }
    }

    public enum Gender
    {
        MALE,
        FEMALE
    }
} 