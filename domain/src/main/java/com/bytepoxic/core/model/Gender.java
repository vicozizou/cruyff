package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum Gender {

    MALE, FEMALE;

    @NotNull
    private String gender;

    @Size(max = 128)
    private String labelKey;
}
