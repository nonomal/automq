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

package kafka.automq.zerozone;

import java.util.Map;
import org.apache.kafka.common.message.ProduceRequestData;

import java.util.Objects;
import org.apache.kafka.common.zerozone.Position;

public class ZoneRouterProduceRequest {
    private final short apiVersion;
    private final short flag;
    private final ProduceRequestData data;
    private final Map<Integer, Position> recordsPositionMap;

    public ZoneRouterProduceRequest(short apiVersion, short flag, ProduceRequestData data) {
        this.apiVersion = apiVersion;
        this.data = data;
        this.flag = flag;
        this.recordsPositionMap = null;
    }

    public ZoneRouterProduceRequest(short apiVersion, short flag, ProduceRequestData data, Map<Integer, Position> recordsPositionMap) {
        this.apiVersion = apiVersion;
        this.data = data;
        this.flag = flag;
        this.recordsPositionMap = recordsPositionMap;
    }

    public short apiVersion() {
        return apiVersion;
    }

    public short flag() {
        return flag;
    }

    public ProduceRequestData data() {
        return data;
    }

    public Map<Integer, Position> recordsPositionMap() {
        return recordsPositionMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ZoneRouterProduceRequest request = (ZoneRouterProduceRequest) o;
        return apiVersion == request.apiVersion && Objects.equals(data, request.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiVersion, data);
    }

    public static class Flag {
        private static final short INTERNAL_TOPICS_ALLOWED = 1;

        private short flag;

        public Flag(short flag) {
            this.flag = flag;
        }

        public Flag() {
            this((short) 0);
        }

        public short value() {
            return flag;
        }

        public Flag internalTopicsAllowed(boolean internalTopicsAllowed) {
            if (internalTopicsAllowed) {
                flag = (short) (flag | INTERNAL_TOPICS_ALLOWED);
            } else {
                flag = (short) (flag & ~INTERNAL_TOPICS_ALLOWED);
            }
            return this;
        }

        public boolean internalTopicsAllowed() {
            return (flag & INTERNAL_TOPICS_ALLOWED) != 0;
        }

    }
}
