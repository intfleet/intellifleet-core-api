package com.intellifleet.utils;

import com.intellifleet.annotations.DTOMap;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JPAUtils {

    private final EntityManagerFactory emf;

    public Class<?> findEntityByTableName(String tableName) {
        for (EntityType<?> entityType : emf.getMetamodel().getEntities()) {
            Class<?> clazz = entityType.getJavaType();
            if (clazz.isAnnotationPresent(Table.class)) {
                Table table = clazz.getAnnotation(Table.class);
                if (table.name().equalsIgnoreCase(tableName)) {
                    return clazz;
                }
            } else {
                // fallback: default table name = class name
                if (clazz.getSimpleName().equalsIgnoreCase(tableName)) {
                    return clazz;
                }
            }
        }
        return null;
    }

    public Class<?> findRequestPayloadDTOByEntity(Class<?> entityClass) {
        // Check if this class is a managed entity
        boolean isEntity = emf.getMetamodel()
                .getEntities()
                .stream()
                .anyMatch(e -> e.getJavaType().equals(entityClass));

        if (!isEntity) {
            throw new IllegalArgumentException(entityClass.getName() + " is not a JPA entity");
        }

        // Look for the annotation
        if (entityClass.isAnnotationPresent(DTOMap.class)) {
            DTOMap ann = entityClass.getAnnotation(DTOMap.class);
            return ann.requestPayload() == Void.class ? null : ann.requestPayload();
        }

        return null; // No annotation present
    }

    public Class<?> findListViewDTOByEntity(Class<?> entityClass) {
        // Check if this class is a managed entity
        boolean isEntity = emf.getMetamodel()
                .getEntities()
                .stream()
                .anyMatch(e -> e.getJavaType().equals(entityClass));

        if (!isEntity) {
            throw new IllegalArgumentException(entityClass.getName() + " is not a JPA entity");
        }

        // Look for the annotation
        if (entityClass.isAnnotationPresent(DTOMap.class)) {
            DTOMap ann = entityClass.getAnnotation(DTOMap.class);
            return ann.listView() == Void.class ? null : ann.listView();
        }

        return null; // No annotation present
    }
}
