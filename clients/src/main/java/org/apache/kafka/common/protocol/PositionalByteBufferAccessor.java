package org.apache.kafka.common.protocol;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.kafka.common.record.MemoryRecords;
import org.apache.kafka.common.zerozone.Position;

public class PositionalByteBufferAccessor extends ByteBufferAccessor {
    // A map from index of records to its position in the buffer
    private final Map<Integer, Position> recordsPositionMap = new HashMap<>();
    private final AtomicInteger index = new AtomicInteger(0);
    private final int startOffset;

    public PositionalByteBufferAccessor(ByteBuffer buf) {
        this(0, buf);
    }

    public PositionalByteBufferAccessor(int startOffset, ByteBuffer buf) {
        super(buf);
        this.startOffset = startOffset;
    }

    @Override
    public MemoryRecords readRecords(int length) {
        if (length >= 0) {
            recordsPositionMap.put(index.getAndIncrement(), new Position(startOffset + buffer().position(), length));
        }
        return super.readRecords(length);
    }

    public Map<Integer, Position> getRecordsPositionMap() {
        return recordsPositionMap;
    }
}
