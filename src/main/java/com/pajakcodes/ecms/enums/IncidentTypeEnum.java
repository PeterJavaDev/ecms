package com.pajakcodes.ecms.enums;

public enum IncidentTypeEnum {

    NATURAL(1L),
    MEDICAL(2L),
    CRIMINAL(3L),
    TRAFFIC(4L);

    private long id;

    IncidentTypeEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static IncidentTypeEnum fromId(long id) {
        for (IncidentTypeEnum type : IncidentTypeEnum.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + id);
    }

}
