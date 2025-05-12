package com.automq.stream.api;

import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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

    public ByteBuf marshall(ByteBuf buf) {
        buf.writeInt(objectPath.length());
        buf.writeBytes(objectPath.getBytes(StandardCharsets.UTF_8));
        buf.writeInt(requestPosition);
        buf.writeInt(requestSize);
        buf.writeInt(recordsPosition);
        buf.writeInt(recordsSize);
        return buf;
    }

    public static RemoteRecordsLocation unmarshall(ByteBuf buf) {
        int objectPathLength = buf.readInt();
        byte[] objectPathBytes = new byte[objectPathLength];
        buf.readBytes(objectPathBytes);
        String objectPath = new String(objectPathBytes, StandardCharsets.UTF_8);
        int requestPosition = buf.readInt();
        int requestSize = buf.readInt();
        int recordsPosition = buf.readInt();
        int recordsSize = buf.readInt();
       return new RemoteRecordsLocation(objectPath, requestPosition, requestSize, recordsPosition, recordsSize);
    }

    public int encodedSize() {
        return 4 + objectPath.length() + 4 + 4 + 4 + 4;
    }
}
