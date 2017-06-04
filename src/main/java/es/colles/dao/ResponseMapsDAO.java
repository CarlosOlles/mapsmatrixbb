//package es.colles.dao;
//
//import es.colles.model.QueryMaps;
//import es.colles.model.ResponseMaps;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Component
//public class ResponseMapsDAO {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void save(ResponseMaps responseMaps) {
//        entityManager.persist(responseMaps);
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<ResponseMaps> list() {
//
//        return entityManager.createQuery("select t from QueryMaps t")
//                .getResultList();
//    }
//
//}
