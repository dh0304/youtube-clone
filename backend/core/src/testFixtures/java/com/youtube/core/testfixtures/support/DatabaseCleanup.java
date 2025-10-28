package com.youtube.core.testfixtures.support;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@TestComponent
public class DatabaseCleanup {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames = new ArrayList<>();

    @PostConstruct
    public void afterPropertiesSet() {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tableNames = entities.stream()
                .map(EntityType::getJavaType)
                .filter(this::isValidEntityForCleanup)
                .map(this::extractTableName)
                .toList();
    }

    private boolean isValidEntityForCleanup(Class<?> javaType) {
        return javaType.getAnnotation(Entity.class) != null &&
                // 단일 테이블 상속(JPA 테이블 상속 전략)을 사용하지만 부모 클래스가 아닌 엔티티는 건너뛴다
                (javaType.getAnnotation(Inheritance.class) != null ||
                        javaType.getAnnotation(DiscriminatorValue.class) == null);
    }

    private String extractTableName(Class<?> javaType) {
        Table tableAnnotation = javaType.getAnnotation(Table.class);

        return tableAnnotation == null || tableAnnotation.name().isEmpty()
                ? javaType.getSimpleName().toLowerCase()
                : tableAnnotation.name();
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }

        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
