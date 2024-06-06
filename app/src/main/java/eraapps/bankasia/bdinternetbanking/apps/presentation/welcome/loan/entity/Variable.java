package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity;
public class Variable {
    int _id;
    int _component_id;
    String _name;

    public Variable(){   }
    public Variable(int id, int component_id, String name){
        this._id = id;
        this._component_id = component_id;
        this._name = name;
    }
    public Variable(int component_id, String name){
        this._component_id = component_id;
        this._name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_component_id() {
        return _component_id;
    }

    public void set_component_id(int _component_id) {
        this._component_id = _component_id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
