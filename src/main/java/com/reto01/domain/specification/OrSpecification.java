package com.reto01.domain.specification;

public class OrSpecification<T> implements Specification<T> {
    private final Specification<T> left;
    private final Specification<T> right;

    public OrSpecification(Specification<T> left, Specification<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) || right.isSatisfiedBy(candidate);
    }

    // Getters for left and right
    public Specification<T> getLeft() {
        return left;
    }

    public Specification<T> getRight() {
        return right;
    }
}
