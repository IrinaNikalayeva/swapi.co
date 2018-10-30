package DataProvider;

import org.testng.annotations.DataProvider;

public class PlanetIdDataProvider {

    @DataProvider(name = "planetIdDataProvider")
    public static Object[][] dataProvider(){
        return new Object[][] {{"1"}, {"10"}, {"23"}, {"39"}, {"61"}};
    }

}
