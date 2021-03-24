package application.tools;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsEqualSpecification<T, V> extends AbstractSpecification<T> {

    private final String column;
    private final V value;

    public IsEqualSpecification(String column, V value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder builder) {
        return builder.and(
                builder.equal(root.get(column), this.value));
    }
}
