package velocloud.tasyteme.com.mgmt_startseite_auto_demo.service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import velocloud.tasyteme.com.mgmt_startseite_auto_demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Service
public class Service {

        private static final Logger log = LoggerFactory.getLogger(Service.class);
        private final WebClient webClient;

        public Service(WebClient.Builder webClientBuilder) {
            //TODO: Magical Values bitte in die application.properties auslagern
            this.webClient = webClientBuilder.baseUrl("http://api.velocloud.ta-systeme.com:12000").build();
        }
        
        //TODO: In den unten stehenden Funktionen machst du immer das gleiche. Kann man das konsolidieren/refaktorien, damit es
        // nicht wie Copy&Paste Code aussieht?
        
        //Url für die Kundenkarten aus der CmdBuild
        public CmdbData<CmdbCustomer> getCmdbCustomerData() {
            //TODO: Magical Values bitte in die application.properties auslagern
            WebClient.ResponseSpec cmbdData1= this.webClient.get().uri("/api/v1/cmdb-api/classes/customers/cards").retrieve();
            Mono<CmdbData<CmdbCustomer>> cmdbData2 = cmbdData1.bodyToMono(new ParameterizedTypeReference<CmdbData<CmdbCustomer>>(){});
            log.info("Getting CMDB Customer Data");
             return cmdbData2.block();
        }
        //Url für die Cmk Kundendaten aus der CmdBuild
        public CmdbData<CmkServerData> getCmkCustomerData()  {
            //TODO: Magical Values bitte in die application.properties auslagern
            WebClient.ResponseSpec cmkData1= this.webClient.get().uri("/api/v1/cmdb-api/domains/server_companies/relations").retrieve();
            Mono<CmdbData<CmkServerData>> cmkData2 = cmkData1.bodyToMono(new ParameterizedTypeReference<CmdbData<CmkServerData>>(){});
            log.info("Getting CMK Customer Data");
            return cmkData2.block();
        }

        //Url für die Vco Kundendaten aus der CmdBuild
        public CmdbData<VcoData> getVcoCustomerData() {
            //TODO: Magical Values bitte in die application.properties auslagern
            WebClient.ResponseSpec vcoData1= this.webClient.get().uri("/api/v1/cmdb-api/classes/CustomerOnVco/cards/").retrieve();
            Mono<CmdbData<VcoData>> vcoData2 = vcoData1.bodyToMono(new ParameterizedTypeReference<CmdbData<VcoData>>(){});
            log.info("Getting VCO Customer Data");
            return vcoData2.block();
        }
        //Url für die CMK OOB Kundendaten aus der CmdBuild
        public CmdbData<MonitoringLink> getOOBCustomerData() {
            //TODO: Magical Values bitte in die application.properties auslagern
            WebClient.ResponseSpec oobData1= this.webClient.get().uri("/api/v1/cmdb-api/classes/oob_server/cards/").retrieve();
            Mono<CmdbData<MonitoringLink>> oobData2 = oobData1.bodyToMono(new ParameterizedTypeReference<CmdbData<MonitoringLink>>(){});
            log.info("Getting CMK OOB Customer Data");
            return oobData2.block();
        }
        //Url für die CMK Inband Kundendaten aus der CmdBuild
        public CmdbData<MonitoringLink> getInbandCustomerData() {
            //TODO: Magical Values bitte in die application.properties auslagern
            WebClient.ResponseSpec inbandData1= this.webClient.get().uri("/api/v1/cmdb-api/classes/inband_server/cards/").retrieve();
            Mono<CmdbData<MonitoringLink>> inbandData2 = inbandData1.bodyToMono(new ParameterizedTypeReference<CmdbData<MonitoringLink>>(){});
            log.info("Getting CMK Inband Customer Data");
            return inbandData2.block();
        }
}
