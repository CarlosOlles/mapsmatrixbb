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
        Assert.assertEquals("Hello from Spring", message);
    }

    @Test
    public void shouldSaveTaskAndResetTaskInstanceOnBean() {
        QueryMaps oldQueryMaps = queryHome.getQueryMaps();
        queryHome.getQueryMaps().setDescription("Sample Description");
        queryHome.getInfoAndSaveQuery();
        Assert.assertNotNull("Saved task ID is null,probably not saved", oldQueryMaps.getId());
        Assert.assertNull("QueryMaps has not been reset", queryHome.getQueryMaps().getDescription());
        Assert.assertNull("QueryMaps has not been reset", queryHome.getQueryMaps().getId());
        Assert.assertNotSame("QueryMaps object has not been replaced", oldQueryMaps, queryHome.getQueryMaps());
    }
}
