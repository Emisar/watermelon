package anton.example.watermelon.service;

import anton.example.watermelon.entity.AbstractIdentifiable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCRUDService<T extends AbstractIdentifiable> implements CRUDService<T> {

    protected final JpaRepository<T, Long> repository;

    @Override
    public T create(T obj) {
        return repository.save(obj);
    }

    @Override
    public T read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> readAll() {
        return repository.findAll();
    }

    @Override
    public T update(Long id, T obj) {
        T dbObject = repository.findById(id).orElse(obj);
        objectUpdater(obj, dbObject);
        return repository.save(dbObject);
    }

    protected abstract void objectUpdater(T dataObject, T updatedObject);

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return repository.findById(id).isPresent();
    }
}
