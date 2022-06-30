package com.schedule.domain.model;

import lombok.Getter;

@Getter
public enum Status {

    OK("3"),
    NG("2"),
    UNKNOWN("1"),
    NONE("0");

    private String value;

    Status(String status) {
        value = status;
    }

    public static Status of(String status) {
        if (status.equals(OK.value)) {
            return OK;
        } else if (status.equals(NG.value)) {
            return NG;
        } else if (status.equals(UNKNOWN.value)) {
            return UNKNOWN;
        } else {
            return NONE;
        }
    }
}
