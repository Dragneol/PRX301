/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.persistences;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dragn
 */
@Entity
@Table(name = "Recipe", catalog = "EzCoVer1", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r"),
    @NamedQuery(name = "Recipe.findById", query = "SELECT r FROM Recipe r WHERE r.id = :id"),
    @NamedQuery(name = "Recipe.findByTitle", query = "SELECT r FROM Recipe r WHERE r.title = :title"),
    @NamedQuery(name = "Recipe.findByDescription", query = "SELECT r FROM Recipe r WHERE r.description = :description"),
    @NamedQuery(name = "Recipe.findByRation", query = "SELECT r FROM Recipe r WHERE r.ration = :ration"),
    @NamedQuery(name = "Recipe.findByPrepareTime", query = "SELECT r FROM Recipe r WHERE r.prepareTime = :prepareTime"),
    @NamedQuery(name = "Recipe.findByCookingTime", query = "SELECT r FROM Recipe r WHERE r.cookingTime = :cookingTime"),
    @NamedQuery(name = "Recipe.findByInstruction", query = "SELECT r FROM Recipe r WHERE r.instruction = :instruction")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Title", length = 1000)
    private String title;
    @Column(name = "Description", length = 2000)
    private String description;
    @Column(name = "Ration")
    private Integer ration;
    @Column(name = "PrepareTime")
    private Integer prepareTime;
    @Column(name = "CookingTime")
    private Integer cookingTime;
    @Column(name = "Instruction", length = 4000)
    private String instruction;
    @OneToMany(mappedBy = "recipeID")
    private Collection<InstructionMenu> instructionMenuCollection;
    @OneToMany(mappedBy = "recipeID")
    private Collection<IngredientMenu> ingredientMenuCollection;

    public Recipe() {
    }

    public Recipe(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRation() {
        return ration;
    }

    public void setRation(Integer ration) {
        this.ration = ration;
    }

    public Integer getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Integer prepareTime) {
        this.prepareTime = prepareTime;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @XmlTransient
    public Collection<InstructionMenu> getInstructionMenuCollection() {
        return instructionMenuCollection;
    }

    public void setInstructionMenuCollection(Collection<InstructionMenu> instructionMenuCollection) {
        this.instructionMenuCollection = instructionMenuCollection;
    }

    @XmlTransient
    public Collection<IngredientMenu> getIngredientMenuCollection() {
        return ingredientMenuCollection;
    }

    public void setIngredientMenuCollection(Collection<IngredientMenu> ingredientMenuCollection) {
        this.ingredientMenuCollection = ingredientMenuCollection;
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
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duongpth.persistences.Recipe[ id=" + id + " ]";
    }
    
}
