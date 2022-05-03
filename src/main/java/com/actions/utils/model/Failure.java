package com.actions.utils.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Builder
public class Failure implements Serializable {

    private final String code;
    private final String description;

    public String getErrorCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

}
