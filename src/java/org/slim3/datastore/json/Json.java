/*
 * Copyright 2004-2010 the original author or authors.
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
package org.slim3.datastore.json;

/**
 * The Json annotation.
 * 
 * @author Takao Nakaguchi
 *
 * @since 1.0.6
 */
public @interface Json {
    /**
     * Determines whether the attribute is ignored when encoding and decoding JSON.
     * 
     * @return true if the attribute is ignored
     */
    boolean ignore() default false;

    /**
     * Determines whether the null attribute is ignored when encoding to JSON.
     * 
     * @return true if the null attribute is ignored
     */
    boolean ignoreNull() default true;

    /**
     * The alias name of the attribute in JSON.
     * 
     * @return the alias name
     */
    String alias() default "";

    /**
     * The encoder class which is responsible for encoding and decoding JSON.
     * 
     * @return the coder class
     */
    Class<? extends JsonCoder> coder() default Default.class;
}
