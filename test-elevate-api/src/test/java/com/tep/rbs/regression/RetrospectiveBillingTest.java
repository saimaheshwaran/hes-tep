package com.tep.rbs.regression;

import com.tep.api.ApiDriver;
import com.tep.api.config.ApiEnums;
import com.tep.utilities.Enums;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class RetrospectiveBillingTest {

    ApiDriver apiDriver = new ApiDriver();

    @Test
    public void retrospective_api_without_claimNumber() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "claimNumber", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The claimNumber must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_Invalid_claimNumber() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "claimNumber", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The claimNumber size must be between 1 and 30."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_without_insurancePayer() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "insurancePayer", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The insurancePayer must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_Invalid_insurancePayer() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "insurancePayer", "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The insurancePayer size must be between 1 and 100."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_without_billChargedAmount() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "billChargedAmount", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The billChargedAmount must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_Invalid_billChargedAmount() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "billChargedAmount", "oooooooo", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The billChargedAmount has invalid format."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_without_insurancePayerBillIdentifier() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "insurancePayerBillIdentifier", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The insurancePayerBillIdentifier must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_Invalid_insurancePayerBillIdentifier() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "insurancePayerBillIdentifier", "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The insurancePayerBillIdentifier size must be between 1 and 50."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_without_billingProviderTaxIdentificationNumber() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "billingProviderTaxIdentificationNumber", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The billingProviderTaxIdentificationNumber must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_Invalid_billingProviderTaxIdentificationNumber() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "billingProviderTaxIdentificationNumber", "opopopopoi", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The billingProviderTaxIdentificationNumber The billingProviderTaxIdentificationNumber must have 9 digits and contain only numbers."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

//    @Test
//    public void retrospective_api_without_billReceivedDate() {
//        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
//        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "billReceivedDate", "", "", Enums.Manipulation_Mode.DELETE);
//        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
//        apiDriver.getResponse().then().statusCode(400);
//        apiDriver.getResponse().then().body("errors[0]", equalTo("The billReceivedDate must not be null."));
//        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
//        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
//    }

    @Test
    public void retrospective_api_Invalid_billReceivedDate() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "billReceivedDate", "opopopopoi", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The billReceivedDate has invalid format."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_without_renderedServices() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The renderedServices must not be empty."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_Invalid_renderedServices() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices", "opopopopoi", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The renderedServices has invalid format."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_dateofservicevalidation_nullvalue_scenario8() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Ione");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService", "", "", Enums.Manipulation_Mode.DELETE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The renderedServices[0].dateOfService must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_morethanoneservice_pastdate_scenario9() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Ptwopast");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService[0]", "2023", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(201);
        Allure.addAttachment("POST Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("POST Call-Response Body", apiDriver.getResponse().getBody().asString());
        String retroBillId = apiDriver.getResponse().getBody().jsonPath().getString("id");
        System.out.println(retroBillId);
        apiDriver.setConfigFromYaml("api_retrospective_billing_get");
        apiDriver.setPathParams(new HashMap<>() {{
            put("id", retroBillId);
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        apiDriver.getResponse().then().statusCode(200);
        apiDriver.getResponse().then().body("id", equalTo(retroBillId));
        apiDriver.getResponse().then().body("status", equalTo("VENDOR_ALLOCATED"));
        Allure.addAttachment("GET Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("GET Call-Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_dateofservicevalidation_invalidformat_scenario10() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService", "mmmm,20,01", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The renderedServices.dateOfService has invalid format."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_morethanoneservice_currentdate_scenario11() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Itwocurrent");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService[0]", "2025", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(201);
        Allure.addAttachment("POST Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("POST Call-Response Body", apiDriver.getResponse().getBody().asString());
        String retroBillId = apiDriver.getResponse().getBody().jsonPath().getString("id");
        apiDriver.setConfigFromYaml("api_retrospective_billing_get");
        apiDriver.setPathParams(new HashMap<>() {{
            put("id", retroBillId);
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        apiDriver.getResponse().then().statusCode(200);
        apiDriver.getResponse().then().body("id", equalTo(retroBillId));
        apiDriver.getResponse().then().body("status", equalTo("VENDOR_ALLOCATED"));
        Allure.addAttachment("GET Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("GET Call-Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_morethanoneservice_futuredate_scenario12() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Itwofuture");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService[0]", "2026", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(201);
        Allure.addAttachment("POST Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("POST Call-Response Body", apiDriver.getResponse().getBody().asString());
        String retroBillId = apiDriver.getResponse().getBody().jsonPath().getString("id");
        apiDriver.setConfigFromYaml("api_retrospective_billing_get");
        apiDriver.setPathParams(new HashMap<>() {{
            put("id", retroBillId);
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        apiDriver.getResponse().then().statusCode(200);
        apiDriver.getResponse().then().body("id", equalTo(retroBillId));
        apiDriver.getResponse().then().body("status", equalTo("READY_FOR_DELIVERY"));
        Allure.addAttachment("GET Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("GET Call-Response Body", apiDriver.getResponse().getBody().asString());
    }


    @Test
    public void retrospective_api_oneservice_currentdate_scenario13() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService[0]", "2025", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(201);
        Allure.addAttachment("POST Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("POST Call-Response Body", apiDriver.getResponse().getBody().asString());
        String retroBillId = apiDriver.getResponse().getBody().jsonPath().getString("id");
        apiDriver.setConfigFromYaml("api_retrospective_billing_get");
        apiDriver.setPathParams(new HashMap<>() {{
            put("id", retroBillId);
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        apiDriver.getResponse().then().statusCode(200);
        apiDriver.getResponse().then().body("id", equalTo(retroBillId));
        apiDriver.getResponse().then().body("status", equalTo("VENDOR_ALLOCATED"));
        Allure.addAttachment("GET Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("GET Call-Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_morethanoneservice_null_scenario14() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Ptwo");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[1].dateOfService", "", "", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The renderedServices[1].dateOfService must not be null."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_morethanoneservice_invalid_scenario15() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Itwo");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[1].dateOfService", "2024,2,1", "String", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(400);
        apiDriver.getResponse().then().body("errors[0]", equalTo("The renderedServices.dateOfService has invalid format."));
        Allure.addAttachment("Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_oneservice_futuredate_scenario16() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Pone");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService[0]", "2026", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(201);
        Allure.addAttachment("POST Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("POST Call-Response Body", apiDriver.getResponse().getBody().asString());
        String retroBillId = apiDriver.getResponse().getBody().jsonPath().getString("id");
        apiDriver.setConfigFromYaml("api_retrospective_billing_get");
        apiDriver.setPathParams(new HashMap<>() {{
            put("id", retroBillId);
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        apiDriver.getResponse().then().statusCode(200);
        apiDriver.getResponse().then().body("id", equalTo(retroBillId));
        apiDriver.getResponse().then().body("status", equalTo("READY_FOR_DELIVERY"));
        Allure.addAttachment("GET Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("GET Call-Response Body", apiDriver.getResponse().getBody().asString());
    }

    @Test
    public void retrospective_api_oneservice_pastdate_scenario17() {
        apiDriver.setConfigFromYaml("api_retrospective_billing_837Ione");
        apiDriver.updateRequestBody(apiDriver.getBody().toString(), "renderedServices[0].dateOfService[0]", "2023", "Integer", Enums.Manipulation_Mode.UPDATE);
        apiDriver.executeRequest(ApiEnums.Http_Method.POST);
        apiDriver.getResponse().then().statusCode(201);
        Allure.addAttachment("POST Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("POST Call-Response Body", apiDriver.getResponse().getBody().asString());
        String retroBillId = apiDriver.getResponse().getBody().jsonPath().getString("id");
        apiDriver.setConfigFromYaml("api_retrospective_billing_get");
        apiDriver.setPathParams(new HashMap<>() {{
            put("id", retroBillId);
        }});
        apiDriver.executeRequest(ApiEnums.Http_Method.GET);
        apiDriver.getResponse().then().statusCode(200);
        apiDriver.getResponse().then().body("id", equalTo(retroBillId));
        apiDriver.getResponse().then().body("status", equalTo("VENDOR_ALLOCATED"));
        Allure.addAttachment("GET Call-Status Code", String.valueOf(apiDriver.Response().get().statusCode()));
        Allure.addAttachment("GET Call-Response Body", apiDriver.getResponse().getBody().asString());
    }

}
