package com.ravensoftware.reporting.base;

/**
 * Created by bilga
 */
public enum Operation {
    DIRECT("DIRECT"),
    REFUND("REFUND"),
    THREE_D("3D"),
    THREE_D_AUTH("3DAUTH"),
    STORED("STORED");

    private String label;

    Operation(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
