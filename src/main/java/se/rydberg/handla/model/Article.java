package se.rydberg.handla.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="article")
public class Article implements Serializable {
    private static final long serialVersionUID = -2861180788425247554L;
    private Integer id;
    private String title;
    public boolean bought = false;
    private Timestamp updated;
    private TypeOfArticle type;
    private ShopList shopList;
    private String unit;
    private String quantity;
    private String completeTitle;


    public Article(){

    }



    @Transient
    public String getCompleteTitle(){
        StringBuilder returnText = new StringBuilder();
        if(title!=null){
            returnText.append(title);
            returnText.append(" ");
        }
        if(quantity!=null){
            returnText.append(quantity);
            returnText.append(" ");
        }
        if(unit!=null){
            returnText.append(unit);
        }
        return returnText.toString().trim();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "article_sequence")
    @SequenceGenerator(name = "article_sequence", sequenceName = "ARTICLE_SEQUENCE")
    @Column(name="id", nullable=false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="title", length=100)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name="updated", length=40)
    public Timestamp getUpdated() {
        return updated;
    }
    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }


    @ManyToOne
    @JoinColumn(name="fk_type")
    public TypeOfArticle getType() {
        return type;
    }
    public void setType(TypeOfArticle type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name="fk_shoplist")
    public ShopList getShopList() {
        return shopList;
    }
    public void setShopList(ShopList shopList) {
        this.shopList = shopList;
    }


    @Column(name="bought")
    public boolean isBought() {
        return bought;
    }
    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Column(name="quantity")
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    @Column(name="unit")
    public String getUnit() {
        return unit;
    }


    public void setUnit(String unit) {
        this.unit = unit;
    }
    


}
