package velocloud.tasyteme.com.mgmt_startseite_auto_demo.service;

import io.netty.handler.ssl.DelegatingSslContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${vcoCustomer.Url}")
    private String vcoCustomerDescription;
    @Value("${https}")
    private String https;
    @Value("${image.yes}")
    private String yes_image;
    @Value("${image.no}")
    private String no_image;
    @Value("${documentation.Wiki}")
    private String WikiLink;
    @Autowired
    Service Service;

    private static final Logger log = LoggerFactory.getLogger(Service.class);

    //getrowData holt sich die Daten von allen Reihen für die Tabelle(index2.html)
    @Cacheable("rowData")
    public List<CustomerRowData> getrowData() {
        CmdbData<CmdbCustomer> customerData = Service.getCmdbCustomerData();
        if (customerData.isSuccess() == false)
            log.error("Connection to CmdBuild Customer Data failed");
        CmdbData<CmkServerData> cmkData = Service.getCmkCustomerData();
        if (cmkData.isSuccess() == false)
            log.error("Connection to CMK Server Data failed");
        CmdbData<VcoData> vcoData = Service.getVcoCustomerData();
        if (vcoData.isSuccess() == false)
            log.error("Connection to Vco Data failed");
        CmdbData<MonitoringLink> oobData = Service.getOOBCustomerData();
        if (oobData.isSuccess() == false)
            log.error("Connection to CMK OOB Data failed");
        CmdbData<MonitoringLink> inbandData = Service.getInbandCustomerData();
        if (inbandData.isSuccess() == false)
            log.error("Connection to CMK Inband Data failed");

        //TODO: Fehlerbehandlung fehlt, was passiert, wenn isSuccess==false ist, oder wenn es eine exception gibt oder wenn keine Daten zurueckkommen
        // Dann darf der Rest hier drunter nicht ausgefuerht werden, aber das Caching darf auch keinen fehlerhaften Inhalt greifen


        //TODO: Raphaels API liefert noch eine andere Art von Fehler - solltes du mal provozieren (Frag Raphael) und dann explizit auch als Fehler 
        // abfangen und behandeln

        List<CustomerRowData> rowData = new LinkedList<CustomerRowData>();

        for (CmdbCustomer customer : customerData.getData()) {
            if (customer.isNoSDWAN() == true) {
                continue;
            }

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
            for (VcoData vcoRow : vcoData.getData()) {
                if (vcoRow.getCustomer() == cmdbCustomerId) {
                    tmp.setVco(https + vcoRow.get_VCO_description() + vcoCustomerDescription + vcoRow.getVcoCustomerId() + "/");
                    tmp.setVcoDisplayName(vcoRow.get_VCO_description());
                    break;
                }
            }

            int tua_id = 0;
            List<MonitoringLink> oobList = new LinkedList<MonitoringLink>();
            for (CmkServerData relation2Monitor : cmkData.getData()) {
                if (relation2Monitor.get_destinationId() == cmdbCustomerId) {
                    for (MonitoringLink oobRow : oobData.getData()) {
                        if (oobRow.get_id() == relation2Monitor.get_sourceId()) {
                            if (tua_id == 0 && oobRow.getTua_id() > 0) {
                                tua_id = oobRow.getTua_id();
                            }
                            oobList.add(oobRow);
                        }
                    }
                }
            }
            tmp.setOOB(oobList);

            List<MonitoringLink> inbandList = new LinkedList<MonitoringLink>();
            for (CmkServerData relation2Monitor : cmkData.getData()) {
                if (relation2Monitor.get_destinationId() == cmdbCustomerId) {
                    for (MonitoringLink inbandRow : inbandData.getData()) {
                        if (inbandRow.get_id() == relation2Monitor.get_sourceId()) {
                            if (tua_id == 0 && inbandRow.getTua_id() > 0) {
                                tua_id = inbandRow.getTua_id();
                            }
                            inbandList.add(inbandRow);
                        }
                    }
                }
            }
            tmp.setInband(inbandList);

            tmp.setID(tua_id);


            if (customer.isAccessSSO() == true) {
                tmp.setVcoSSOAccess(yes_image);
            } else {
                tmp.setVcoSSOAccess(no_image);
            }

            tmp.setDocumentation(WikiLink + customer.getCode());

            if (cusName.size() == 0) {
                tmp.setCustomer(List.of("-"));
            }
            if (tmp.getDeployer() == null || tmp.getDeployer().length() == 0) {
                tmp.setDeployer("-");
            }
            if (tmp.getSDManager() == null || tmp.getSDManager().length() == 0) {
                tmp.setSDManager("-");
            }
            if (tmp.getInsideSales() == null || tmp.getInsideSales().length() == 0) {
                tmp.setInsideSales("-");
            }
            if (tmp.getFinance() == null || tmp.getFinance().length() == 0) {
                tmp.setFinance("-");
            }
            if (tmp.getVcoDisplayName() == null || tmp.getVcoDisplayName().length() == 0) {
                tmp.setVcoDisplayName("-");
            }

            rowData.add(tmp);

        }
        rowData.sort((o1, o2) -> {
            return (o1.getCustomer().get(0).compareTo(o2.getCustomer().get(0)));
        });

        return rowData;
    }

    //Das ist dewr Cache die rowData holt sich durch die Methode bewirkt das die Daten nur stündlich abgeholt werden dadurch dauert das laden nur noch ein paar ms
    @CacheEvict(value = "rowData", allEntries = true)
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void emptydatarowCache() {

    }
}
