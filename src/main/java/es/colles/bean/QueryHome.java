package es.colles.bean;

import es.colles.core.MapsConfig;
import es.colles.manager.QueryManager;
import es.colles.model.QueryMaps;
import es.colles.model.ResponseMaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("queryHome")
public class QueryHome {

    private static final Logger logger = LoggerFactory.getLogger(QueryHome.class);

    //For the view
    public String selectedQuery;

    @Autowired
    private QueryManager queryManager;

    @Autowired
    private MapsConfig mapsConfig;
    private QueryMaps queryMaps = new QueryMaps();
    private List<ResponseMaps> responseMaps = new ArrayList<>();
    private List<QueryMaps> queries;
    private Date lastDate;
    private List<String> modes;
    private List<String> units;

    public String getSelectedQuery() {
        return selectedQuery;
    }

    public void setSelectedQuery(String selectedQuery) {
        this.selectedQuery = selectedQuery;
    }

    public String getMessage() {
        logger.debug("Returning message from queryMaps home bean");
        return "Carlos rulz!!";
    }

    public QueryMaps getQueryMaps() {
        return queryMaps;
    }

    public void getInfoAndSaveQuery() {
        responseMaps = queryManager.getInfoAndSaveQuery(queryMaps);

        lastDate = queryMaps.getTimeStamp();
        queryMaps = new QueryMaps();
        invalidateTasks();
    }

    private void invalidateTasks() {
        queries = null;
    }

    public List<QueryMaps> getQueries() {

        if (queries == null) {
            queries = queryManager.list();
        }
        return queries;

    }

    public void onClickQuery() {
        responseMaps.clear();
        responseMaps.add(queryManager.executeGet(selectedQuery));
        logger.debug("yeaah");
    }


    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public List<ResponseMaps> getResponseMaps() {
        return responseMaps;
    }

    public void setResponseMaps(List<ResponseMaps> responseMaps) {
        this.responseMaps = responseMaps;
    }


    public List<String> getModes() {
        return mapsConfig.getModes();
    }

    public void setModes() {
        this.modes = mapsConfig.getModes();
    }

    public List<String> getUnits() {
        return mapsConfig.getUnits();
    }

    public void setUnits() {
        this.units = mapsConfig.getUnits();
    }
}
