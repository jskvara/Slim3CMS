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
package org.slim3.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The locator for {@link HttpServletRequest}.
 * 
 * @author higa
 * @since 1.0.0
 * 
 */
public final class RequestLocator {

    private static ThreadLocal<HttpServletRequest> requests =
        new ThreadLocal<HttpServletRequest>();

    /**
     * Returns the request attached to the current thread.
     * 
     * @return the request attached to the current thread
     */
    public static HttpServletRequest get() {
        return requests.get();
    }

    /**
     * Sets the request to the current thread.
     * 
     * @param request
     *            the request
     */
    public static void set(HttpServletRequest request) {
        requests.set(request);
    }

    private RequestLocator() {
    }
}