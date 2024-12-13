package com.fjmp.controllers;

import com.fjmp.entities.Quality;
import com.fjmp.entities.User;
import com.fjmp.services.DataService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Named
public class HomeController {
    @Inject
    DataService dataService;
    
    private Optional<User> currentUser;
    private List<Quality> currentQualities;
    
    @PostConstruct
    public  void initialize() {
        String username = "saddams";
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent( user -> {
            this.currentQualities = dataService.getQualities(user);
        });
    }

    public User getCurrentUser() {
        return this.currentUser.orElse(null);
    }

    public List<Quality> getCurrentQualities() {
        return currentQualities;
    }
    
    
}
