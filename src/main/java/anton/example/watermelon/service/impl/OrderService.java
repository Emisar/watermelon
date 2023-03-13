package anton.example.watermelon.service.impl;

import anton.example.watermelon.entity.Order;
import anton.example.watermelon.service.AbstractCRUDService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderService extends AbstractCRUDService<Order> {

    public OrderService(JpaRepository<Order, Long> repository) {
        super(repository);
    }



    @Override
    protected void objectUpdater(Order dataObject, Order updatedObject) {
        if (Objects.nonNull(dataObject.getCustomer())) {
            updatedObject.setCustomer(dataObject.getCustomer());
        }
        if (Objects.nonNull(dataObject.getProduct())) {
            updatedObject.setProduct(dataObject.getProduct());
        }
    }
}
