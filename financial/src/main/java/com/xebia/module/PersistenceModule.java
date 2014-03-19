package com.xebia.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.xebia.application.CreditApplicationService;
import com.xebia.domain.credit.CreditRepository;
import com.xebia.infrastructure.persistence.HibernateCreditRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceModule extends AbstractModule {

    private EntityManagerFactory entityManagerFactory;

    @Override
    protected void configure() {
        entityManagerFactory = Persistence.createEntityManagerFactory("dddDB");

        bind(CreditRepository.class).to(HibernateCreditRepository.class);

        bind(CreditApplicationService.class);
    }

    @Provides
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
