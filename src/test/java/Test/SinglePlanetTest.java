package Test;

import DataProvider.NegativePlanetIdProvider;
import DataProvider.PlanetIdDataProvider;
import Model.BaseModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SinglePlanetTest {
    private BaseModel model = new BaseModel();
    private String singlePlanetUrl = "https://swapi.co/api/planets/";


    @Test(dataProvider = "planetIdDataProvider", dataProviderClass = PlanetIdDataProvider.class)
    public void RequestPlanetByIdTest(String planetId) throws IOException {
        {
            model.PerformRequestToSwapi(singlePlanetUrl + planetId + "/?format=json");
            StringBuffer responseText = model.GetResponseText();
            System.out.println(responseText);
            boolean planetContent = model.CompareInfo(responseText, planetId);
            Assert.assertEquals(planetContent, true);
        }

    }

    @Test(dataProvider = "NegativePlaneIdDataProvider", dataProviderClass = NegativePlanetIdProvider.class)
    public void RequestPlanetByIdNegativeTest(String planetId) throws IOException {
        model.PerformRequestToSwapi(singlePlanetUrl + planetId + "/?format=json");
        int responseCode = model.GetResponseCode();
        //StringBuffer responseText = model.GetResponseText();
        System.out.println(responseCode);
        Assert.assertEquals(responseCode == 404, true);
    }


}
