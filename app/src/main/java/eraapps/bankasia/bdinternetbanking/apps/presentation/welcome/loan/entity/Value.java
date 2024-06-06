package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity;
public class Value {
    int _value_id;
    String _value_name;
    String _value_score;
    int _value_variable_id;
    String _flag;
    String _min_value;
    String _max_value;

    public Value(){   }
    public Value(int id, String value_name, String value_score, String flag, String min_value,String max_value){
        this._value_id = id;
        this._value_name = value_name;
        this._value_score = value_score;
        this._flag = flag;
        this._min_value = min_value;
        this._max_value = max_value;
    }
    public Value(String value_name, String value_score, String flag, String min_value,String max_value){
        this._value_name = value_name;
        this._value_score = value_score;
        this._flag = flag;
        this._min_value = min_value;
        this._max_value = max_value;
    }

    public String get_flag() {
        return _flag;
    }

    public void set_flag(String _flag) {
        this._flag = _flag;
    }

    public String get_min_value() {
        return _min_value;
    }

    public void set_min_value(String _min_value) {
        this._min_value = _min_value;
    }

    public String get_max_value() {
        return _max_value;
    }

    public void set_max_value(String _max_value) {
        this._max_value = _max_value;
    }

    public int get_value_id() {
        return _value_id;
    }

    public void set_value_id(int _value_id) {
        this._value_id = _value_id;
    }

    public String get_value_name() {
        return _value_name;
    }

    public void set_value_name(String _value_name) {
        this._value_name = _value_name;
    }

    public String get_value_score() {
        return _value_score;
    }

    public int get_value_variable_id() {
        return _value_variable_id;
    }

    public void set_value_variable_id(int _value_variable_id) {
        this._value_variable_id = _value_variable_id;
    }

    public void set_value_score(String _value_score) {
        this._value_score = _value_score;
    }
}
