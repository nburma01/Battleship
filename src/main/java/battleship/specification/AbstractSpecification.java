package battleship.specification;

/**
 * Specification pattern
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    /*
     * (non-Javadoc)
     * 
     * @see battleship.specification.Specification#isSatisfiedBy(java.lang.Object)
     */
    @Override
    public abstract boolean isSatisfiedBy(T t);

    /*
     * (non-Javadoc)
     * 
     * @see battleship.specification.Specification#and(battleship.specification.Specification)
     */
    @Override
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.specification.Specification#or(battleship.specification.Specification)
     */
    @Override
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<T>(this, specification);
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.specification.Specification#not(battleship.specification.Specification)
     */
    @Override
    public Specification<T> not(final Specification<T> specification) {
        return new NotSpecification<T>(specification);
    }
}
