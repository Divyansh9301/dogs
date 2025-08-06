using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
    [Table("BookPet")]
    public class BookPet
    {
        [Key]
        [Column("bk_id")]
        public int BookId { get; set; }

        [Required]
        [Column("p_id")]
        public int PetId { get; set; }

        [Required]
        [Column("Price")]
        public float Price { get; set; }

        [Required]
        [Column("date")]
        public DateOnly Date { get; set; }

        [Required]
        [Column("time")]
        public TimeOnly Time { get; set; }

        [Required]
        [Column("buyer_id")]
        public int BuyerId { get; set; }

        [Column("status")]
        public BookingStatus Status { get; set; } = BookingStatus.PENDING;

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        // Navigation properties
        [ForeignKey("PetId")]
        public Pet? Pet { get; set; }

        [ForeignKey("BuyerId")]
        public User? Buyer { get; set; }

        public ICollection<Bill> Bills { get; set; } = new List<Bill>();
    }

    public enum BookingStatus
    {
        PENDING,
        CONFIRMED,
        COMPLETED,
        CANCELLED
    }
} 