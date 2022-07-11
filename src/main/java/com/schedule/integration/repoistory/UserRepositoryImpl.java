package com.schedule.integration.repoistory;

import com.schedule.domain.model.User;
import com.schedule.domain.repository.UserRepository;
import com.schedule.integration.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private final EntityManager entityManager;


    private final String SELECT_USER_BY_NAME = "FROM UserEntity WHERE user_name = :name";
    private final String SELECT_USER_LIKE_NAME = "SELECT u FROM UserEntity u WHERE UPPER(u.userName) LIKE UPPER(:name)";
    private final String DELETE_USER = "DELETE FROM UserEntity WHERE user_name = :name";

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String name) {
        TypedQuery<UserEntity> query = entityManager.createQuery(SELECT_USER_BY_NAME, UserEntity.class);
        query.setParameter("name", name);
        UserEntity userEntity = null;
        try {
            userEntity = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return userEntity.toModel();
    }

    @Override
    @Transactional
    public void addUser(String name, String password, String address) {
        entityManager.persist(UserEntity.toEntity(name, password, address));
    }

    @Override
    public List<User> searchUsers(String name) {
        TypedQuery<UserEntity> query = entityManager.createQuery(SELECT_USER_LIKE_NAME, UserEntity.class);
        query.setParameter("name", "%" + name + "%");
        List<UserEntity> userEntities = query.getResultList();

        return userEntities.stream().map(UserEntity::toModel).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void editUser(String name, String password, String address) {
        TypedQuery<UserEntity> query = entityManager.createQuery(SELECT_USER_BY_NAME, UserEntity.class);
        query.setParameter("name", name);
        UserEntity userEntity = query.getSingleResult();
        userEntity.setPassword(password);
        userEntity.setAddress(address);
    }

    @Override
    @Transactional
    public void deleteUser(String name) {
        entityManager.createQuery(DELETE_USER)
        .setParameter("name", name)
        .executeUpdate();
    }
}
