package com.bytepoxic.core.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public enum IdentificationType {

    LOCALE, RESIDENCEsc, AMNESTY, PASSPORT, OTHER;

    @NotNull
    private String type;

    @Size(max = 128)
    private String labelKey;
}
