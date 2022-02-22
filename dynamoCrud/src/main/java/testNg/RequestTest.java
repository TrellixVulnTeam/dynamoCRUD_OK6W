package testNg;

import com.tutorial.dynamocrud.Request;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.*;

public class RequestTest {

    private Request request;

    public void Scenario(){
        request = new Request();
    }

    @Test
    public void testGetId() {

    }

    @Test
    public void testSetId() {
    }

    @Test
    public void testGetHttpMethod() {
        Assert.assertTrue(Objects.equals(request.getHttpMethod(), "POST"));
    }

    @Test
    public void testGetUser() {
    }

    @Test
    public void testSetUser() {
    }
}