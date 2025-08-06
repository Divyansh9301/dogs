using Microsoft.AspNetCore.Mvc;
using PetService.DTOs;
using PetService.Services;

namespace PetService.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class PetController : ControllerBase
    {
        private readonly Services.PetService _petService;

        public PetController(Services.PetService petService)
        {
            _petService = petService;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<PetResponseDto>>> GetAllPets()
        {
            var pets = await _petService.GetAllPetsAsync();
            return Ok(pets);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<PetResponseDto>> GetPet(int id)
        {
            var pet = await _petService.GetPetByIdAsync(id);
            if (pet == null)
            {
                return NotFound();
            }
            return Ok(pet);
        }

        [HttpGet("seller/{sellerId}")]
        public async Task<ActionResult<IEnumerable<PetResponseDto>>> GetPetsBySeller(int sellerId)
        {
            var pets = await _petService.GetPetsBySellerAsync(sellerId);
            return Ok(pets);
        }

        [HttpGet("search")]
        public async Task<ActionResult<IEnumerable<PetResponseDto>>> SearchPets([FromQuery] string term)
        {
            if (string.IsNullOrWhiteSpace(term))
            {
                return BadRequest("Search term is required");
            }

            var pets = await _petService.SearchPetsAsync(term);
            return Ok(pets);
        }

        [HttpPost]
        public async Task<ActionResult<PetResponseDto>> CreatePet([FromBody] PetCreateDto createDto)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var pet = await _petService.CreatePetAsync(createDto);
                return CreatedAtAction(nameof(GetPet), new { id = pet.PetId }, pet);
            }
            catch (Exception ex)
            {
                return BadRequest($"Error creating pet: {ex.Message}");
            }
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<PetResponseDto>> UpdatePet(int id, [FromBody] PetCreateDto updateDto)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var pet = await _petService.UpdatePetAsync(id, updateDto);
                if (pet == null)
                {
                    return NotFound();
                }
                return Ok(pet);
            }
            catch (Exception ex)
            {
                return BadRequest($"Error updating pet: {ex.Message}");
            }
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult> DeletePet(int id)
        {
            try
            {
                var result = await _petService.DeletePetAsync(id);
                if (!result)
                {
                    return NotFound();
                }
                return NoContent();
            }
            catch (Exception ex)
            {
                return BadRequest($"Error deleting pet: {ex.Message}");
            }
        }

        [HttpPost("book")]
        public async Task<ActionResult> BookPet([FromBody] BookPetDto bookingDto)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var booking = await _petService.BookPetAsync(bookingDto);
                if (booking == null)
                {
                    return BadRequest("Pet not available for booking");
                }
                return Ok(new { BookingId = booking.BookId, Message = "Pet booked successfully" });
            }
            catch (Exception ex)
            {
                return BadRequest($"Error booking pet: {ex.Message}");
            }
        }
    }
} 