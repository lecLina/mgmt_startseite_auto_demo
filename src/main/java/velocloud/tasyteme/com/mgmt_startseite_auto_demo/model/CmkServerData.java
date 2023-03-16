package velocloud.tasyteme.com.mgmt_startseite_auto_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CmkServerData {

    //TODO: Bitte eine Beschreibung einbauen, aus welcher Klasse der CMDB diese Daten kommen und was die Felder in der CMDB dann auch bedeutet

    @JsonProperty("Description")
    String Description;
    int _id;
    String _sourceType;
    String _sourceDescription;
    String _sourceCode;
    String _destinationCode;
    int _destinationId;
    int _sourceId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_sourceType() {
        return _sourceType;
    }

    public void set_sourceType(String _sourceType) {
        this._sourceType = _sourceType;
    }

    public String get_sourceDescription() {
        return _sourceDescription;
    }

    public void set_sourceDescription(String _sourceDescription) {
        this._sourceDescription = _sourceDescription;
    }

    public String get_sourceCode() {
        return _sourceCode;
    }

    public void set_sourceCode(String _sourceCode) {
        this._sourceCode = _sourceCode;
    }

    public String get_destinationCode() {
        return _destinationCode;
    }

    public void set_destinationCode(String _destinationCode) {
        this._destinationCode = _destinationCode;
    }

    public int get_destinationId() {
        return _destinationId;
    }

    public void set_destinationId(int _destinationId) {
        this._destinationId = _destinationId;
    }

    public int get_sourceId() {
        return _sourceId;
    }

    public void set_sourceId(int _sourceId) {
        this._sourceId = _sourceId;
    }
}
