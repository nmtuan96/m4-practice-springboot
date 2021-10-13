package com.example.practice.service.interfaces;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public interface GenericQuery<T>{
    default Path<T> getPath(Root<T> root, String[] properties) {
        Path<T> path = properties.length == 0? null : root.get(properties[0]);
        for (int i = 0; i < properties.length; i++) {
            path = path.get(properties[i]);
        }
        return path;
    }

    default Specification<T> getQueryNotNull(String... properties) {
        return (root, query, cb) -> cb.isNotNull(getPath(root, properties));
    }

    default Specification<T> getQueryForEqual(Object value, String... properties) {
        return (root, query, cb) -> cb.equal( getPath(root, properties), value);
    }
}
