package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum EmailType {

    HOME_EMAIL, WORK_EMAIL;

    @NotNull
    private String type;

    @Size(max = 128)
    private String labelKey;
}
