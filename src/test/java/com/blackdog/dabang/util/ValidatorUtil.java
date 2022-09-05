package com.blackdog.dabang.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.apache.commons.collections4.CollectionUtils;

public final class ValidatorUtil {
    private ValidatorUtil() {
        throw new UnsupportedOperationException("A utility class cannot be instantiated");
    }

    public static final void validate(Validator validator, Object param, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(param, groups);
        if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
            throw new ConstraintViolationException(constraintViolationSet);
        }
    }
}
