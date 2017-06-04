package es.colles.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import es.colles.dao.QueryMapsDAO;
import es.colles.model.QueryMaps;
import es.colles.model.ResponseMaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Component("queryHome")
public class QueryHome {

    private static final String EQUAL = "=";
    private static final String MAPS_API_URL = "https://maps.googleapis.com/maps/api";
    private static final String AND = "&";
    private static final String CONST_ORIGINS = "origins";
    private static final String CONST_DESTINATIONS = "destinations";
    private static final String CONST_MODE = "mode";
    private static final String CONST_LANGUAGE = "language";
    private static final String CONST_LANG_ES = "es-ES";
    private static final String CONST_KEY = "key";
    private static final String CONST_UNITS = "units";

    private static final Logger logger = LoggerFactory.getLogger(QueryHome.class);

    private QueryMaps queryMaps = new QueryMaps();
    private ResponseMaps responseMaps = new ResponseMaps();
    private List<QueryMaps> queries;
    private Date lastDate;

    @Autowired
    private QueryMapsDAO queryMapsDAO;


    public String getMessage() {
        logger.debug("Returning message from queryMaps home bean");
        return "Hello from Spring";
    }

    public QueryMaps getQueryMaps() {
        return queryMaps;
    }

    public void saveQuery() {
        getInfo(queryMaps);
        queryMapsDAO.save(queryMaps);
        lastDate = queryMaps.getTimeStamp();
        queryMaps = new QueryMaps();
        invalidateTasks();
    }

    private void invalidateTasks() {
        queries = null;
    }

    public List<QueryMaps> getQueries() {

        if (queries == null) {
            queries = queryMapsDAO.list();
        }
        return queries;

    }

    public void getInfo(QueryMaps queryMaps) {
        String key = (queryMaps.getKey().isEmpty()) ? "AIzaSyAQUI9Aqu09Nbeh-NTWU08sAqe068HwHmY" : queryMaps.getKey();
        String mode = "car";
        String origins = queryMaps.getOrigins();
        String destinations = queryMaps.getDestinations();
        String units = queryMaps.getUnits();
        String queryText = MAPS_API_URL + "/distancematrix/json?" + CONST_UNITS + EQUAL + units + AND + CONST_ORIGINS +
                EQUAL +
                origins +
                AND +
                CONST_DESTINATIONS
                + EQUAL + destinations + AND + CONST_MODE + EQUAL + mode + AND + CONST_LANGUAGE + EQUAL +
                CONST_LANG_ES + AND + CONST_KEY + "=";
        queryText = queryText.replace("|", "%7C");
        queryText.concat(key);
        logger.debug("Lanzo consulta-> " + queryText);

        try {

            Client client = Client.create();
            WebResource webResource = client.resource(queryText);
            InputStream response = webResource.accept("application/json")
                    .get(InputStream.class);
            ObjectMapper mapper = new ObjectMapper();

            // Convert JSON string to Object ResponseMaps
            responseMaps = mapper.readValue(response, ResponseMaps.class);

            if (responseMaps.getStatus() != "OK") {
                throw new RuntimeException("Failed : HTTP error code : "
                        + responseMaps.getStatus() + " - " + responseMaps.getError_message());
            }

            logger.debug("Output from Server .... \n");
            logger.debug(responseMaps.toString());

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
