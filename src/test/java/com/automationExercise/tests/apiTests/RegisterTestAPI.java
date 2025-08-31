package com.automationExercise.tests.apiTests;

import com.automationExercise.apis.UserManagementAPI;
import com.automationExercise.tests.BaseTest;
import com.automationExercise.utils.TimeManager;
import com.automationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterTestAPI extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    @Test
    public void registerTest() {
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber"))
                .verifyUserCreatedSuccessfully();
    }
    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData=new JsonReader("SignUp_Data");
    }

}

