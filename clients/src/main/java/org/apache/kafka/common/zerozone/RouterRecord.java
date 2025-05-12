/*
 * Copyright 2025, AutoMQ HK Limited.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.common.zerozone;

import java.nio.ByteBuffer;

public class RouterRecord {
    private static final short MAGIC = 0x01;
    private final int nodeId;
    private final short bucketId;
    private final long objectId;
    private final Position position;

    public RouterRecord(int nodeId, short bucketId, long objectId, Position position) {
        this.nodeId = nodeId;
        this.bucketId = bucketId;
        this.objectId = objectId;
        this.position = position;
    }

    public int nodeId() {
        return nodeId;
    }

    public short bucketId() {
        return bucketId;
    }

    public long objectId() {
        return objectId;
    }

    public Position position() {
        return position;
    }

    public int size() {
        return position.size();
    }

    public ByteBuffer encode() {
        ByteBuffer buf = ByteBuffer.allocate(1 /* magic */ + 4 /* nodeId */ + 2 /* bucketId */ + 8 /* objectId */ + 4 /* position */ + 4 /* size */);
        buf.putShort(MAGIC);
        buf.putInt(nodeId);
        buf.putShort(bucketId);
        buf.putLong(objectId);
        buf.putInt(position.position());
        buf.putInt(position.size());
        return buf;
    }

    @Override
    public String toString() {
        return "RouterRecord{" +
            "nodeId=" + nodeId +
            ", bucketId=" + bucketId +
            ", objectId=" + objectId +
            ", position=" + position.position() +
            ", size=" + position.size() +
            '}';
    }

    public static RouterRecord decode(ByteBuffer buf) {
        buf = buf.slice();
        byte magic = buf.get();
        if (magic != MAGIC) {
            throw new IllegalArgumentException("Invalid magic byte: " + magic);
        }
        int nodeId = buf.getInt();
        short bucketId = buf.getShort();
        long objectId = buf.getLong();
        int position = buf.getInt();
        int size = buf.getInt();
        return new RouterRecord(nodeId, bucketId, objectId, new Position(position, size));
    }

}
