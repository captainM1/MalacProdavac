﻿using back.BLL.Dtos;
using back.BLL.Dtos.Cards;
using back.BLL.Dtos.HelpModels;
using back.BLL.Dtos.Infos;
using back.DAL.Repositories;
using back.Models;

namespace back.BLL.Services
{
    public class ProductService : IProductService
    {
        IProductRepository _repository;

        public ProductService(IProductRepository repository)
        {
            _repository = repository;
        }

        string imagesFolderPath = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "..\\..\\..\\images");

        public async Task<List<ProductCard>> GetProducts(int userId, List<int>? categories, int? rating, bool? open, int? range, string? location, int sort, string? search, int page, int? specificShopId, bool? favorite, float? currLat, float? currLong)
        {
            List<ProductCard> products = await _repository.GetProducts(userId, categories, rating, open, range, location, sort, search, page, specificShopId, favorite, currLat, currLong);
            if (products.Count == 0) throw new ArgumentException("No products found.");
            return products;
        }

        public int ProductPages()
        {
            int total = _repository.ProductPages();
            if (total == 0) throw new ArgumentException("No pages.");

            return total;
        }

        public async Task<ProductInfo> ProductDetails(int productId, int userId)
        {
            ProductInfo productInfo = await _repository.ProductDetails(productId, userId);
            if (productInfo == null) throw new ArgumentException("No product found!");

            return productInfo;
        }

        public async Task<List<ProductReviewExtended>> GetProductReviews(int productId, int page)
        {
            List<ProductReviewExtended> reviews = await _repository.GetProductReviews(productId, page);
            if (reviews.Count == 0) throw new ArgumentException("No reviews found!");

            return reviews;
        }
        public async Task<bool> ToggleLike(int productId, int userId)
        {
            if (await _repository.GetLike(productId, userId) == null)
            {
                if (!await _repository.LikeProduct(productId, userId)) throw new ArgumentException("Product could not be liked!");
                return true;
            }

            if (!await _repository.DislikeProduct(productId, userId)) throw new ArgumentException("Product could not be disliked!");
            return true;
        }

        public async Task<bool> AddToCart(int productId, int userId, int quantity)
        {
            var item = await _repository.GetCartItem(productId, userId);
            
            if (item != null)
            {
                if (await _repository.UpdateCart(productId, userId, quantity)) return true;
                throw new ArgumentException("Quantity could not be updated!");
            }

            if (await _repository.AddToCart(productId, userId, quantity)) return true;
            throw new ArgumentException("Item could not be added!");
        }

        public async Task<bool> RemoveFromCart(int productId, int userId)
        {
            if (await _repository.RemoveFromCart(productId, userId)) return true;
            throw new ArgumentException("Item could not be removed!");
        }

        public async Task<bool> LeaveReview(ReviewDto review)
        {
            if (await _repository.LeaveReview(review)) return true;
            throw new ArgumentException("Product could not be reviewed!");
        }

        public async Task<bool> LeaveQuestion(QnADto question)
        {
            if (await _repository.LeaveQuestion(question)) return true;
            throw new ArgumentException("Question could not be saved!");
        }

        public async Task<bool> DeleteProductPhoto(int photoId)
        {
            string img = await _repository.DeleteProductPhoto(photoId);
            if (img == null) throw new ArgumentException("No image found!");

            string path = Path.Combine(imagesFolderPath, img);

            if (File.Exists(path))
            {
                File.Delete(path);
                return true;
            }

            return false;
        }

        public async Task<bool> AddProduct(ProductDto product)
        {
            Product newProduct = new Product
            {
                CategoryId = product.CategoryId,
                Description = product.Description,
                MetricId = product.MetrticId,
                Price = product.Price,
                Name = product.Name,
                SaleMessage = product.SaleMessage,
                SaleMinQuantity = product.SaleMinQuantity,
                SalePercentage = product.SalePercentage,
                ShopId = product.ShopId,
                SubcategoryId = product.SubcategoryId
            };

            if (!await _repository.AddProduct(newProduct)) throw new ArgumentException("The product could not be added!");

            return true;
        }
    }
}
