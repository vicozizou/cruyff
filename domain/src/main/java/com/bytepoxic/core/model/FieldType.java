package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum FieldType {

    TEXT_TYPE, INTEGER_TYPE, LONG_TYPE, FLOAT_TYPE, DOUBLE_TYPE, BIG_DECIMAL_TYPE, BOOLEAN_TYPE, DATE_TYPE;

    @NotNull
    private String type;

    @NotNull
    private String javaType;

    @Size(max = 128)
    private String labelKey;
}
