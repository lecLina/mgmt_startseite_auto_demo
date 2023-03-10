package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import java.io.Serializable;
import java.util.List;

public class CmdbData<T> implements Serializable {

    
    //TODO: Bitte eine Beschreibung einbauen, aus welcher Klasse der CMDB diese Daten kommen und was die Felder in der CMDB dann auch bedeutet
    
    List<T> data;
    CmdbMeta meta;
    boolean success;
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public CmdbMeta getMeta() {
        return meta;
    }

    public void setMeta(CmdbMeta meta) {
        this.meta = meta;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
