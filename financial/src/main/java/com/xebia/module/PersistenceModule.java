package com.xebia.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import com.xebia.domain.credit.CreditRepositoryImpl;
import com.xebia.domain.credit.CreditService;
import com.xebia.domain.credit.CreditRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceModule extends AbstractModule {

    private EntityManagerFactory entityManagerFactory;

    @Override
    protected void configure() {
        entityManagerFactory = Persistence.createEntityManagerFactory("dddDB");

        bind(CreditRepository.class).to(CreditRepositoryImpl.class);

        bind(CreditService.class);
    }

    @Provides
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
