package com.intellifleet.repository;


import com.intellifleet.dto.SaveOrUpdateDTO;
import com.intellifleet.exceptions.NoRecordFoundException;
import com.intellifleet.utils.ParameterVerifier;
import com.intellifleet.utils.QueryUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class GenericRepository {

    @PersistenceContext
    private EntityManager em;

    public <T> T findById(Class<T> clazz, Object id) {
        return em.find(clazz, id);
    }

    public <T> List<T> findAll(Class<T> clazz) {
        String query = "FROM " + clazz.getSimpleName();
        return em.createQuery(query, clazz).getResultList();
    }

    public <T> List<T> findAll(Class<T> clazz, String whereClause) {
        String query = "FROM " + clazz.getSimpleName();
        if(StringUtils.isNotBlank(whereClause)) {
            query += " WHERE " + whereClause;
        }
        return em.createQuery(query, clazz).getResultList();
    }

    public <T> List<T> findAll(Class<T> clazz, String whereClause, List<Object> paramValues) {
        String queryStr = "FROM " + clazz.getSimpleName();
        if(StringUtils.isNotBlank(whereClause)) {
            queryStr += " WHERE " + whereClause;
        }
        TypedQuery<T> query = em.createQuery(queryStr, clazz);
        if(paramValues != null) {
            int pos = 1;
            for (Object value : paramValues) {
                query.setParameter(pos++, value);
            }
        }
        return query.getResultList();
    }

    public <T> List<T> findByNativeQuery(Class<T> clazz, String queryStr, List<Object> paramValues) {
        Query query = em.createNativeQuery(queryStr, clazz);
        if(paramValues != null) {
            int pos = 1;
            for (Object value : paramValues) {
                query.setParameter(pos++, value);
            }
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findByNativeQuery(String queryStr) {
        return em.createNativeQuery(queryStr).getResultList();
    }

    public List<?> findByNativeQuery(String queryStr, List<Object> paramValues) {
        Query query = em.createNativeQuery(queryStr);
        if(paramValues != null) {
            int pos = 1;
            for (Object value : paramValues) {
                query.setParameter(pos++, value);
            }
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findByNativeQuery(String queryStr, Map<String, Object> paramValues) {
        Query query = em.createNativeQuery(queryStr);
        if(paramValues != null) {
            paramValues.keySet().forEach(fieldName -> {
                query.setParameter(fieldName, paramValues.get(fieldName));
            });
        }
        return query.getResultList();
    }

    public <T> List<T> findByNativeQuery(String queryStr, String resultSetMapping, List<Object> paramValues) {
        Query query = em.createNativeQuery(queryStr, resultSetMapping);
        if(paramValues != null) {
            int pos = 1;
            for (Object value : paramValues) {
                query.setParameter(pos++, value);
            }
        }
        return query.getResultList();
    }

    @Transactional
    public <T> T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    public <T> T update(T entity) {
        return em.merge(entity);
    }

    @Transactional
    public <T> void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Transactional
    public <T> void deleteById(Class<T> clazz, Long id) {
        T t = em.find(clazz, id);
        if (t != null) {
            em.remove(t);
        } else {
            throw new NoRecordFoundException();
        }
    }

    @Transactional
    public boolean saveRecord(String tableName, SaveOrUpdateDTO saveOrUpdateDTO) {
        saveOrUpdateDTO.getFieldValues().keySet().remove("id");
        // Remove all null values
        saveOrUpdateDTO.getFieldValues().values().removeIf(Objects::isNull);

        //Create query
        String query = QueryUtils.createNativeQuery(tableName, saveOrUpdateDTO.getFieldValues());
        log.info("query: {}", query);
        Query nativeQuery = em.createNativeQuery(query);

        List<String> fieldList = saveOrUpdateDTO.getFieldValues().keySet().stream().map(String::trim).toList();
        fieldList.forEach(fieldName -> {
            nativeQuery.setParameter(fieldName, saveOrUpdateDTO.getFieldValues().get(fieldName));
        });
        int updateCount = nativeQuery.executeUpdate();
        return updateCount > 0;
    }

    @Transactional
    public boolean update(String tableName, SaveOrUpdateDTO saveOrUpdateDTO, String pkFieldName) {
        // Remove all null values
        saveOrUpdateDTO.getFieldValues().values().removeIf(Objects::isNull);

        Long id = ParameterVerifier.getLong(saveOrUpdateDTO.getFieldValues().get(pkFieldName));
        saveOrUpdateDTO.getFieldValues().remove(pkFieldName);

        //Create query
        String query = QueryUtils.createUpdateNativeQuery(tableName, saveOrUpdateDTO.getFieldValues(), pkFieldName);
        Query nativeQuery = em.createNativeQuery(query);
        nativeQuery.setParameter(pkFieldName, id);

        List<String> fieldList = saveOrUpdateDTO.getFieldValues().keySet().stream().map(String::trim).toList();
        fieldList.forEach(fieldName -> {
            nativeQuery.setParameter(fieldName, saveOrUpdateDTO.getFieldValues().get(fieldName));
        });
        int updateCount = nativeQuery.executeUpdate();
        return updateCount > 0;
    }
}
