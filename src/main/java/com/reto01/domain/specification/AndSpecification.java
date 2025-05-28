package com.reto01.domain.specification;

public class AndSpecification<T> implements Specification<T> {
    private final Specification<T> left;
    private final Specification<T> right;

    public AndSpecification(Specification<T> left, Specification<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) && right.isSatisfiedBy(candidate);
    }

    // Getters for left and right can be useful for inspection or translation
    public Specification<T> getLeft() {
        return left;
    }

    public Specification<T> getRight() {
        return right;
    }
}
