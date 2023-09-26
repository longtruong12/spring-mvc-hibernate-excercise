package com.example.mvc.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mvc.entity.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepository implements BaseRepository<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        return session.createQuery(cq).getResultList();
    }

    @Override
    public void save(Product object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(object);
    }

    @Override
    public Product getById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Product.class, id);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Product product = currentSession.get(Product.class, id);
        if (product != null) {
            currentSession.delete(product);
        }
    }
}
