package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoringLink {

    int _id;
    int tua_id;
    String _OOB_Server_Type_code;
    String ipv4;
    @JsonProperty("Code")
    String Code;
    String _state_code;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getTua_id() {
        return tua_id;
    }

    public void setTua_id(int tua_id) {
        this.tua_id = tua_id;
    }

    public String get_OOB_Server_Type_code() {
        return _OOB_Server_Type_code;
    }

    public void set_OOB_Server_Type_code(String _OOB_Server_Type_code) {
        this._OOB_Server_Type_code = _OOB_Server_Type_code;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String get_state_code() {
        return _state_code;
    }

    public void set_state_code(String _state_code) {
        this._state_code = _state_code;
    }
}
