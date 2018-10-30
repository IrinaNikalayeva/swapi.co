package DataProvider;

import org.testng.annotations.DataProvider;

public class NegativePlanetIdProvider {

    @DataProvider(name = "NegativePlaneIdDataProvider")
    public static Object[] [] dataProvider(){
        return new Object[][] {{"0"}, {"!"}, {"*"} };
    }
}
