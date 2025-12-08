package ec.edu.espe.videocalls.model;

/**
 *
 * @author Daniel
 */
public class VideoCall {
    private int callId;
    private int customerId;
    private int eventId;
    private String videoCallDate;
    private String medium;

    
    public VideoCall(int callId, int customerId, int eventId, String videoCallDate, String medium) {
        this.callId = callId;
        this.customerId = customerId;
        this.eventId = eventId;
        this.videoCallDate = videoCallDate;
        this.medium = medium;
    }
    

    /**
     * @return the callId
     */
    public int getCallId() {
        return callId;
    }

    /**
     * @param callId the callId to set
     */
    public void setCallId(int callId) {
        this.callId = callId;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the eventId
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the videoCallDate
     */
    public String getVideoCallDate() {
        return videoCallDate;
    }

    /**
     * @param videoCallDate the videoCallDate to set
     */
    public void setVideoCallDate(String videoCallDate) {
        this.videoCallDate = videoCallDate;
    }

    /**
     * @return the medium
     */
    public String getMedium() {
        return medium;
    }

    /**
     * @param medium the medium to set
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }
    
}