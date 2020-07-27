package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

public class OneMcpRecordSet {

    private String messageType;        // The type of the message. Two values are valid: {CALL|MSG}
    private long timestamp;                 // The timestamp of the message
    private long origin;                    // Mobile identifier of the origin mobile (MSISDN)
    private long destination;               // Mobile identifier of the destination mobile (MSISDN)
    private long duration;                  // Call duration. Only for CALL (message_type)
    private String statusCode;          // Status code of the call. Only for CALL (message_type). Two values are valid: {OK|KO}
    private String statusDescription;       // Status description of the call. Only for CALL (message_type)
    private String messageContent;          // Content of the message. Only for MSG (message_type)
    private String messageStatus;    // Status of the message. Two values are valid: {DELIVERED|SEEN}


    public OneMcpRecordSet(String messageType,
                           long timestamp,
                           long origin,
                           long destination,
                           long duration,
                           String statusCode,
                           String statusDescription,
                           String messageContent,
                           String messageStatus) {
        this.messageType = messageType;
        this.timestamp = timestamp;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.messageContent = messageContent;
        this.messageStatus = messageStatus;
    }


    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getOrigin() {
        return origin;
    }

    public void setOrigin(long origin) {
        this.origin = origin;
    }

    public long getDestination() {
        return destination;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }
}
