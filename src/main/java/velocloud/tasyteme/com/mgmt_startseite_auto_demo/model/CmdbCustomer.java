package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CmdbCustomer {

    //Die Daten kommen aus der Customer Card in der CmdBuild
    //für jeden Kunden die verschiedenen Felder

    int _id;
    //_id = Allgemeine Customer Id
    @JsonProperty("Code")
    String Code;
    //Code = kürzel vom Kunden
    @JsonProperty("Description")
    String Description;
    //Description = Ausgeschriebener Kundenname
    @JsonProperty("NoSDWAN")
    boolean NoSDWAN;
    //True = Kein SDWAN Kunde und false = SDWAN Kunde
    @JsonProperty("AccessSSO")
    boolean AccessSSO;
    //true = es gibt ein VCO SSO Access oder false = es gibt keinen
    String _RespTechnology_code;
    //Deployer (Kürzel)
    String _RespServiceDelivery_code;
    //SD Manager (Kürzel)
    String _RespSalesperson_code;
    //Inside Sales Zuständiger (Kürzel)
    String _RespFinance_code;
    //Finance Zuständiger (Kürzel)

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

    public boolean isNoSDWAN() {
        return NoSDWAN;
    }

    public void setNoSDWAN(boolean noSDWAN) {
        NoSDWAN = noSDWAN;
    }

    public boolean isAccessSSO() {
        return AccessSSO;
    }

    public void setAccessSSO(boolean accessSSO) {
        AccessSSO = accessSSO;
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
