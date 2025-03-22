package ge.mycredo.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;


public class CredoDataProvider {

    @DataProvider(name = "negativeLoginDataProvider")
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {
                        Constants.LANGUAGE_BUTTON_TEXT_GE,
                        Constants.HEADER_GE,
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        Constants.INCORRECT_DATA_ERROR_GE
                },

                {
                        Constants.LANGUAGE_BUTTON_TEXT_SVAN,
                        Constants.HEADER_SVAN,
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        Constants.INCORRECT_DATA_ERROR_SVAN
                },

                {
                        Constants.LANGUAGE_BUTTON_TEXT_ENG,
                        Constants.HEADER_ENG,
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        Constants.INCORRECT_DATA_ERROR_ENG
                }
        };
    }
}
