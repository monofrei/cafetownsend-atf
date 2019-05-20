package org.cafetownsend.atf.ui.pages;

import org.cafetownsend.atf.ui.elements.ValidatedInput;
import org.cafetownsend.atf.ui.utils.ReflectionUtils;

import java.util.Objects;

public interface Validatable {

    default boolean hasValidationErrors(String detail) {

        ValidatedInput input = ReflectionUtils.getFieldInstanceByName(detail, this);

        return !Objects.equals(input, null) && input.hasValidationErrors();
    }
}
