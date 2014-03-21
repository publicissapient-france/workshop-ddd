package com.xebia.domain.credit;

import com.google.inject.Inject;
import com.google.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class CreditRepositoryImpl implements CreditRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(CreditRepositoryImpl.class);

    private Provider<EntityManager> entityManagerProvider;

    @Inject
    public CreditRepositoryImpl(Provider<EntityManager> entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    public List<Credit> findAll() {
        EntityManager entityManager = entityManagerProvider.get();

        try {
            return entityManager.createQuery("from Credit").getResultList();
        }
        finally {
            entityManager.close();
        }
    }

    public Credit findOne(Long id) {
        EntityManager entityManager = entityManagerProvider.get();

        try {
            return entityManager.find(Credit.class, id);
        }
        finally {
            entityManager.close();
        }
    }

    public void save(Credit credit) {
        EntityManager entityManager = entityManagerProvider.get();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(credit);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();

            LOGGER.error("Unable to save credit: {}", e.getCause());
        }
        finally {
            entityManager.close();
        }
    }

}
