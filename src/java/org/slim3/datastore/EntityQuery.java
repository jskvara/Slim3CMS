/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.slim3.datastore;

import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;

/**
 * A query for datastore entity.
 * 
 * @author higa
 * @since 1.0.0
 * 
 */
public class EntityQuery extends AbstractQuery<EntityQuery> {

    /**
     * Constructor.
     * 
     * @param ds
     *            the asynchronous datastore service
     * @param kind
     *            the kind
     * @throws NullPointerException
     *             if the ds parameter is null or if the kind parameter is null
     * 
     */
    public EntityQuery(AsyncDatastoreService ds, String kind)
            throws NullPointerException {
        super(ds, kind);
    }

    /**
     * Constructor.
     * 
     * @param ds
     *            the asynchronous datastore service
     * @param kind
     *            the kind
     * @param ancestorKey
     *            the ancestor key
     * @throws NullPointerException
     *             if the ds parameter is null or if the kind parameter is null
     *             or if the ancestorKey parameter is null
     * 
     */
    public EntityQuery(AsyncDatastoreService ds, String kind, Key ancestorKey)
            throws NullPointerException {
        super(ds, kind, ancestorKey);
    }

    /**
     * Constructor.
     * 
     * @param ds
     *            the asynchronous datastore service
     * @param tx
     *            the transaction
     * @param kind
     *            the kind
     * @param ancestorKey
     *            the ancestor key
     * @throws NullPointerException
     *             if the ds parameter is null if the kind parameter is null or
     *             if the ancestorKey parameter is null
     * 
     */
    public EntityQuery(AsyncDatastoreService ds, Transaction tx, String kind,
            Key ancestorKey) throws NullPointerException {
        super(ds, kind, ancestorKey);
        setTx(tx);
    }

    /**
     * Returns entities as a list.
     * 
     * @return entities as a list
     */
    public List<Entity> asList() {
        return super.asEntityList();
    }

    /**
     * Returns a query result list.
     * 
     * @return a query result list
     */
    public QueryResultList<Entity> asQueryResultList() {
        return super.asQueryResultEntityList();
    }

    /**
     * Returns a query result iterator.
     * 
     * @return a query result iterator
     */
    public QueryResultIterator<Entity> asQueryResultIterator() {
        return super.asQueryResultEntityIterator();
    }

    @Override
    public Entity asSingleEntity() {
        return super.asSingleEntity();
    }

    /**
     * Returns entities as {@link Iterable}.
     * 
     * @return entities as {@link Iterable}
     */
    public Iterable<Entity> asIterable() {
        return super.asIterableEntities();
    }

    /**
     * Returns entities as {@link Iterator}.
     * 
     * @return entities as {@link Iterator}
     */
    public Iterator<Entity> asIterator() {
        return asEntityIterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T min(String propertyName) throws NullPointerException {
        return (T) super.min(propertyName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T max(String propertyName) throws NullPointerException {
        return (T) super.max(propertyName);
    }

}