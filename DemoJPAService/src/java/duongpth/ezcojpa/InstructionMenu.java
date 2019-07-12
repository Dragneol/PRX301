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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dragn
 */
@Entity
@Table(name = "InstructionMenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstructionMenu.findAll", query = "SELECT i FROM InstructionMenu i")
    , @NamedQuery(name = "InstructionMenu.findById", query = "SELECT i FROM InstructionMenu i WHERE i.id = :id")
    , @NamedQuery(name = "InstructionMenu.findByNumStep", query = "SELECT i FROM InstructionMenu i WHERE i.numStep = :numStep")
    , @NamedQuery(name = "InstructionMenu.findByDetail", query = "SELECT i FROM InstructionMenu i WHERE i.detail = :detail")})
public class InstructionMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NumStep")
    private Integer numStep;
    @Size(max = 2000)
    @Column(name = "Detail")
    private String detail;
    @JoinColumn(name = "RecipeID", referencedColumnName = "ID")
    @ManyToOne
    private Recipe recipeID;

    public InstructionMenu() {
    }

    public InstructionMenu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumStep() {
        return numStep;
    }

    public void setNumStep(Integer numStep) {
        this.numStep = numStep;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        if (!(object instanceof InstructionMenu)) {
            return false;
        }
        InstructionMenu other = (InstructionMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duongpth.ezcojpa.InstructionMenu[ id=" + id + " ]";
    }
    
}
