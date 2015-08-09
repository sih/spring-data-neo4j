/*
 * Copyright (c)  [2011-2015] "Pivotal Software, Inc." / "Neo Technology" / "Graph Aware Ltd."
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package org.springframework.data.neo4j.repository;

import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Vince Bickers
 */
@Repository
public class GraphRepositoryImpl<T> implements GraphRepository<T> {

    private final Class clazz;
    private final Session session;

    public GraphRepositoryImpl(Class clazz, Session session) {
        this.clazz = clazz;
        this.session = session;
    }

    @Override
    public <S extends T> S save(S s) {
        session.save(s);
        return s;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> ses) {
        for (S s : ses) {
            session.save(s);
        }
        return null;
    }

    @Override
    public T findOne(Long id) {
        return (T) session.load(clazz, id);
    }

    @Override
    public boolean exists(Long id) {
        return findOne(id) != null;
    }

    @Override
    public Iterable<T> findAll() {
        return (Iterable<T>) session.loadAll(clazz);
    }

    @Override
    public Iterable<T> findAll(Iterable<Long> longs) {
        return (Iterable<T>) session.loadAll(clazz, (Collection<Long>) longs);
    }

    @Override
    public long count() {
        Collection<?> all = (Collection) findAll();
        return all.size();
    }

    @Override
    public void delete(Long id) {
        Object o = findOne(id);
        if (o != null) {
            session.delete(o);
        }
    }

    @Override
    public void delete(T t) {
        session.delete(t);
    }

    @Override
    public void delete(Iterable<? extends T> ts) {
        for (T t : ts) {
            session.delete(t);
        }
    }

    @Override
    public void deleteAll() {
        session.deleteAll(clazz);
    }

    @Override
    public <S extends T> S save(S s, int depth) {
        session.save(s, depth);
        return s;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> ses, int depth) {
        session.save(ses, depth);
        return null;
    }

    @Override
    public T findOne(Long id, int depth) {
        return (T) session.load(clazz, id, depth);
    }

    @Override
    public Iterable<T> findAll(int depth) {
        return (Iterable<T>) session.loadAll(clazz, depth);
    }

    @Override
    public Iterable<T> findAll(Iterable<Long> ids, int depth) {
        return (Iterable<T>) session.loadAll(clazz, (Collection<Long>) ids, depth);
    }

//  removed
//    @Override
//    public Iterable<T> findByProperty(String propertyName, Object propertyValue) {
//        return (Iterable<T>) session.loadByProperty(clazz, new Property(propertyName, propertyValue));
//    }
//
//    @Override
//    public Iterable<T> findByProperty(String propertyName, Object propertyValue, int depth) {
//        return (Iterable<T>) session.loadByProperty(clazz, new Property(propertyName, propertyValue), depth);
//    }
}
