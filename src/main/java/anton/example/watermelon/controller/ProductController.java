package anton.example.watermelon.controller;

import anton.example.watermelon.entity.Product;
import anton.example.watermelon.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static anton.example.watermelon.consts.LoggingMessages.CALLING_METHOD;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public boolean addProduct(@RequestBody Product product) {
        log.info(CALLING_METHOD, "create", Product.class);
        return Objects.nonNull(productService.create(product));
    }

    @GetMapping("/get")
    public Product getProduct(@RequestParam Long id) {
        log.info(CALLING_METHOD, "read", Product.class);
        return productService.read(id);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        log.info(CALLING_METHOD, "readAll", Product.class);
        return productService.readAll();
    }

    @PatchMapping("/update")
    public boolean updateProduct(@RequestParam Long id, @RequestBody Product product) {
        log.info(CALLING_METHOD, "update", Product.class);
        return Objects.nonNull(productService.update(id, product));
    }

    @DeleteMapping("/delete")
    public boolean deleteProduct(@RequestParam Long id) {
        log.info(CALLING_METHOD, "delete", Product.class);
        return productService.delete(id);
    }
}
