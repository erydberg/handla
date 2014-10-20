package se.rydberg.handla.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="shoplist")
public class ShopList implements Serializable{
	private static final long serialVersionUID = 2960152067840478775L;
	
	private Integer listId;
	private String name;
	public Set<Article> articles = new LinkedHashSet<Article>();
//	private List shoppers;
	
	public ShopList() {
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "list_sequence")
    @SequenceGenerator(name = "list_sequence", sequenceName = "LIST_SEQUENCE")
	@Column(name="listid", nullable=false)
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	
	@Column(name="listname", length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	@OrderBy("title asc")
	@JoinColumn(name="fk_shoplist")
	
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
}
