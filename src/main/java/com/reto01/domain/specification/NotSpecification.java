package com.reto01.domain.specification;

public class NotSpecification<T> implements Specification<T> {
    private final Specification<T> other;

    public NotSpecification(Specification<T> other) {
        this.other = other;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !other.isSatisfiedBy(candidate);
    }

    // Getter for other
    public Specification<T> getOther() {
        return other;
    }
}
