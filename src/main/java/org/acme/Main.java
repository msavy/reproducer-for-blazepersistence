package org.acme;

import com.blazebit.persistence.CriteriaBuilderFactory;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class Main {
    @Inject
    CriteriaBuilderFactory criteriaBuilderFactory;

    @Inject
    EntityManagerFactory emf;

    void startup(@Observes StartupEvent event) {
    }

    public Main() {

    }

    @PostConstruct
    public void postConstruct() {
        criteriaBuilderFactory.create(emf.createEntityManager(), io.apiman.manager.api.beans.clients.ClientBean.class);
        System.out.println("created successfully?");
    }
}
