package pl.javastart.devicerentboot.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.devicerentboot.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    public Category read(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    public Category update(Category category) {
        return entityManager.merge(category);
    }

    @Transactional
    public void delete(Category category) {
        Category attachedCategory = read(category.getId());
        entityManager.remove(attachedCategory);
    }
}