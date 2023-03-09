package velocloud.tasyteme.com.mgmt_startseite_auto_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Component
@EnableCaching
public class Datenberechnung {

    @Autowired
    Service Service;

    @Cacheable("rowData")
    public List<CustomerRowData> getrowData(){
        CmdbData<CmdbCustomer> customerData = Service.getCmdbCustomerData();
        CmdbData<CmkServerData> cmkData = Service.getCmkCustomerData();
        CmdbData<VcoData> vcoData = Service.getVcoCustomerData();
        CmdbData<MonitoringLink> oobData = Service.getOOBCustomerData();
        CmdbData<MonitoringLink> inbandData = Service.getInbandCustomerData();

        List<CustomerRowData> rowData = new LinkedList<CustomerRowData>();

        for( CmdbCustomer customer : customerData.getData() )
        {
            CustomerRowData tmp = new CustomerRowData();

            List<String> cusName = new ArrayList<String>();
            cusName.add(customer.getCode());
            cusName.add(customer.getDescription());
            tmp.setCustomer(cusName);

            tmp.setDeployer(customer.get_RespTechnology_code());
            tmp.setSDManager(customer.get_RespServiceDelivery_code());
            tmp.setInsideSales(customer.get_RespSalesperson_code());
            tmp.setFinance(customer.get_RespFinance_code());

            int cmdbCustomerId = customer.get_id();
            for (VcoData vcoRow : vcoData.getData())
            {
                if (vcoRow.getCustomer() == cmdbCustomerId)
                {
                    tmp.setVco("https://" + vcoRow.get_VCO_description() + "/ta-systeme/#!/msp/customer/"+ vcoRow.getVcoCustomerId() +"/");
                    tmp.setVcoDisplayName(vcoRow.get_VCO_description());
                    break;
                }
            }

            int tua_id = 0;
            List<MonitoringLink> oobList = new LinkedList<MonitoringLink>();
            for (CmkServerData relation2Monitor : cmkData.getData())
            {
                if (relation2Monitor.get_destinationId() == cmdbCustomerId)
                {
                    for (MonitoringLink oobRow : oobData.getData())
                    {
                        if (oobRow.get_id() == relation2Monitor.get_sourceId())
                        {
                            if (tua_id == 0 && oobRow.getTua_id() > 0)
                            {
                                tua_id = oobRow.getTua_id();
                            }
                            oobList.add(oobRow);
                        }
                    }
                }
            }
            tmp.setOOB(oobList);

            List<MonitoringLink> inbandList = new LinkedList<MonitoringLink>();
            for (CmkServerData relation2Monitor : cmkData.getData())
            {
                if (relation2Monitor.get_destinationId() == cmdbCustomerId)
                {
                    for (MonitoringLink inbandRow : inbandData.getData())
                    {
                        if (inbandRow.get_id() == relation2Monitor.get_sourceId())
                        {
                            if (tua_id == 0 && inbandRow.getTua_id() > 0)
                            {
                                tua_id = inbandRow.getTua_id();
                            }
                            inbandList.add(inbandRow);
                        }
                    }
                }
            }
            tmp.setInband(inbandList);

            tmp.setID(tua_id);

            tmp.setVcoSSOAccess(false);
            tmp.setDocumentation("https://www.velocloud.ta-systeme.com/wiki/index.php/Kategorie:" + customer.getCode());

            rowData.add(tmp);
        }
        rowData.sort((o1, o2) -> {
            return Integer.compare(o1.getID(),o2.getID());
        });

        return rowData;
    }

    @CacheEvict(value = "rowData", allEntries = true)
    @Scheduled(fixedRate = 60*60*1000)
    public void emptydatarowCache(){

    }
}
