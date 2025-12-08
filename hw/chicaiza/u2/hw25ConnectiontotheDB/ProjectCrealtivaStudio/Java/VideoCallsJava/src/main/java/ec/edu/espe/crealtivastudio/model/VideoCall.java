package ec.edu.espe.crealtivastudio.model;

/**
 *
 * @author Daniel
 */
public class VideoCall {
    private int callId;
    private int customerId;
    private int eventId;
    private String customerName;
    private String videoCallDate;
    private String videoCallHour;
    private String medium;       // Zoom, Meet, Teams
    private String note;
    
    @Override
    public String toString() {
        return  """
                Call ID: """ + callId + "\n"
                + "Customer ID: " + customerId + "\n"
                + "Event ID: " + eventId + "\n"
                + "Customer Name: " + customerName + "\n"
                + "Date: " + videoCallDate + "\n"
                + "Hour: " + videoCallHour + "\n"
                + "Medium: " + medium + "\n"
                + "Note: " + note + "\n";
    }

    public VideoCall() {
    }

    public VideoCall(int callId, int customerId, int eventId, String customerName,
                     String videoCallDate, String videoCallHour, String medium, String note) {
        this.callId = callId;
        this.customerId = customerId;
        this.eventId = eventId;
        this.customerName = customerName;
        this.videoCallDate = videoCallDate;
        this.videoCallHour = videoCallHour;
        this.medium = medium;
        this.note = note;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVideoCallDate() {
        return videoCallDate;
    }

    public void setVideoCallDate(String videoCallDate) {
        this.videoCallDate = videoCallDate;
    }

    public String getVideoCallHour() {
        return videoCallHour;
    }

    public void setVideoCallHour(String videoCallHour) {
        this.videoCallHour = videoCallHour;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
