package com.fjmp.services;

import com.fjmp.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataInitializer {
    @Inject
    DataService dataService;
    
    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (dataService.getAllUsers().isEmpty()) {
            User sally = dataService.createUser("Sally Addams", "saddams", "saddams", "admin");
            User tom = dataService.createUser("Tom Matthews", "tmatthews", "tmatthews", "user");
            
            dataService.createQuality("Wonderful", sally);
            dataService.createQuality("Team player", sally);
            dataService.createQuality("Good Judgement", sally);
            dataService.createQuality("Good Leader", sally);
            
            dataService.createQuality("Dilligent", tom);
            dataService.createQuality("Responsible", tom);
            dataService.createQuality("Cares for his teammates", tom);            
        }
    }

}
