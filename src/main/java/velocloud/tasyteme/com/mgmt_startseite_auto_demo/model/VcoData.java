package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VcoData {

    int _id;
    @JsonProperty("Customer")
    int Customer;
    @JsonProperty("VcoCustomerId")
    int VcoCustomerId;
    @JsonProperty("CustomerPrefix")
    String CustomerPrefix;
    String _VCO_description;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCustomer() {
        return Customer;
    }

    public void setCustomer(int customer) {
        Customer = customer;
    }

    public int getVcoCustomerId() {
        return VcoCustomerId;
    }

    public void setVcoCustomerId(int vcoCustomerId) {
        VcoCustomerId = vcoCustomerId;
    }

    public String getCustomerPrefix() {
        return CustomerPrefix;
    }

    public void setCustomerPrefix(String customerPrefix) {
        CustomerPrefix = customerPrefix;
    }

    public String get_VCO_description() {
        return _VCO_description;
    }

    public void set_VCO_description(String _VCO_description) {
        this._VCO_description = _VCO_description;
    }
}
