﻿using back.BLL.Dtos;
using back.BLL.Services;
using Microsoft.AspNetCore.Mvc;

namespace back.PL.Controllers
{
    [Route("back/[controller]")]
    [ApiController]
    public class DeliveryController : Controller
    {
        IDeliveryService _service;

        public DeliveryController(IDeliveryService service)
        {
            _service = service;
        }

        [HttpPost("InsertDeliveryRoute")]
        public async Task<IActionResult> InsertDeliveryRoute(DeliveryRouteDto dto)
        {
            try
            {
                return Ok(new { Success = await _service.InsertDeliveryRoute(dto) });
            }
            catch (Exception ex)
            {
                return BadRequest(new { Error = ex.Message });
            }
        }

        [HttpPut("AddToRoute")]
        public async Task<IActionResult> AddToRoute(int reqId, int routeId)
        {
            try
            {
                return Ok(new { Success = await _service.AddToRoute(reqId, routeId) });
            }
            catch (Exception ex)
            {
                return BadRequest(new { Error = ex.Message });
            }
        }

        [HttpGet("GetRequestsForDeliveryPerson")]
        public async Task<IActionResult> GetRequestsForDeliveryPerson(int deliveryPerson)
        {
            try
            {
                return Ok(await _service.GetRequestsForDeliveryPerson(deliveryPerson));
            }
            catch (Exception ex)
            {
                return BadRequest(new { Error = ex.Message });
            }
        }


        [HttpGet("GetRoutesForRequest")]
        public async Task<IActionResult> GetRoutesForRequest(int userId, int requestId)
        {
            try
            {
                return Ok(await _service.GetRoutesForRequest(userId, requestId));
            }
            catch (Exception ex)
            {
                return BadRequest(new { Error = ex.Message });
            }
        }
    }
}
