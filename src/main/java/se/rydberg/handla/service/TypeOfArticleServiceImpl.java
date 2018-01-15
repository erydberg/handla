package se.rydberg.handla.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.rydberg.handla.dao.TypeOfArticleDao;
import se.rydberg.handla.exception.TypeOfArticleNotDeletedException;
import se.rydberg.handla.exception.TypeOfArticleNotFoundException;
import se.rydberg.handla.model.TypeOfArticle;

@Service
public class TypeOfArticleServiceImpl implements TypeOfArticleService {

	@Autowired
	private TypeOfArticleDao typeOfArticleDao;
	
	@Override
	@Transactional
	public void saveTypeOfArticle(TypeOfArticle typeOfArticle) {
		typeOfArticleDao.saveTypeOfArticle(typeOfArticle);
	}

	@Override
	@Transactional
	public void deleteTypeOfArticle(TypeOfArticle typeOfArticle) {
		typeOfArticleDao.deleteTypeOfArticle(typeOfArticle);
	}

	@Override
	@Transactional
	public void deleteTypeOfArticleById(Integer id) throws TypeOfArticleNotDeletedException {
		try {
			TypeOfArticle typeOfArticleDel = getTypeOfArticleById(id);
			this.deleteTypeOfArticle(typeOfArticleDel);
		} catch (TypeOfArticleNotFoundException e) {
			
			e.printStackTrace();
			throw new TypeOfArticleNotDeletedException("Det gick inte att ta bort kategorin då den inte hittades");
		}
		

	}

	@Override
	@Transactional
	public TypeOfArticle getTypeOfArticleById(Integer id)
			throws TypeOfArticleNotFoundException {
		return typeOfArticleDao.getTypeOfArticleById(id);
	}

}
