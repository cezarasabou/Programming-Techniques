package model;

/**
 * simple client class which holds all the necessary attributes
 */
public class Client {
    private int clientId;
    private String clientName;
    private String clientAddress;
    private String clientBirthDate;
    private String clientPhoneNumber;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientBirthDate() {
        return clientBirthDate;
    }

    public void setClientBirthDate(String clientBirthDate) {
        this.clientBirthDate = clientBirthDate;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
