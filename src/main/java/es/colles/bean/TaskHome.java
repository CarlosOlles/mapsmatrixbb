package es.colles.bean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import es.colles.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("taskHome")
public class TaskHome {

    private static final Logger logger = LoggerFactory.getLogger(TaskHome.class);

    private Task task = new Task();
    private List<Task> tasks;

    @Autowired
    private TaskDao taskDao;


    public String getMessage() {
        logger.debug("Returning message from task home bean");
        return "Hello from Spring";
    }

    public Task getTask() {
        return task;
    }

    public void saveTask() {
        getInfo();
        taskDao.save(task);
        task = new Task();
        invalidateTasks();
    }

    private void invalidateTasks() {
        tasks = null;
    }

    public List<Task> getTasks() {

        if (tasks == null) {
            tasks = taskDao.list();
        }
        return tasks;

    }

    public void getInfo() {
        String key = "AIzaSyAQUI9Aqu09Nbeh-NTWU08sAqe068HwHmY";
        String mode = "car";
        String query = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC&mode=" + mode + "&language=es-ES&key=";
        query = query.replace("|", "%7C");

        try {

            Client client = Client.create();

            WebResource webResource = client.resource(query + key);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }


    }
}
