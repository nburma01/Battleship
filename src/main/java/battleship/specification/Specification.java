package battleship.specification;

public interface Specification<T> {

    /**
     * @param t
     * @return true if t satisfies the current specification.
     */
    boolean isSatisfiedBy(T t);

    /**
     * @param specification
     * @return the boolean logical AND between the two specifications.
     */
    Specification<T> and(Specification<T> specification);

    /**
     * @param specification
     * @return the boolean logical OR between the two specifications.
     */
    Specification<T> or(Specification<T> specification);

    /**
     * @param specification
     * @return the boolean logical NOT between the two specifications.
     */
    Specification<T> not(Specification<T> specification);
}
