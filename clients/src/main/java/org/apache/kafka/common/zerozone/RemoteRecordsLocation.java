package org.apache.kafka.common.zerozone;

public class RemoteRecordsLocation {
    private final String objectPath;
    private final int requestPosition;
    private final int requestSize;
    private final int recordsPosition;
    private final int recordsSize;

    public RemoteRecordsLocation(String objectPath, int requestPosition, int requestSize, int recordsPosition, int recordsSize) {
        this.objectPath = objectPath;
        this.requestPosition = requestPosition;
        this.requestSize = requestSize;
        this.recordsPosition = recordsPosition;
        this.recordsSize = recordsSize;
    }

    public String objectPath() {
        return objectPath;
    }

    public int requestPosition() {
        return requestPosition;
    }

    public int requestSize() {
        return requestSize;
    }

    public int recordsPosition() {
        return recordsPosition;
    }

    public int recordsSize() {
        return recordsSize;
    }
}
