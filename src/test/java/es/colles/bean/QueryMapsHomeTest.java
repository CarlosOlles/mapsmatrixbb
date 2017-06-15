package es.colles.bean;

import es.colles.model.QueryMaps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QueryMapsHomeTest {

    @Autowired
    private QueryHome queryHome;

    @Test
    public void shouldGetCorrectMessage() {
        String message = queryHome.getMessage();
        Assert.assertEquals("Carlos rulz!!", message);
    }

    @Test
    public void shouldSaveTaskAndResetTaskInstanceOnBean() {
        QueryMaps queryMaps = queryHome.getQueryMaps();
        queryMaps.setKey("KEY");

        queryHome.getQueryMaps().setDescription("Sample Description");
        Assert.assertNotNull("Saved task ID is null,probably not saved", queryMaps.getKey());
    }
}
