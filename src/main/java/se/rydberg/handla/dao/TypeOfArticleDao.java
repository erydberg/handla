package se.rydberg.handla.dao;

import java.util.List;

import se.rydberg.handla.exception.TypeOfArticleNotFoundException;
import se.rydberg.handla.model.TypeOfArticle;;

public interface TypeOfArticleDao {
	public void deleteTypeOfArticle(TypeOfArticle typeOfArticle);
	public void saveTypeOfArticle(TypeOfArticle typeOfArticle);
	public TypeOfArticle getTypeOfArticleById(Integer id) throws TypeOfArticleNotFoundException;
	public List<TypeOfArticle> getAllTypeOfArticles();
}
