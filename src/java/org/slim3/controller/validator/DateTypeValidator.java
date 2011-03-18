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
package org.slim3.controller.validator;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.slim3.util.ApplicationMessage;

/**
 * A validator for a date value.
 * 
 * @author higa
 * @since 1.0.0
 * 
 */
public class DateTypeValidator extends AbstractValidator {

    /**
     * The pattern of {@link SimpleDateFormat}.
     */
    protected String pattern;

    /**
     * Constructor.
     * 
     * @param pattern
     *            the pattern of {@link SimpleDateFormat}
     */
    public DateTypeValidator(String pattern) {
        this(pattern, null);
    }

    /**
     * Constructor.
     * 
     * @param pattern
     *            the pattern of {@link SimpleDateFormat}
     * @param message
     *            the error message
     * @throws NullPointerException
     *             if the pattern parameter is null
     */
    public DateTypeValidator(String pattern, String message)
            throws NullPointerException {
        super(message);
        if (pattern == null) {
            throw new NullPointerException("The pattern parameter is null.");
        }
        this.pattern = pattern;
    }

    public String validate(Map<String, Object> parameters, String name) {
        Object value = parameters.get(name);
        if (value == null || "".equals(value)) {
            return null;
        }
        try {
            String s = (String) value;
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);
            format.parse(s);
            return null;
        } catch (Throwable ignore) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get(
                getMessageKey(),
                getLabel(name),
                pattern);
        }
    }

    @Override
    protected String getMessageKey() {
        return "validator.dateType";
    }
}