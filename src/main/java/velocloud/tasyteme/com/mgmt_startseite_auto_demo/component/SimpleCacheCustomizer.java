package velocloud.tasyteme.com.mgmt_startseite_auto_demo.component;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleCacheCustomizer
        implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        List<String> tmp = new ArrayList<>();
            
        //TODO: Kommentar fehlt, warum rowData als CacheName gesetzt wird
        //Mehre Daten werden gespeichert in einem.
        tmp.add("rowData");
        cacheManager.setCacheNames(tmp);
    }
}
