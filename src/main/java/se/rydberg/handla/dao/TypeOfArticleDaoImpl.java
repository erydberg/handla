package se.rydberg.handla.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.rydberg.handla.exception.TypeOfArticleNotFoundException;
import se.rydberg.handla.model.TypeOfArticle;

@Repository
public class TypeOfArticleDaoImpl implements TypeOfArticleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteTypeOfArticle(TypeOfArticle typeOfArticle) {
		sessionFactory.getCurrentSession().delete(typeOfArticle);
	}

	@Override
	public void saveTypeOfArticle(TypeOfArticle typeOfArticle) {
		sessionFactory.getCurrentSession().saveOrUpdate(typeOfArticle);
	}

	@Override
	public TypeOfArticle getTypeOfArticleById(Integer id)
			throws TypeOfArticleNotFoundException {
		TypeOfArticle typeOfArticle = null;
		@SuppressWarnings("unchecked")
		List<TypeOfArticle> typeOfArticles = (List<TypeOfArticle>)sessionFactory.getCurrentSession().createQuery("from TypeOfArticle as type where type.typeId=?").setParameter(0, id).list();
		if(typeOfArticles==null||typeOfArticles.isEmpty()||typeOfArticles.size()>1){
			throw new TypeOfArticleNotFoundException("Hittar inte typ av artikel med id: " + id);
		}
		typeOfArticle = typeOfArticles.get(0);
		
		return typeOfArticle;
	}

	@Override
	public List<TypeOfArticle> getAllTypeOfArticles() {
		@SuppressWarnings("unchecked")
		List<TypeOfArticle> typeOfArticles = (List<TypeOfArticle>)sessionFactory.getCurrentSession().createQuery("from TypeOfArticle as type order by type.name asc").list();
		return typeOfArticles;
	}

}
