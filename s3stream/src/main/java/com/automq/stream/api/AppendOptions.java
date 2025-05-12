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

package com.automq.stream.api;

public class AppendOptions {
    public static final AppendOptions DEFAULT = new AppendOptions();

    private RemoteRecordsLocation remoteRecordsLocation;

    public static Builder builder() {
        return new Builder();
    }

    public RemoteRecordsLocation remoteRecordsLocation() {
        return remoteRecordsLocation;
    }

    public static class Builder {
        private final AppendOptions options = new AppendOptions();

        public Builder remoteRecordsLocation(RemoteRecordsLocation remoteRecordsLocation) {
            options.remoteRecordsLocation = remoteRecordsLocation;
            return this;
        }

        public AppendOptions build() {
            return options;
        }
    }

}
