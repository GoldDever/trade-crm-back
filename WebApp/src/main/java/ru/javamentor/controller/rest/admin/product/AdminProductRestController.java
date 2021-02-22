package ru.javamentor.controller.rest.admin.product;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.javamentor.dto.product.ProductPostDto;
import ru.javamentor.model.product.Product;
import ru.javamentor.service.storage.FileStorageException;
import ru.javamentor.service.product.ProductService;
import ru.javamentor.service.storage.FileStorageService;

@RestController
@RequestMapping("api/admin/product")
public class AdminProductRestController {

    private final ProductService productService;
    private final FileStorageService fileStorageService; //test

    public AdminProductRestController(ProductService productService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService; //delete
    }

    /**
     * метод сохранения нового продукта
     * @param dto экземпляр нового продукта
     * @return статус выполнения запроса
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductPostDto dto) {
        productService.saveProduct(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * метод изменения существующего продукта
     * @param productDto экземпляр изменённого продукта
     * @return статус выполнения запроса
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductPostDto productDto) {
        productService.updateProduct(productDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/image/{idFromErp}")
    public ResponseEntity<?> imageUpdateProduct(
            @PathVariable String idFromErp,
            @RequestParam MultipartFile image) {
        Product product = productService.getProductByIdFromErp(idFromErp);
        if (product == null) {
            return ResponseEntity.badRequest().body("Продукт не найден.");
        }
        try {
            productService.imageUpdateProduct(product, image);
            return ResponseEntity.ok("Файл загружен.");
        } catch (FileStorageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/image/{idFromErp}")
    public ResponseEntity<?> getProductImage(@PathVariable String idFromErp) {
        Product product = productService.getProductByIdFromErp(idFromErp);
        if (product == null) {
            return ResponseEntity.badRequest().body("Продукт не найден.");
        }
        try {
            Resource resource = fileStorageService.loadImageAsResource(product.getImageUrl());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (FileStorageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
