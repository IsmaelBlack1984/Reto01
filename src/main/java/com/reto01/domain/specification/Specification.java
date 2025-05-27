package com.reto01.domain.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);
    // En el futuro, podríamos añadir métodos para ayudar a traducir esto
    // a criterios de persistencia (ej: toPredicate(CriteriaBuilder cb, Root<T> root)),
    // pero por ahora, isSatisfiedBy es suficiente para el adaptador en memoria
    // y como base del patrón.
}
