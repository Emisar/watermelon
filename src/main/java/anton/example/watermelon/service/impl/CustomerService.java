package anton.example.watermelon.service.impl;

import anton.example.watermelon.entity.Customer;
import anton.example.watermelon.service.AbstractCRUDService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractCRUDService<Customer> {

    public CustomerService(JpaRepository<Customer, Long> repository) {
        super(repository);
    }

    @Override
    protected void objectUpdater(Customer dataObject, Customer updatedObject) {
        if (StringUtils.isNotBlank(dataObject.getName())) {
            updatedObject.setName(dataObject.getName());
        }
        if (StringUtils.isNotBlank(dataObject.getAddress())) {
            updatedObject.setAddress(dataObject.getAddress());
        }
    }
}
