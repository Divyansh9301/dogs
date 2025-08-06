using Microsoft.AspNetCore.Mvc;
using PetService.Models;
using PetService.Services;

namespace PetService.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BreedController : ControllerBase
    {
        private readonly Services.PetService _petService;

        public BreedController(Services.PetService petService)
        {
            _petService = petService;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Breed>>> GetAllBreeds()
        {
            var breeds = await _petService.GetBreedsAsync();
            return Ok(breeds);
        }
    }
} 