package es.golemdr.tiendaweb.server.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.golemdr.tiendaweb.server.domain.Producto;

import org.hibernate.criterion.Restrictions;


@Repository
//@Transactional(readOnly = true)
public class ProductosRepositoryImpl implements ProductosRepositoryCustom{
	
    @PersistenceContext
    EntityManager entityManager;


	public List<Producto> getProductosPorPrecio(Double limiteInferior, Double limiteSuperior) {
			

		
        Query query = entityManager.createNativeQuery("SELECT pr.* FROM tiendaweb.productos pr where precio >= ?", Producto.class);

        query.setParameter(1, limiteInferior + "%");

        return query.getResultList();
		

	}
	
	// Ejemplo con CriteriaBuilder para sustituir a Criteria
	
//    public List<User> findUserByEmails(Set<String> emails) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = cb.createQuery(User.class);
//        Root<User> user = query.from(User.class);
// 
//        Path<String> emailPath = user.get("email");
// 
//        List<Predicate> predicates = new ArrayList<>();
//        for (String email : emails) {
//            predicates.add(cb.like(emailPath, email));
//        }
//        query.select(user)
//            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
// 
//        return entityManager.createQuery(query)
//            .getResultList();
//		}
	
	
	// Otro ejemplo en el que se ve el antes y el ahora para hacer una query dinamica

	// Antes
//	public List<MyObject> listAllForIds(List<Long> ids) {
//	    Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MyObject.class)
//	            .createAlias("joinObject", "joinObject")
//	            .add(Restrictions.not(Restrictions.like("name", "string1", MatchMode.END)))
//	            .add(Restrictions.not(Restrictions.like("name", "string2", MatchMode.END)))
//	            .add(Restrictions.in("joinObject.id", ids));
//
//	    return criteria.list();
//	}

	// Ahora
//	public List<MyObject> listAllForIds(List<Long> ids) {
//
//	    CriteriaBuilder builder = getSessionFactory().getCurrentSession().getCriteriaBuilder();
//	    CriteriaQuery<MyObject> criteria = builder.createQuery(MyObject.class);
//	    Root<MyObject> myObjectRoot = criteria.from(MyObject.class);
//	    Join<MyObject, JoinObject> joinObject = myObjectRoot.join("joinObject");
//
//	    Predicate likeRestriction = builder.and(
//	            builder.notLike( myObjectRoot.get("name"), "%string1"),
//	            builder.notLike( myObjectRoot.get("name"), "%string2")
//	    );
//
//	    criteria.select(myObjectRoot).where(joinObject.get("id").in(ids), likeRestriction);
//
//	    TypedQuery<MyObject> query = getSessionFactory().getCurrentSession().createQuery(criteria);
//
//	    return query.getResultList();
//	}


}
