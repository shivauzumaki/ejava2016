package com.business;

import com.model.People;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;

/**
 *
 * @author Vishnu
 */
@Stateless
@Path("/people")
public class PersonBean {
    
    private static final String findByEmailQuery = "select p from People p where p.email = :email";
            
    @PersistenceContext private EntityManager em;
    
    public void addPerson(People people) {
        
        String uniqueID = UUID.randomUUID().toString().substring(0, 8);
        people.setPid(uniqueID);
        em.persist(people);
    }
    
    
    public Optional<People> findByEmail(String email) {
        
        //System.out.println("Reached PersonBean : email is  "+ email );
        TypedQuery<People> query = em.createQuery(findByEmailQuery, People.class);
        query.setParameter("email", email);
        People singleResult = null;
        try{
           singleResult =  query.getSingleResult();
        }catch(Exception ex){
            
        }
        return Optional.ofNullable(singleResult);
    }
}
