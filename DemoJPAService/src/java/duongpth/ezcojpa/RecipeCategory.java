/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.ezcojpa;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dragn
 */
@Entity
@Table(name = "RecipeCategory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeCategory.findAll", query = "SELECT r FROM RecipeCategory r")
    , @NamedQuery(name = "RecipeCategory.findById", query = "SELECT r FROM RecipeCategory r WHERE r.id = :id")
    , @NamedQuery(name = "RecipeCategory.findByName", query = "SELECT r FROM RecipeCategory r WHERE r.name = :name")
    , @NamedQuery(name = "RecipeCategory.findByLink", query = "SELECT r FROM RecipeCategory r WHERE r.link = :link")})
public class RecipeCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 1000)
    @Column(name = "Name")
    private String name;
    @Size(max = 2000)
    @Column(name = "Link")
    private String link;
    @OneToMany(mappedBy = "cateID")
    private Collection<CateRep> cateRepCollection;

    public RecipeCategory() {
    }

    public RecipeCategory(Integer id) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlTransient
    public Collection<CateRep> getCateRepCollection() {
        return cateRepCollection;
    }

    public void setCateRepCollection(Collection<CateRep> cateRepCollection) {
        this.cateRepCollection = cateRepCollection;
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
        if (!(object instanceof RecipeCategory)) {
            return false;
        }
        RecipeCategory other = (RecipeCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duongpth.ezcojpa.RecipeCategory[ id=" + id + " ]";
    }
    
}
