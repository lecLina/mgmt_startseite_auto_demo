package velocloud.tasyteme.com.mgmt_startseite_auto_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.model.*;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.service.Datenberechnung;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.service.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class anbindungHtml {

    @Autowired
    Datenberechnung dr;
    @RequestMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("Data", dr.getrowData());

        return "index2";
    }

    /*
    @RequestMapping(value = "/test")
    public String TestCall(){
        CmdbData<CmdbCustomer> temptest = Service.getCmdbCustomerData();
        return temptest.toString();
    }
    @RequestMapping(value = "/test2")
    public String TestCall2(){
        CmdbData<CmkServerData> temptest = Service.getCmkCustomerData();
        return temptest.toString();
    }
    @RequestMapping(value = "/test3")
    public String TestCall3(){
        CmdbData<VcoData> temptest = Service.getVcoCustomerData();
        return temptest.toString();
    }
    @RequestMapping(value = "/test4")
    public String TestCall4(){
        CmdbData<MonitoringLink> temptest = Service.getOOBCustomerData();
        return temptest.toString();
    }
    @RequestMapping(value = "/test5")
    public String TestCall5(){
        CmdbData<MonitoringLink> temptest = Service.getInbandCustomerData();
        return temptest.toString();
    }
    */
    //Customer = Code & Description
    //Deployer = _RespTechnology_code
    //SD-Manager = _RespServiceDelivery_code
    //InsideSales = _RespSalesperson_code
    //Finance = _RespFinance_code
    //OOB & Inband = _sourceType und Link: _sourceDescription
    // +_sourceCode: z.B "cwsboco-openvpn" wird so angegeben
    //_destinationCode
    //_destinationId
    //_sourceId
    //_VCO_description
    //CustomerPrefix
    //Customer
    //VcoCustomerId

}
