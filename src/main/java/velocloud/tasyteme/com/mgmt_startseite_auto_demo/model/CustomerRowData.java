package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import javax.swing.*;
import java.util.List;


//TODO: Was ist die Aufgabe dieser Klasse, sie hat ja keinen direkten Bezug zur CMDB
public class CustomerRowData {


    //TODO: Was bedeuten die einzelnen Felder, wo und wie werden sie verwendet
    int ID;
    List<String> Customer;
    String Deployer;
    String SDManager;
    String InsideSales;
    String Finance;
    List<MonitoringLink> OOB;
    List<MonitoringLink> Inband;
    String Vco;
    String VcoDisplayName;
    String VcoSSOAccess;
    String Documentation;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<String> getCustomer() {
        return Customer;
    }

    public void setCustomer(List<String> customer) {Customer = customer;}

    public String getDeployer() {
        return Deployer;
    }

    public void setDeployer(String deployer) {
        Deployer = deployer;
    }

    public String getSDManager() {
        return SDManager;
    }

    public void setSDManager(String SDManager) {
        this.SDManager = SDManager;
    }

    public String getInsideSales() {
        return InsideSales;
    }

    public void setInsideSales(String insideSales) {
        InsideSales = insideSales;
    }

    public String getFinance() {
        return Finance;
    }

    public void setFinance(String finance) {
        Finance = finance;
    }

    public List<MonitoringLink> getOOB() {
        return OOB;
    }

    public void setOOB(List<MonitoringLink> OOB) {
        this.OOB = OOB;
    }

    public List<MonitoringLink> getInband() {
        return Inband;
    }

    public void setInband(List<MonitoringLink> inband) {
        Inband = inband;
    }

    public String getVco() {
        return Vco;
    }

    public void setVco(String vco) {
        Vco = vco;
    }

    public String getVcoDisplayName() {
        return VcoDisplayName;
    }

    public void setVcoDisplayName(String vcoDisplayName) {
        VcoDisplayName = vcoDisplayName;
    }

    public String getVcoSSOAccess() {
        return VcoSSOAccess;
    }

    public void setVcoSSOAccess(String vcoSSOAccess) {
        VcoSSOAccess = vcoSSOAccess;
    }

    public String getDocumentation() {
        return Documentation;
    }

    public void setDocumentation(String documentation) {
        Documentation = documentation;
    }
}
