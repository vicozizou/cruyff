package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum UserStatus {

    ACTIVE, BLOCKED, EXPIRED;

    @NotNull
    private String userStatus;

    @Size(max = 128)
    private String labelKey;
}
