package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import java.io.Serializable;
import java.util.List;

public class CmdbData<T> implements Serializable {

    //Sobald ich die CMDBuild, das VCo oder das CMK abrufe sind dat, meta und success die Oberbegriffe(Ordner)
    //im Ordner Dta sind dann die Kunden und die weiteren Daten die ich abrufe

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
