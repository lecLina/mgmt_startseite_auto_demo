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
            this.webClient = webClientBuilder.baseUrl("http://api.velocloud.ta-systeme.com:12000").build();
        }

        public CmdbData<CmdbCustomer> getCmdbCustomerData() {
            WebClient.ResponseSpec testcmdbEins= this.webClient.get().uri("/api/v1/cmdb-api/classes/customers/cards").retrieve();
            Mono<CmdbData<CmdbCustomer>> testcmdbzwei = testcmdbEins.bodyToMono(new ParameterizedTypeReference<CmdbData<CmdbCustomer>>(){});
            log.info("Getting CMDB Customer Data");
             return testcmdbzwei.block();
        }
        public CmdbData<CmkServerData> getCmkCustomerData()  {
            WebClient.ResponseSpec testcmkEins= this.webClient.get().uri("/api/v1/cmdb-api/domains/server_companies/relations").retrieve();
            Mono<CmdbData<CmkServerData>> testcmkzwei = testcmkEins.bodyToMono(new ParameterizedTypeReference<CmdbData<CmkServerData>>(){});
            return testcmkzwei.block();
        }
        public CmdbData<VcoData> getVcoCustomerData() {
            WebClient.ResponseSpec testvcoEins= this.webClient.get().uri("/api/v1/cmdb-api/classes/CustomerOnVco/cards/").retrieve();
            Mono<CmdbData<VcoData>> testvcozwei = testvcoEins.bodyToMono(new ParameterizedTypeReference<CmdbData<VcoData>>(){});
            return testvcozwei.block();
        }
        public CmdbData<MonitoringLink> getOOBCustomerData() {
            WebClient.ResponseSpec testoobEins= this.webClient.get().uri("/api/v1/cmdb-api/classes/oob_server/cards/").retrieve();
            Mono<CmdbData<MonitoringLink>> testoobzwei = testoobEins.bodyToMono(new ParameterizedTypeReference<CmdbData<MonitoringLink>>(){});
            return testoobzwei.block();
        }
        public CmdbData<MonitoringLink> getInbandCustomerData() {
            WebClient.ResponseSpec testinbandEins= this.webClient.get().uri("/api/v1/cmdb-api/classes/inband_server/cards/").retrieve();
            Mono<CmdbData<MonitoringLink>> testinbandzwei = testinbandEins.bodyToMono(new ParameterizedTypeReference<CmdbData<MonitoringLink>>(){});
            return testinbandzwei.block();
        }
}
