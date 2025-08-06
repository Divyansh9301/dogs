using Microsoft.EntityFrameworkCore;
using PetService.Models;

namespace PetService.Data
{
    public class PetServiceDbContext : DbContext
    {
        public PetServiceDbContext(DbContextOptions<PetServiceDbContext> options) : base(options)
        {
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Role> Roles { get; set; }
        public DbSet<State> States { get; set; }
        public DbSet<City> Cities { get; set; }
        public DbSet<Breed> Breeds { get; set; }
        public DbSet<Pet> Pets { get; set; }
        public DbSet<BookPet> BookPets { get; set; }
        public DbSet<Bill> Bills { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Configure User relationships
            modelBuilder.Entity<User>()
                .HasOne(u => u.City)
                .WithMany(c => c.Users)
                .HasForeignKey(u => u.CityId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<User>()
                .HasOne(u => u.Role)
                .WithMany(r => r.Users)
                .HasForeignKey(u => u.RoleId)
                .OnDelete(DeleteBehavior.Restrict);

            // Configure Pet relationships
            modelBuilder.Entity<Pet>()
                .HasOne(p => p.Seller)
                .WithMany(u => u.Pets)
                .HasForeignKey(p => p.SellerId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<Pet>()
                .HasOne(p => p.FatherBreed)
                .WithMany(b => b.FatherBreedPets)
                .HasForeignKey(p => p.FatherBreedId)
                .OnDelete(DeleteBehavior.SetNull);

            modelBuilder.Entity<Pet>()
                .HasOne(p => p.MotherBreed)
                .WithMany(b => b.MotherBreedPets)
                .HasForeignKey(p => p.MotherBreedId)
                .OnDelete(DeleteBehavior.SetNull);

            // Configure BookPet relationships
            modelBuilder.Entity<BookPet>()
                .HasOne(bp => bp.Pet)
                .WithMany(p => p.BookPets)
                .HasForeignKey(bp => bp.PetId)
                .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<BookPet>()
                .HasOne(bp => bp.Buyer)
                .WithMany()
                .HasForeignKey(bp => bp.BuyerId)
                .OnDelete(DeleteBehavior.Restrict);

            // Configure Bill relationships
            modelBuilder.Entity<Bill>()
                .HasOne(b => b.Booking)
                .WithMany(bp => bp.Bills)
                .HasForeignKey(b => b.BookingId)
                .OnDelete(DeleteBehavior.Cascade);

            // Configure City-State relationship
            modelBuilder.Entity<City>()
                .HasOne(c => c.State)
                .WithMany(s => s.Cities)
                .HasForeignKey(c => c.StateId)
                .OnDelete(DeleteBehavior.Restrict);

            // Configure enum conversions
            modelBuilder.Entity<Pet>()
                .Property(p => p.Gender)
                .HasConversion<string>();

            modelBuilder.Entity<BookPet>()
                .Property(bp => bp.Status)
                .HasConversion<string>();
        }
    }
} 