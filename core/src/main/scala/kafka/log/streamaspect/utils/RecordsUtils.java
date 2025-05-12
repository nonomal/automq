package kafka.log.streamaspect.utils;

import com.automq.stream.api.RemoteRecordsLocation;

public class RecordsUtils {
    public static RemoteRecordsLocation translate(org.apache.kafka.common.zerozone.RemoteRecordsLocation location) {
        return new RemoteRecordsLocation(location.objectPath(), location.requestPosition(), location.requestSize(), location.recordsPosition(), location.recordsSize());
    }
}
