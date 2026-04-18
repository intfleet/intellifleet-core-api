package com.intellifleet.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DBDataTypeMap {

    STRING("text", DataType.STRING),
    INTEGER("integer", DataType.INTEGER),
    DOUBLE_DECIMAL("decimal", DataType.DOUBLE),
    DOUBLE_NUMERIC("numeric", DataType.DOUBLE),
    FLOAT("real", DataType.FLOAT),
    DOUBLE("double precision", DataType.DOUBLE),
    DATE("date", DataType.DATE),
    TIME("time", DataType.TIME),
    TIMESTAMP("timestamp", DataType.DATETIME),
    BOOLEAN("boolean", DataType.BOOLEAN);

    private final String dbType;
    private final DataType jsonType;

    public static DataType getJsonType(String dbType) {
        for (DBDataTypeMap e : values()) {
            if (e.dbType.equalsIgnoreCase(dbType) || dbType.contains(e.dbType)) {
                return e.jsonType;
            }
        }
        throw new IllegalArgumentException("No params found for type: " + dbType);
    }
}
