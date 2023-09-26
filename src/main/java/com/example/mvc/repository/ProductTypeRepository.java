package com.example.mvc.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mvc.entity.ProductType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductTypeRepository implements BaseRepository<ProductType> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductType> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProductType> cq = cb.createQuery(ProductType.class);
        Root<ProductType> root = cq.from(ProductType.class);
        cq.select(root);
        return session.createQuery(cq).getResultList();
    }

    @Override
    public void save(ProductType object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(object);
    }

    @Override
    public ProductType getById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(ProductType.class, id);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        ProductType productType = currentSession.get(ProductType.class, id);
        if (productType != null) {
            currentSession.delete(productType);
        }
    }
}
