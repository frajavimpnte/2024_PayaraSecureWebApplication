package com.fjmp.services;

import com.fjmp.entities.Quality;
import com.fjmp.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DataService {
    @PersistenceContext(unitName = "PayarSecureWebApplication_unit")
    EntityManager em;
    
    @Transactional
    public User createUser(String name, String username, String password, String group) {
        User newUser = new User(name, username, password, group);
        em.persist(newUser);
        em.flush();
        return newUser;
    }
    
    @Transactional
    public Quality createQuality(String description, User user) {
        Quality newQuality = new Quality(description, user);
        em.persist(newQuality);
        em.flush();
        return newQuality;
    }
    
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.all", User.class).getResultList();
    }
    
    public Optional<User> getUser(String username) {
        return em.createNamedQuery("User.byUsername", User.class)
                    .setParameter("username", username)
                    .getResultList()
                    .stream()
                    .findFirst();
    }
    
    public List<Quality> getQualities(User user) {
        return em.createNamedQuery("Quality.byUser", Quality.class)
                 .setParameter("userId", user.getId())
                 .getResultList();
    }
}
