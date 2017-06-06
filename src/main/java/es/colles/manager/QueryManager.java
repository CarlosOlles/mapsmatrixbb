package es.colles.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.model.DistanceMatrixElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import es.colles.bean.QueryHome;
import es.colles.core.MapsConfig;
import es.colles.dao.QueryMapsDAO;
import es.colles.model.QueryMaps;
import es.colles.model.ResponseMaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by PORTATIL on 04/06/2017.
 */
@Component
public class QueryManager {

    private static final String KEY_COLLES = "AIzaSyAQUI9Aqu09Nbeh-NTWU08sAqe068HwHmY";
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
    private static final String CONST_IMPERIAL = "imperial";

    private static final Logger logger = LoggerFactory.getLogger(QueryHome.class);


    @Autowired
    private QueryMapsDAO queryMapsDAO;

    @Autowired
    private MapsConfig mapsConfig;

    public List<ResponseMaps> getInfoAndSaveQuery(QueryMaps queryMaps) {


        List<ResponseMaps> responseList = new ArrayList<>();
        String queryText;
        if (Objects.equals(queryMaps.getMode(), "")) {
            for (String mode : mapsConfig.getModes()) {
                QueryMaps queryMapsFor = new QueryMaps();
                queryMapsFor.setMode(mode);
                queryMapsFor.setDescription(queryMaps.getDescription());
                queryMapsFor.setLanguage(queryMaps.getLanguage());
                queryMapsFor.setUnits(queryMaps.getUnits());
                queryMapsFor.setDestinations(queryMaps.getDestinations());
                queryMapsFor.setOrigins(queryMaps.getOrigins());
                queryMapsFor.setKey(queryMaps.getKey());


                queryText = buildQuery(queryMapsFor);
                queryMapsFor.setQuery(queryText);
                logger.debug("Lanzo consulta-> " + queryText);

                queryMapsFor.setMode(mode);
                saveQuery(queryMapsFor);
                ResponseMaps responseMaps = executeGet(queryText);
                responseMaps.setMode(queryMapsFor.getMode());
                responseList.add(responseMaps);
            }
        } else {
            queryText = buildQuery(queryMaps);
            queryMaps.setQuery(queryText);
            logger.debug("Lanzo consulta-> " + queryText);

            ResponseMaps responseMaps = executeGet(queryText);
            responseMaps.setMode(queryMaps.getMode());
            responseList.add(responseMaps);
        }

        return responseList;
    }

    public ResponseMaps executeGet(String queryText) {
        ResponseMaps responseMaps = null;
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(queryText);
            InputStream response = webResource.accept("application/json")
                    .get(InputStream.class);
            ObjectMapper mapper = new ObjectMapper();

            // Convert JSON string to Object ResponseMaps
            responseMaps = mapper.readValue(response, ResponseMaps.class);

            if (Objects.equals(responseMaps.getStatus(), DistanceMatrixElement.DistanceMatrixElementStatus.OK.toString())) {
                logger.debug("Output from Server .... \n");
                logger.debug(responseMaps.toString());
            } else {
                throw new RuntimeException("Failed : HTTP error code : "
                        + responseMaps.getStatus() + " - " + responseMaps.getError_message());
            }


        } catch (Exception e) {
            logger.error("Ha ocurrido una excepción ->" + e.getMessage());
        }
        return responseMaps;
    }

    private String buildQuery(QueryMaps queryMaps) {
        String key = !queryMaps.getKey().isEmpty() ? queryMaps.getKey() : KEY_COLLES;
        String mode = queryMaps.getMode();
        String origins = queryMaps.getOrigins().replace(" ", "+");
        String destinations = queryMaps.getDestinations().replace(" ", "+");
        String units = queryMaps.getUnits() != null ? queryMaps.getUnits() : CONST_IMPERIAL;
        String lang = queryMaps.getLanguage() != null ? queryMaps.getLanguage() : CONST_LANG_ES;
        String queryText = MAPS_API_URL + "/distancematrix/json?" + CONST_UNITS + EQUAL + units + AND + CONST_ORIGINS +
                EQUAL +
                origins +
                AND +
                CONST_DESTINATIONS
                + EQUAL + destinations + AND + CONST_MODE + EQUAL + mode + AND + CONST_LANGUAGE + EQUAL +
                lang + AND + CONST_KEY + "=";
        queryText = queryText.replace("|", "%7C");
        queryText = queryText.concat(key);
        return queryText;
    }

    public Boolean saveQuery(QueryMaps queryMaps) {
        queryMapsDAO.save(queryMaps);
        return queryMaps.getId() != null;
    }

    public List<QueryMaps> list() {
        return queryMapsDAO.list();
    }

}
