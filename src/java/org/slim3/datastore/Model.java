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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for entity.
 * 
 * @author higa
 * @since 1.0.0
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Model {

    /**
     * The kind of entity.
     */
    String kind() default "";

    /**
     * The schema version. If the value is greater than 0, the value is written
     * to datastore as slim3.schemaVersion property.
     */
    int schemaVersion() default 0;

    /**
     * The name of schemaVersionName property.
     */
    String schemaVersionName() default "slim3.schemaVersion";

    /**
     * The name of classHierarchyListName property.
     */
    String classHierarchyListName() default "slim3.classHierarchyList";
}