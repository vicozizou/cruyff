package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum PhoneType {

    HOME_PHONE, HOME_FAX, WORK_PHONE, WORK_FAX, MOBILE_PHONE;

    @NotNull
    private String type;

    @Size(max = 128)
    private String labelKey;
}
