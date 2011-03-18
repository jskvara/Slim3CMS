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

/**
 * A meta data of collection attribute.
 * 
 * @author higa
 * @param <M>
 *            the model type
 * @param <A>
 *            the attribute type
 * @since 1.0.0
 * 
 */
public class StringCollectionAttributeMeta<M, A> extends
        CollectionAttributeMeta<M, A, String> {

    /**
     * Constructor.
     * 
     * @param modelMeta
     *            the meta data of model
     * @param name
     *            the name
     * @param fieldName
     *            the field name
     * @param attributeClass
     *            the attribute class
     * @throws NullPointerException
     *             if the modelMeta parameter is null or if the name parameter
     *             is null or if the attributeClass parameter is null or if the
     *             fieldName parameter is null
     */
    public StringCollectionAttributeMeta(ModelMeta<M> modelMeta, String name,
            String fieldName, Class<? super A> attributeClass) {
        super(modelMeta, name, fieldName, attributeClass);
    }

    /**
     * Returns the "startsWith" filter.
     * 
     * @param value
     *            the value
     * @return the "startsWith" filter
     */
    public StartsWithCriterion startsWith(String value) {
        return new StartsWithCriterion(this, value);
    }

    /**
     * Returns the "endsWith" in-memory filter.
     * 
     * @param value
     *            the value
     * @return the "endsWith" in-memory filter
     */
    public InMemoryEndsWithCriterion endsWith(String value) {
        return new InMemoryEndsWithCriterion(this, value);
    }

    /**
     * Returns the "contains" in-memory filter.
     * 
     * @param value
     *            the value
     * @return the "contains" in-memory filter
     */
    public InMemoryContainsCriterion contains(String value) {
        return new InMemoryContainsCriterion(this, value);
    }
}