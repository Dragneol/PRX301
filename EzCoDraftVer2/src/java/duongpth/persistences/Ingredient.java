/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.persistences;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dragn
 */
@Entity
@Table(name = "Ingredient", catalog = "EzCoVer1", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findById", query = "SELECT i FROM Ingredient i WHERE i.id = :id"),
    @NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE i.name = :name"),
    @NamedQuery(name = "Ingredient.findByPrice", query = "SELECT i FROM Ingredient i WHERE i.price = :price"),
    @NamedQuery(name = "Ingredient.findByLink", query = "SELECT i FROM Ingredient i WHERE i.link = :link"),
    @NamedQuery(name = "Ingredient.findByImage", query = "SELECT i FROM Ingredient i WHERE i.image = :image"),
    @NamedQuery(name = "Ingredient.findByUnit", query = "SELECT i FROM Ingredient i WHERE i.unit = :unit")})
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 10)
    private String id;
    @Column(name = "Name", length = 800)
    private String name;
    @Column(name = "Price")
    private Integer price;
    @Column(name = "Link", length = 1000)
    private String link;
    @Column(name = "Image", length = 100)
    private String image;
    @Column(name = "Unit")
    private Integer unit;

    public Ingredient() {
    }

    public Ingredient(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duongpth.persistences.Ingredient[ id=" + id + " ]";
    }
    
}
