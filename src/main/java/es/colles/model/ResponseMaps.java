package es.colles.model;


import com.google.model.DistanceMatrixRow;

import javax.persistence.Id;

/**
 * Created by PORTATIL on 03/06/2017.
 */
public class ResponseMaps {

    @Id
    private String id;
    private String status;
    private String error_message;
    private String[] origin_addresses;
    private String[] destination_addresses;
    private DistanceMatrixRow[] rows;
    private String mode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String[] getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(String[] origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public String[] getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(String[] destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public DistanceMatrixRow[] getRows() {
        return rows;
    }

    public void setRows(DistanceMatrixRow[] rows) {
        this.rows = rows;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
