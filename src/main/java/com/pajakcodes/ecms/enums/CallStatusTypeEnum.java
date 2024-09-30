package com.pajakcodes.ecms.enums;

public enum CallStatusTypeEnum {

    CREATED(1L),
    PROCESSING(2L),
    CLOSED(3L);

    private long id;

    CallStatusTypeEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static CallStatusTypeEnum fromId(long id) {
        for (CallStatusTypeEnum status : CallStatusTypeEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + id);
    }

}
