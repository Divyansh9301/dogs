using Microsoft.EntityFrameworkCore;
using PetService.Data;
using PetService.DTOs;
using PetService.Models;

namespace PetService.Services
{
    public class PetService
    {
        private readonly PetServiceDbContext _context;

        public PetService(PetServiceDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<PetResponseDto>> GetAllPetsAsync()
        {
            var pets = await _context.Pets
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .Include(p => p.Seller)
                .Where(p => p.IsAvailable)
                .ToListAsync();

            return pets.Select(MapToPetResponseDto);
        }

        public async Task<PetResponseDto?> GetPetByIdAsync(int id)
        {
            var pet = await _context.Pets
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .Include(p => p.Seller)
                .FirstOrDefaultAsync(p => p.PetId == id);

            return pet != null ? MapToPetResponseDto(pet) : null;
        }

        public async Task<IEnumerable<PetResponseDto>> GetPetsBySellerAsync(int sellerId)
        {
            var pets = await _context.Pets
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .Include(p => p.Seller)
                .Where(p => p.SellerId == sellerId)
                .ToListAsync();

            return pets.Select(MapToPetResponseDto);
        }

        public async Task<IEnumerable<PetResponseDto>> SearchPetsAsync(string searchTerm)
        {
            var pets = await _context.Pets
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .Include(p => p.Seller)
                .Where(p => p.IsAvailable && 
                           (p.PetName.Contains(searchTerm) ||
                            (p.FatherBreed != null && p.FatherBreed.BreedName.Contains(searchTerm)) ||
                            (p.MotherBreed != null && p.MotherBreed.BreedName.Contains(searchTerm))))
                .ToListAsync();

            return pets.Select(MapToPetResponseDto);
        }

        public async Task<PetResponseDto> CreatePetAsync(PetCreateDto createDto)
        {
            var pet = new Pet
            {
                PetName = createDto.PetName,
                PetAge = createDto.PetAge,
                Gender = createDto.Gender,
                PetPhoto = createDto.PetPhoto,
                FatherBreedId = createDto.FatherBreedId,
                MotherBreedId = createDto.MotherBreedId,
                SellerId = createDto.SellerId,
                IsVaccinated = createDto.IsVaccinated,
                Price = createDto.Price,
                Description = createDto.Description,
                IsAvailable = true,
                CreatedAt = DateTime.UtcNow
            };

            _context.Pets.Add(pet);
            await _context.SaveChangesAsync();

            // Reload with includes
            var createdPet = await _context.Pets
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .Include(p => p.Seller)
                .FirstAsync(p => p.PetId == pet.PetId);

            return MapToPetResponseDto(createdPet);
        }

        public async Task<PetResponseDto?> UpdatePetAsync(int id, PetCreateDto updateDto)
        {
            var pet = await _context.Pets.FindAsync(id);
            if (pet == null) return null;

            pet.PetName = updateDto.PetName;
            pet.PetAge = updateDto.PetAge;
            pet.Gender = updateDto.Gender;
            pet.PetPhoto = updateDto.PetPhoto;
            pet.FatherBreedId = updateDto.FatherBreedId;
            pet.MotherBreedId = updateDto.MotherBreedId;
            pet.IsVaccinated = updateDto.IsVaccinated;
            pet.Price = updateDto.Price;
            pet.Description = updateDto.Description;

            await _context.SaveChangesAsync();

            // Reload with includes
            var updatedPet = await _context.Pets
                .Include(p => p.FatherBreed)
                .Include(p => p.MotherBreed)
                .Include(p => p.Seller)
                .FirstAsync(p => p.PetId == pet.PetId);

            return MapToPetResponseDto(updatedPet);
        }

        public async Task<bool> DeletePetAsync(int id)
        {
            var pet = await _context.Pets.FindAsync(id);
            if (pet == null) return false;

            _context.Pets.Remove(pet);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<BookPet?> BookPetAsync(BookPetDto bookingDto)
        {
            var pet = await _context.Pets.FindAsync(bookingDto.PetId);
            if (pet == null || !pet.IsAvailable) return null;

            var booking = new BookPet
            {
                PetId = bookingDto.PetId,
                BuyerId = bookingDto.BuyerId,
                Price = bookingDto.Price,
                Date = bookingDto.Date,
                Time = bookingDto.Time,
                Status = BookingStatus.PENDING,
                CreatedAt = DateTime.UtcNow
            };

            _context.BookPets.Add(booking);
            
            // Mark pet as unavailable
            pet.IsAvailable = false;
            
            await _context.SaveChangesAsync();
            return booking;
        }

        public async Task<IEnumerable<Breed>> GetBreedsAsync()
        {
            return await _context.Breeds.ToListAsync();
        }

        private PetResponseDto MapToPetResponseDto(Pet pet)
        {
            var crossBreed = pet.FatherBreed != null && pet.MotherBreed != null && 
                           pet.FatherBreed.BreedName != pet.MotherBreed.BreedName
                           ? $"{pet.FatherBreed.BreedName} x {pet.MotherBreed.BreedName}"
                           : null;

            return new PetResponseDto
            {
                PetId = pet.PetId,
                PetName = pet.PetName,
                PetAge = pet.PetAge,
                Gender = pet.Gender,
                PetPhoto = pet.PetPhoto,
                FatherBreedId = pet.FatherBreedId,
                FatherBreedName = pet.FatherBreed?.BreedName,
                MotherBreedId = pet.MotherBreedId,
                MotherBreedName = pet.MotherBreed?.BreedName,
                SellerId = pet.SellerId,
                SellerName = pet.Seller?.Username,
                IsVaccinated = pet.IsVaccinated,
                Price = pet.Price,
                Description = pet.Description,
                IsAvailable = pet.IsAvailable,
                CreatedAt = pet.CreatedAt,
                CrossBreed = crossBreed
            };
        }
    }
} 