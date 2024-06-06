package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

public class SlabModel {

    private String component_name;
    private String variable_name;
    private String slab_value1;
    private String slab_value2;
    private String slab_value3;
    private String slab_value4;
    private String slab_value5;
    private String score1;
    private String score2;
    private String score3;
    private String score4;
    private String score5;
    private int component_image;

    // Constructor
    public SlabModel(String component_name, String variable_name, String slab_value1,
                     String slab_value2, String slab_value3, String slab_value4, String slab_value5,String score1, String score2,
                     String score3,String score4, String score5, int component_image)
    {
        this.component_name = component_name;
        this.variable_name = variable_name;
        this.component_image = component_image;
        this.slab_value1 = slab_value1;
        this.slab_value2 = slab_value2;
        this.slab_value3 = slab_value3;
        this.slab_value2 = slab_value4;
        this.slab_value3 = slab_value5;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
    }

    public String getSlab_value5() {
        return slab_value5;
    }

    public void setSlab_value5(String slab_value5) {
        this.slab_value5 = slab_value5;
    }

    public String getSlab_value4() {
        return slab_value4;
    }

    public void setSlab_value4(String slab_value4) {
        this.slab_value4 = slab_value4;
    }

    public String getScore5() {
        return score5;
    }

    public void setScore5(String score5) {
        this.score5 = score5;
    }

    public String getScore4() {
        return score4;
    }

    public void setScore4(String score4) {
        this.score4 = score4;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getScore3() {
        return score3;
    }

    public void setScore3(String score3) {
        this.score3 = score3;
    }

    public String getSlab_value1() {
        return slab_value1;
    }

    public void setSlab_value1(String slab_value1) {
        this.slab_value1 = slab_value1;
    }

    public String getSlab_value2() {
        return slab_value2;
    }

    public void setSlab_value2(String slab_value2) {
        this.slab_value2 = slab_value2;
    }

    public String getSlab_value3() {
        return slab_value3;
    }

    public void setSlab_value3(String slab_value3) {
        this.slab_value3 = slab_value3;
    }

    public String getComponent_name() {
        return component_name;
    }

    public void setComponent_name(String component_name) {
        this.component_name = component_name;
    }

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public int getComponent_image() {
        return component_image;
    }

    public void setComponent_image(int component_image) {
        this.component_image = component_image;
    }
}
