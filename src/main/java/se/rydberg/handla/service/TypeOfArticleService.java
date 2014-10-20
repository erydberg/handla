package se.rydberg.handla.service;

import se.rydberg.handla.exception.TypeOfArticleNotDeletedException;
import se.rydberg.handla.exception.TypeOfArticleNotFoundException;
import se.rydberg.handla.model.TypeOfArticle;

public interface TypeOfArticleService {
	public void saveTypeOfArticle(TypeOfArticle typeOfArticle);
	public void deleteTypeOfArticle(TypeOfArticle typeOfArticle);
	public void deleteTypeOfArticleById(Integer id) throws TypeOfArticleNotDeletedException;
	public TypeOfArticle getTypeOfArticleById(Integer id) throws TypeOfArticleNotFoundException;
}
