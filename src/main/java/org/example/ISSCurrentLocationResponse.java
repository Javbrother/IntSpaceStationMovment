package org.example;

public class ISSCurrentLocationResponse {
    public String message;
    public Position iss_position;
    public long timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Position getIss_position() {
        return iss_position;
    }

    public void setIss_position(Position iss_position) {
        this.iss_position = iss_position;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {

        this.timestamp = timestamp;
    }
}