/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.persistences;

import duongpth.jaxbs.Instructionmenu;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author dragn
 */
public class RecipeBLO implements Serializable{

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
    
    public boolean insert(duongpth.jaxbs.Recipe recipe) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        transaction.begin();
        Recipe r = new Recipe();
        
        Instructionmenu instructionMenu = recipe.getInstructionmenu();
//        r.setCookingTime(recipe.getCookingtime());
//        r.setDescription(recipe.getDescription());
        
        return false;
    }
}
