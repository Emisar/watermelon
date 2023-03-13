package anton.example.watermelon.service.impl;

import anton.example.watermelon.entity.Product;
import anton.example.watermelon.service.AbstractCRUDService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductService extends AbstractCRUDService<Product> {

    public ProductService(JpaRepository<Product, Long> repository) {
        super(repository);
    }

    @Override
    protected void objectUpdater(Product dataObject, Product updatedObject) {
        if (StringUtils.isNotBlank(dataObject.getName())) {
            updatedObject.setName(dataObject.getName());
        }
        if (Objects.nonNull(dataObject.getPrice())) {
            updatedObject.setPrice(dataObject.getPrice());
        }
    }
}
