package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CmdbCustomer {

    //TODO: Bitte eine Beschreibung einbauen, aus welcher Klasse der CMDB diese Daten kommen und was die Felder in der CMDB dann auch bedeutet
    
    int _id;
    @JsonProperty("Code")
    String Code;
    @JsonProperty("Description")
    String Description;
    String _RespTechnology_code;
    String _RespServiceDelivery_code;
    String _RespSalesperson_code;
    String _RespFinance_code;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String get_RespTechnology_code() {
        return _RespTechnology_code;
    }

    public void set_RespTechnology_code(String _RespTechnology_code) {
        this._RespTechnology_code = _RespTechnology_code;
    }

    public String get_RespServiceDelivery_code() {
        return _RespServiceDelivery_code;
    }

    public void set_RespServiceDelivery_code(String _RespServiceDelivery_code) {
        this._RespServiceDelivery_code = _RespServiceDelivery_code;
    }

    public String get_RespSalesperson_code() {
        return _RespSalesperson_code;
    }

    public void set_RespSalesperson_code(String _RespSalesperson_code) {
        this._RespSalesperson_code = _RespSalesperson_code;
    }

    public String get_RespFinance_code() {
        return _RespFinance_code;
    }

    public void set_RespFinance_code(String _RespFinance_code) {
        this._RespFinance_code = _RespFinance_code;
    }

}
