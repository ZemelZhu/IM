package com.im.zemel.type;

public enum MeeageStatusType {
    UNREAD(0),
    READ(1),
    UNCHANGEABLE(3);
    private final byte value;

    MeeageStatusType(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }
}
