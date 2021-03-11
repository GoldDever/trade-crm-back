package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.product.ProductCategory;
import ru.javamentor.repository.product.ProductCategoryRepository;

@Component
public class InitProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public InitProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public void initProductCategory() {
        createProductCategory("Чай, кофе, какао", true, null);
        createProductCategory("Кофе", false, productCategoryRepository.findProductCategoryByCategoryName("Чай, кофе, какао"));
        createProductCategory("Чай", false, productCategoryRepository.findProductCategoryByCategoryName("Чай, кофе, какао"));
        createProductCategory("Какао, горячий шоколад", false, productCategoryRepository.findProductCategoryByCategoryName("Чай, кофе, какао"));

        createProductCategory("Хлеб, выпечка, сладости", true, null);
        createProductCategory("Выпечка и сдоба", false, productCategoryRepository.findProductCategoryByCategoryName("Хлеб, выпечка, сладости"));
        createProductCategory("Печенье, пряники и вафли", false, productCategoryRepository.findProductCategoryByCategoryName("Хлеб, выпечка, сладости"));
        createProductCategory("Зефир, пастела", false, productCategoryRepository.findProductCategoryByCategoryName("Хлеб, выпечка, сладости"));

        createProductCategory("Мясо и птица", true, null);
        createProductCategory("Говядина", false, productCategoryRepository.findProductCategoryByCategoryName("Мясо и птица"));
        createProductCategory("Свинина", false, productCategoryRepository.findProductCategoryByCategoryName("Мясо и птица"));
        createProductCategory("Баранина", false, productCategoryRepository.findProductCategoryByCategoryName("Мясо и птица"));

        createProductCategory("Овощи, фрукты, зелень", true, null);
        createProductCategory("Овощи", false, productCategoryRepository.findProductCategoryByCategoryName("Овощи, фрукты, зелень"));
        createProductCategory("Фрукты", false, productCategoryRepository.findProductCategoryByCategoryName("Овощи, фрукты, зелень"));
        createProductCategory("Зелень", false, productCategoryRepository.findProductCategoryByCategoryName("Овощи, фрукты, зелень"));

        createProductCategory("Колбасы, сосиски деликатесы", true, null);
        createProductCategory("Колбасы", false, productCategoryRepository.findProductCategoryByCategoryName("Колбасы, сосиски деликатесы"));
        createProductCategory("Бекон и сало", false, productCategoryRepository.findProductCategoryByCategoryName("Колбасы, сосиски деликатесы"));
        createProductCategory("Карбонад", false, productCategoryRepository.findProductCategoryByCategoryName("Колбасы, сосиски деликатесы"));
    }

    private void createProductCategory(String categoryName,
                                       Boolean mainCategory,
                                       ProductCategory mainProductCategory) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(categoryName);
        productCategory.setMainCategory(mainCategory);
        productCategory.setMainProductCategory(mainProductCategory);
        productCategoryRepository.save(productCategory);
    }
}
