package anton.example.watermelon.controller;

import anton.example.watermelon.entity.Customer;
import anton.example.watermelon.service.impl.CustomerService;
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
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public boolean addCustomer(@RequestBody Customer customer) {
        log.info(CALLING_METHOD, "create", Customer.class);
        return Objects.nonNull(customerService.create(customer));
    }

    @GetMapping("/get")
    public Customer getCustomer(@RequestParam Long id) {
        log.info(CALLING_METHOD, "read", Customer.class);
        return customerService.read(id);
    }

    @GetMapping("/getAll")
    public List<Customer> getAllCustomers() {
        log.info(CALLING_METHOD, "readAll", Customer.class);
        return customerService.readAll();
    }

    @PatchMapping("/update")
    public boolean updateCustomer(@RequestParam Long id, @RequestBody Customer customer) {
        log.info(CALLING_METHOD, "update", Customer.class);
        return Objects.nonNull(customerService.update(id, customer));
    }

    @DeleteMapping("/delete")
    public boolean deleteCustomer(@RequestParam Long id) {
        log.info(CALLING_METHOD, "delete", Customer.class);
        return customerService.delete(id);
    }
}
