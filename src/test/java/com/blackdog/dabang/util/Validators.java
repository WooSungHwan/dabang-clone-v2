package com.blackdog.dabang.util;

import javax.validation.Validation;
import javax.validation.Validator;

public final class Validators {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private Validators() {
    }

    public static <T> void validate(T t, Class<?>... groups) {
        ValidatorUtil.validate(VALIDATOR, t, groups);
    }
}
