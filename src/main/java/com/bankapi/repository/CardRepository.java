package com.bankapi.repository;

import com.bankapi.model.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CardRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public List<CardEntity> findAll() {
        EntityManager em = getEntityManager();
        List<CardEntity> cards = em.createQuery("SELECT cards from CardEntity cards ").getResultList();
        return cards;
    }

    public Optional<CardEntity> findById(Long id) {
        EntityManager em = getEntityManager();
        CardEntity entity = em.find(CardEntity.class, id);
        em.detach(entity);
        return Optional.ofNullable(entity);
    }

    public CardEntity createCard(CardEntity entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    public CardEntity updateCard(Long id, CardEntity account) {
        account.setId(id);
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(account);
        em.getTransaction().commit();
        return account;
    }

    public void deleteById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CardEntity cardEntity = em.find(CardEntity.class, id);
        em.remove(cardEntity);
        em.getTransaction().commit();
    }

}


