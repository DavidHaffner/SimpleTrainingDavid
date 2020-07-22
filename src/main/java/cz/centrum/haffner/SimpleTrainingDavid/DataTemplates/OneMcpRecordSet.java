package cz.centrum.haffner.SimpleTrainingDavid.DataTemplates;

public class OneMcpRecordSet {

    private MessageType messageType;        // The type of the message. Two values are valid: {CALL|MSG}
    private long timestamp;                 // The timestamp of the message
    private long origin;                    // Mobile identifier of the origin mobile (MSISDN)
    private long destination;               // Mobile identifier of the destination mobile (MSISDN)
    private long duration;                  // Call duration. Only for CALL (message_type)
    private StatusCode statusCode;          // Status code of the call. Only for CALL (message_type). Two values are valid: {OK|KO}
    private String statusDescription;       // Status description of the call. Only for CALL (message_type)
    private String messageContent;          // Content of the message. Only for MSG (message_type)
    private MessageStatus messageStatus;    // Status of the message. Two values are valid: {DELIVERED|SEEN}


    public OneMcpRecordSet(MessageType messageType,
                           long timestamp,
                           long origin,
                           long destination,
                           long duration,
                           StatusCode statusCode,
                           String statusDescription,
                           String messageContent,
                           MessageStatus messageStatus) {
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


}
