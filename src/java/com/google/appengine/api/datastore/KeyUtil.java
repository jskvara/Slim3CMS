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
package com.google.appengine.api.datastore;

/**
 * A utility for key.
 * 
 * @author higa
 * @since 1.0.0
 * 
 */
public final class KeyUtil {

    /**
     * Sets the identifier to the key.
     * 
     * @param key
     *            the key
     * @param id
     *            the identifier
     */
    public static void setId(Key key, long id) {
        key.setId(id);
    }

    private KeyUtil() {
    }
}