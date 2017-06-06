/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.google.model;

/**
 * A single result corresponding to a origin/destination pair in a Distance Matrix response.
 * <p>
 * <p>Be sure to check the status for each element, as a matrix response can have a mix of
 * successful and failed elements depending on the connectivity of the origin and destination.
 */
public class DistanceMatrixElement {

    /**
     * {@code status} indicates the status of the request for this origin/destination pair.
     * <p>
     * Will be one of {@link DistanceMatrixElementStatus}.
     */
    private DistanceMatrixElementStatus status;
    /**
     * {@code duration} indicates the total duration of this leg
     */
    private Duration duration;
    /**
     * {@code durationInTraffic} indicates the length of time to travel this route,
     * based on current and historical traffic conditions.  The duration in traffic
     * will only be returned if all of the following are true:
     * <ol>
     * <li>The request includes a departureTime parameter.</li>
     * <li>The request includes a valid API key, or a valid Google Maps APIs Premium Plan client ID and signature.</li>
     * <li>Traffic conditions are available for the requested route.</li>
     * <li>The mode parameter is set to driving.</li>
     * </ol>
     */
    private Duration durationInTraffic;
    /**
     * {@code distance} indicates the total distance covered by this leg.
     */
    private Distance distance;

    public DistanceMatrixElementStatus getStatus() {
        return status;
    }

    public void setStatus(DistanceMatrixElementStatus status) {
        this.status = status;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getDurationInTraffic() {
        return durationInTraffic;
    }

    public void setDurationInTraffic(Duration durationInTraffic) {
        this.durationInTraffic = durationInTraffic;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
//    /**
//     * {@code fare} indicates the contains information about the fare (that is, the ticket costs) on
//     * this route.
//     */
//    public Fare fare;

    public enum DistanceMatrixElementStatus {
        /**
         * {@code OK} indicates the response contains a valid result.
         */
        OK,

        /**
         * {@code NOT_FOUND} indicates that the origin and/or destination of this pairing could not be
         * geocoded.
         */
        NOT_FOUND,

        /**
         * {@code ZERO_RESULTS} indicates no route could be found between the origin and destination.
         */
        ZERO_RESULTS
    }

    public class Distance {

        /**
         * This is the human friendly distance. This is rounded and in an appropriate unit for the
         * request. The units can be overriden with a request parameter.
         */
        private String text;
        /**
         * This is the numeric distance, always in meters. This is intended to be used only in algorithmic
         * situations, e.g. sorting results by some user specified metric.
         */
        private long value;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public class Duration {

        /**
         * This is the numeric duration, in seconds. This is intended to be used only in algorithmic
         * situations, e.g. sorting results by some user specified metric.
         */
        private long value;
        /**
         * This is the human friendly duration. Use this for display purposes.
         */
        private String text;

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
