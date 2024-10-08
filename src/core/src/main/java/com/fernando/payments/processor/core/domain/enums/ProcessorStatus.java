package com.fernando.payments.processor.core.domain.enums;

public enum ProcessorStatus {

    PROCESSED(1),
    ERROR(2);

    private final int code;

    ProcessorStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProcessorStatus fromCode(int code) {
        for (ProcessorStatus status : ProcessorStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
