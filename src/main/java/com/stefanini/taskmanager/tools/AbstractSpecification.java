package com.stefanini.taskmanager.tools;

import org.hibernate.cfg.NotYetImplementedException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractSpecification<T> {

    public Predicate toPredicate(Root<T> root, CriteriaBuilder builder) {
        throw new NotYetImplementedException();
    }
}
