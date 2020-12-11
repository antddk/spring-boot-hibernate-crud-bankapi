package com.bankapi.repository;

import com.bankapi.model.AccountEntity;
import com.bankapi.model.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public AccountRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("DROP TABLE IF EXISTS ACCOUNT;")
                .executeUpdate();
        em.createNativeQuery("CREATE TABLE ACCOUNT (" +
                "  id INT AUTO_INCREMENT  PRIMARY KEY," +
                "  first_name VARCHAR(250) NOT NULL," +
                "  last_name VARCHAR(250) NOT NULL," +
                "  account BIGINT DEFAULT NULL," +
                "  balance FLOAT DEFAULT 0.0" +
                ");").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS CARD;")
                .executeUpdate();
        em.createNativeQuery("CREATE TABLE CARD (" +
                "  id INT AUTO_INCREMENT  PRIMARY KEY," +
                "  card_number VARCHAR(250) NOT NULL," +
                "  expdate TIMESTAMP NOT NULL," +
                "  cvv INT NOT NULL," +
                "  account_id INT NOT NULL" +
                ");").executeUpdate();
        em.createNativeQuery("INSERT INTO ACCOUNT (first_name, last_name, account, balance) VALUES ('Lokesh', 'Gupta', 1111222212334444, '123.23'),('John', 'Doe', 2222336644445555, '3847.13');").executeUpdate();
        em.createNativeQuery("INSERT INTO ACCOUNT (first_name, last_name, account, balance) VALUES ('JHfhgfjh', 'Rruigregjkgh', 1114022233334444, '123.23'),('Marta', 'Ddd', 2222333399445555, '3847.13');").executeUpdate();
        em.createNativeQuery("INSERT INTO ACCOUNT (first_name, last_name, account, balance) VALUES ('RRRRRRRRRRRRRRR', 'Ajkgjkhg', 1111220033334444, '123.23'),('Ignat', 'Panteleevich', 2222324344445555, '3847.13');").executeUpdate();
        em.createNativeQuery("INSERT INTO CARD (card_number, expdate, cvv, account_id) VALUES ('1111 2222 3333 4444', {ts '2022-09-17 00:00:00'}, 123, 1),('1111 2322 3333 4444', {ts '2024-10-01 00:00:00'}, 345, 2);").executeUpdate();
        em.createNativeQuery("INSERT INTO CARD (card_number, expdate, cvv, account_id) VALUES ('4561 2222 3355 4444', {ts '2022-09-17 00:00:00'}, 123, 3),('5869 2322 4563 4444', {ts '2024-10-01 00:00:00'}, 345, 4);").executeUpdate();
        em.createNativeQuery("INSERT INTO CARD (card_number, expdate, cvv, account_id) VALUES ('4510 2222 3355 4444', {ts '2022-09-17 00:00:00'}, 123, 5);").executeUpdate();
        em.createNativeQuery("INSERT INTO CARD (card_number, expdate, cvv, account_id) VALUES ('7593 2222 3355 4444', {ts '2024-09-17 00:00:00'}, 123, 5);").executeUpdate();
        em.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public List<AccountEntity> findAll() {
        EntityManager em = getEntityManager();
        List<AccountEntity> movies = em.createQuery("SELECT accounts from AccountEntity accounts ").getResultList();
        return movies;
    }

    public Optional<AccountEntity> findById(Long id) {
        EntityManager em = getEntityManager();
        AccountEntity entity = em.find(AccountEntity.class, id);
        em.detach(entity);
        return Optional.ofNullable(entity);
    }

    public List<AccountEntity> findByAcc(Long accId) {
        EntityManager em = getEntityManager();
        List<AccountEntity> ac = em.createQuery("SELECT accounts from AccountEntity accounts WHERE account = "+accId).getResultList();
        return ac;
    }




    public AccountEntity createAccount(AccountEntity entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    public AccountEntity updateAccount(Long id, AccountEntity account) {
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
        AccountEntity accountEntity = em.find(AccountEntity.class, id);
        em.remove(accountEntity);
        em.getTransaction().commit();
    }
}
