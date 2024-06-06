package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

public class ResultItemModel {
    public String date;
    public String result;
    public String applied_for;
    public String applied_amount;

    public ResultItemModel(String date, String result, String applied_for, String applied_amount) {
        this.date = date;
        this.result = result;
        this.applied_for = applied_for;
        this.applied_amount = applied_amount;
    }

    public String getApplied_for() {
        return applied_for;
    }

    public String getApplied_amount() {
        return applied_amount;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }
}
