using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AdminApi.Models
{
    public class Order
    {
        [Key]
        public long Id { get; set; }

        [Required]
        [Column("buyer_id")]
        public long BuyerId { get; set; }

        [ForeignKey("BuyerId")]
        public User Buyer { get; set; } = null!;

        [Required]
        [Column("pet_id")]
        public long PetId { get; set; }

        [ForeignKey("PetId")]
        public Pet Pet { get; set; } = null!;

        [Required]
        [Column("total_amount_cents")]
        public int TotalAmountCents { get; set; }

        [Required]
        [Column("processing_fee_cents")]
        public int ProcessingFeeCents { get; set; } = 5000; // $50.00

        [Required]
        public OrderStatus Status { get; set; } = OrderStatus.PENDING;

        [Column("payment_method")]
        public string? PaymentMethod { get; set; }

        [Column("transaction_id")]
        public string? TransactionId { get; set; }

        [Column("created_at")]
        public DateTime CreatedAt { get; set; } = DateTime.UtcNow;

        [Column("updated_at")]
        public DateTime UpdatedAt { get; set; } = DateTime.UtcNow;
    }

    public enum OrderStatus
    {
        PENDING,
        PAID,
        COMPLETED,
        CANCELLED
    }
} 