package org.sm;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Base config for any restassured usage
        RestAssured.config = RestAssuredConfig
                .config()
                .logConfig(LogConfig
                        .logConfig()
                        .enablePrettyPrinting(true)
                        .enableLoggingOfRequestAndResponseIfValidationFails()
                )
                .and()
                .httpClient(HttpClientConfig
                        .httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000)
                );

        //Request
        RequestSpecification reqSpec = RestAssured.given()
                .baseUri("https://catfact.ninja")
                .header("Content-Type", "application/json")
                .log().all();

        Response response = reqSpec
                .when()
                        .get("/fact");
        System.out.println(response.asString());

        //System.out.println(reqSpec.request("GET", "/fact").asString());

    }
}