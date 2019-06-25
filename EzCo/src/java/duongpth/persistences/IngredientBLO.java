/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.persistences;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author dragn
 */
public class IngredientBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EzCoPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean insert(duongpth.jaxbs.Ingredient ingredient) {
        EntityManager em = emf.createEntityManager();

        Ingredient ing = em.find(Ingredient.class, ingredient.getId());

        if (ing == null) {
            ing = new Ingredient();
            ing.setId(ingredient.getId());
            ing.setImage(ingredient.getImage());
            ing.setLink(ingredient.getLink());
            ing.setName(ingredient.getName());
            ing.setPrice(ingredient.getPrice());
            ing.setUnit(ingredient.getUnit());

            em.getTransaction().begin();
            em.persist(ing);
            em.getTransaction().commit();
            return true;
        }

        return false;
    }
}
