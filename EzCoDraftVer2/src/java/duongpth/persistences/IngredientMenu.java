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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dragn
 */
@Entity
@Table(name = "IngredientMenu", catalog = "EzCoVer1", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngredientMenu.findAll", query = "SELECT i FROM IngredientMenu i"),
    @NamedQuery(name = "IngredientMenu.findById", query = "SELECT i FROM IngredientMenu i WHERE i.id = :id"),
    @NamedQuery(name = "IngredientMenu.findByName", query = "SELECT i FROM IngredientMenu i WHERE i.name = :name"),
    @NamedQuery(name = "IngredientMenu.findByUnit", query = "SELECT i FROM IngredientMenu i WHERE i.unit = :unit"),
    @NamedQuery(name = "IngredientMenu.findByQuantitive", query = "SELECT i FROM IngredientMenu i WHERE i.quantitive = :quantitive")})
public class IngredientMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Name", length = 1000)
    private String name;
    @Column(name = "Unit", length = 50)
    private String unit;
    @Column(name = "Quantitive")
    private Integer quantitive;
    @JoinColumn(name = "RecipeID", referencedColumnName = "ID")
    @ManyToOne
    private Recipe recipeID;

    public IngredientMenu() {
    }

    public IngredientMenu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantitive() {
        return quantitive;
    }

    public void setQuantitive(Integer quantitive) {
        this.quantitive = quantitive;
    }

    public Recipe getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Recipe recipeID) {
        this.recipeID = recipeID;
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
        if (!(object instanceof IngredientMenu)) {
            return false;
        }
        IngredientMenu other = (IngredientMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duongpth.persistences.IngredientMenu[ id=" + id + " ]";
    }
    
}
