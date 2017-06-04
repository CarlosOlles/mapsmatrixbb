package es.colles.dao;

import es.colles.model.QueryMaps;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class QueryMapsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(QueryMaps queryMaps) {
        entityManager.persist(queryMaps);
    }

    @SuppressWarnings("unchecked")
    public List<QueryMaps> list() {

        return entityManager.createQuery("select t from QueryMaps t")
                .getResultList();
    }

}
