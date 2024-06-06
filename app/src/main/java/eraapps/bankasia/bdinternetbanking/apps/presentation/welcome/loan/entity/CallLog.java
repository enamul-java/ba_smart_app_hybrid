package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity;
public class CallLog {

    private String Number;
    private String Name;
    private String Duration;
    private String Type;

    public CallLog(String Number,String Name,String Duration,String Type) {
        this.Number = Number;
        this.Name = Name;
        this.Duration = Duration;
        this.Type = Type;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}