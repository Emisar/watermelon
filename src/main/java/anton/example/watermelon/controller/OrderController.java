package anton.example.watermelon.controller;

import anton.example.watermelon.entity.Customer;
import anton.example.watermelon.entity.Order;
import anton.example.watermelon.entity.Product;
import anton.example.watermelon.service.impl.CustomerService;
import anton.example.watermelon.service.impl.OrderService;
import anton.example.watermelon.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static anton.example.watermelon.consts.LoggingMessages.CALLING_METHOD;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    @PostMapping("/add")
    public boolean addOrder(@RequestParam Long customerId, @RequestParam Long productId) {
        log.info(CALLING_METHOD, "create", Order.class);
        Customer customer = customerService.read(customerId);
        Product product = productService.read(productId);
        return Objects.nonNull(orderService.create(new Order(customer, product)));
    }

    @GetMapping("/get")
    public Order getOrder(@RequestParam Long id) {
        log.info(CALLING_METHOD, "read", Order.class);
        return orderService.read(id);
    }

    @GetMapping("/getAll")
    public List<Order> getAllOrders() {
        log.info(CALLING_METHOD, "readAll", Order.class);
        return orderService.readAll();
    }

    @DeleteMapping("/delete")
    public boolean deleteOrder(@RequestParam Long id) {
        log.info(CALLING_METHOD, "delete", Order.class);
        return orderService.delete(id);
    }
}
