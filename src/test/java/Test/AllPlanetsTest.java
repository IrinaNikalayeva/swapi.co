package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import Model.BaseModel;


    public class AllPlanetsTest {

        private BaseModel model = new BaseModel();
        private String allPlanetsUri = "https://swapi.co/api/planets/?format=json";

        @Test
        public void RequestAllPlanetsTest() throws IOException {
            model.PerformRequestToSwapi((allPlanetsUri));
            int responseCode = model.GetResponseCode();
            StringBuffer responseText = model.GetResponseText();
            Object planetCount = model.GetTagValue(responseText, "count");

            Assert.assertEquals( responseCode, 200);
            Assert.assertEquals(planetCount, 61);
        }


    }
