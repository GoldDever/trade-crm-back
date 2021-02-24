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
        ProductCategory productMainCategory1 = new ProductCategory();
        productMainCategory1.setCategoryName("Чай, кофе, какао");
        productCategoryRepository.save(productMainCategory1);

        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setCategoryName("Кофе");
        productCategory1.setMainProductCategory(productMainCategory1);
        productCategoryRepository.save(productCategory1);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setCategoryName("Чай");
        productCategory2.setMainProductCategory(productMainCategory1);
        productCategoryRepository.save(productCategory2);

        ProductCategory productCategory3 = new ProductCategory();
        productCategory3.setCategoryName("Какао, горячий шоколад");
        productCategory3.setMainProductCategory(productMainCategory1);
        productCategoryRepository.save(productCategory3);

        ProductCategory productMainCategory2 = new ProductCategory();
        productMainCategory2.setCategoryName("Хлеб, выпечка, сладости");
        productCategoryRepository.save(productMainCategory2);

        ProductCategory productCategory4 = new ProductCategory();
        productCategory4.setCategoryName("Выпечка и сдоба");
        productCategory4.setMainProductCategory(productMainCategory2);
        productCategoryRepository.save(productCategory4);

        ProductCategory productCategory5 = new ProductCategory();
        productCategory5.setCategoryName("Печенье, пряники и вафли");
        productCategory5.setMainProductCategory(productMainCategory2);
        productCategoryRepository.save(productCategory5);

        ProductCategory productCategory6 = new ProductCategory();
        productCategory6.setCategoryName("Зефир, пастела");
        productCategory6.setMainProductCategory(productMainCategory2);
        productCategoryRepository.save(productCategory6);

        ProductCategory productMainCategory3 = new ProductCategory();
        productMainCategory3.setCategoryName("Мясо и птица");
        productCategoryRepository.save(productMainCategory3);

        ProductCategory productCategory7 = new ProductCategory();
        productCategory7.setCategoryName("Говядина");
        productCategory7.setMainProductCategory(productMainCategory3);
        productCategoryRepository.save(productCategory7);

        ProductCategory productCategory8 = new ProductCategory();
        productCategory8.setCategoryName("Свинина");
        productCategory8.setMainProductCategory(productMainCategory3);
        productCategoryRepository.save(productCategory8);

        ProductCategory productCategory9 = new ProductCategory();
        productCategory9.setCategoryName("Баранина");
        productCategory9.setMainProductCategory(productMainCategory3);
        productCategoryRepository.save(productCategory9);

        ProductCategory productMainCategory4 = new ProductCategory();
        productMainCategory4.setCategoryName("Овощи, фрукты, зелень");
        productCategoryRepository.save(productMainCategory4);

        ProductCategory productCategory10 = new ProductCategory();
        productCategory10.setCategoryName("Овощи");
        productCategory10.setMainProductCategory(productMainCategory4);
        productCategoryRepository.save(productCategory10);

        ProductCategory productCategory11 = new ProductCategory();
        productCategory11.setCategoryName("Фрукты");
        productCategory11.setMainProductCategory(productMainCategory4);
        productCategoryRepository.save(productCategory11);

        ProductCategory productCategory12 = new ProductCategory();
        productCategory12.setCategoryName("Зелень");
        productCategory12.setMainProductCategory(productMainCategory4);
        productCategoryRepository.save(productCategory12);

        ProductCategory productMainCategory5 = new ProductCategory();
        productMainCategory5.setCategoryName("Колбасы, сосиски деликатесы");
        productCategoryRepository.save(productMainCategory5);

        ProductCategory productCategory13 = new ProductCategory();
        productCategory13.setCategoryName("Колбасы");
        productCategory13.setMainProductCategory(productMainCategory5);
        productCategoryRepository.save(productCategory13);

        ProductCategory productCategory14 = new ProductCategory();
        productCategory14.setCategoryName("Бекон и сало");
        productCategory14.setMainProductCategory(productMainCategory5);
        productCategoryRepository.save(productCategory14);

        ProductCategory productCategory15 = new ProductCategory();
        productCategory15.setCategoryName("Карбонад");
        productCategory15.setMainProductCategory(productMainCategory5);
        productCategoryRepository.save(productCategory15);

    }
}
