using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PetService.Models
{
    [Table("Bill")]
    public class Bill
    {
        [Key]
        [Column("blid")]
        public int BillId { get; set; }

        [Required]
        [Column("useracc")]
        public long UserAccount { get; set; }

        [Required]
        [Column("Amt")]
        public float Amount { get; set; }

        [Required]
        [Column("date")]
        public DateOnly Date { get; set; }

        [Required]
        [Column("bk_id")]
        public int BookingId { get; set; }

        [StringLength(50)]
        [Column("payment_method")]
        public string? PaymentMethod { get; set; }

        [StringLength(255)]
        [Column("transaction_id")]
        public string? TransactionId { get; set; }

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        // Navigation properties
        [ForeignKey("BookingId")]
        public BookPet? Booking { get; set; }
    }
} 