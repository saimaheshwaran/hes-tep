package com.tep.api.support;

import com.tep.api.ApiDriver;

import java.util.Map;

import static com.tep.utilities.MapUtils.updateMap;

public class ApiDriverHelpers extends ApiDriver {

    // Methods for updating query params, form params, cookies, and headers (with logging)
    public void setQueryParams(Map<String, String> queryParams, String mode) {
        updateAndLogParams("QUERY PARAMETERS", queryParams, mode);
        //this.queryParams = updateMap(mode, queryParams, this.queryParams);
        setQueryParams(updateMap(mode, queryParams,getQueryParams()));
    }

    public void setFormParams(Map<String, String> formParams, String mode) {
        updateAndLogParams("FORM PARAMETERS", formParams, mode);
        //this.formParams = updateMap(mode, formParams, this.formParams);
        setFormParams(updateMap(mode, formParams, getFormParams()));
    }

    public void setCookies(Map<String, String> cookies, String mode) {
        updateAndLogParams("COOKIES", cookies, mode);
        //this.cookies = updateMap(mode, cookies, this.cookies);
        setCookies(updateMap(mode, cookies, getCookies()));
    }

    public void setHeaders(Map<String, String> headers, String mode) {
        updateAndLogParams("HEADERS", headers, mode);
        //this.headers = updateMap(mode, headers, this.headers);
        setHeaders(updateMap(mode, headers, getHeaders()));
    }
}
