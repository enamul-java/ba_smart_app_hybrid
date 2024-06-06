package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity;
public class Component {

    int _id;
    int _data_source_id;
    String _name;
    int _weight;
    int _max_point;

    public Component(){   }
    public Component(int id, int data_source_id, String name, int weight, int max_point){
        this._id = id;
        this._data_source_id = data_source_id;
        this._name = name;
        this._weight = weight;
        this._max_point = max_point;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_data_source_id() {
        return _data_source_id;
    }

    public void set_data_source_id(int _data_source_id) {
        this._data_source_id = _data_source_id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_weight() {
        return _weight;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }

    public int get_max_point() {
        return _max_point;
    }

    public void set_max_point(int _max_point) {
        this._max_point = _max_point;
    }
}
