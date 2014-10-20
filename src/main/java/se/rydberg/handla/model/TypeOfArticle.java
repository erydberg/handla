package se.rydberg.handla.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="typeofarticle")
public class TypeOfArticle implements Serializable {
	private static final long serialVersionUID = -6575338804225085947L;
	private Integer typeId;
	private String name;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "typeid_sequence")
    @SequenceGenerator(name = "typeid_sequence", sequenceName = "TYPEID_SEQUENCE")
	@Column(name="typeId", nullable=false)
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	@Column(name="name", length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
