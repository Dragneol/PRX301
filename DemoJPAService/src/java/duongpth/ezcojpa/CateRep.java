/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.ezcojpa;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dragn
 */
@Entity
@Table(name = "CateRep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CateRep.findAll", query = "SELECT c FROM CateRep c")
    , @NamedQuery(name = "CateRep.findById", query = "SELECT c FROM CateRep c WHERE c.id = :id")})
public class CateRep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "RecipeID", referencedColumnName = "ID")
    @ManyToOne
    private Recipe recipeID;
    @JoinColumn(name = "CateID", referencedColumnName = "ID")
    @ManyToOne
    private RecipeCategory cateID;

    public CateRep() {
    }

    public CateRep(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Recipe recipeID) {
        this.recipeID = recipeID;
    }

    public RecipeCategory getCateID() {
        return cateID;
    }

    public void setCateID(RecipeCategory cateID) {
        this.cateID = cateID;
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
        if (!(object instanceof CateRep)) {
            return false;
        }
        CateRep other = (CateRep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duongpth.ezcojpa.CateRep[ id=" + id + " ]";
    }
    
}
