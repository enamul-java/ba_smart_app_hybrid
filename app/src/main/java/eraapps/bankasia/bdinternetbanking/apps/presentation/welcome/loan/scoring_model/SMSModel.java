package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

public class SMSModel {
    private String sender;
    private String msg;
    private String formattedDate;
    private String phoneNumber;

    // Constructor
    public SMSModel(String sender, String msg, String formattedDate, String phoneNumber)
    {
        this.sender = sender;
        this.msg = msg;
        this.formattedDate = formattedDate;
        this.phoneNumber = phoneNumber;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
