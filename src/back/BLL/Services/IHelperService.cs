﻿namespace back.BLL.Services
{
    public interface IHelperService
    {
        public Task<bool> UploadImage(IFormFile image, int type, int id);
        public Task<string> GeneratePaymentSlip(int userId, int shopId, float amount, string? address);
        public Task<double> Route(string start, string end, string shop, string shipping);
    }
}
