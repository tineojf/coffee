package com.cavoshcoffee.backend.config;

public class Constant {
    // API Version
    public static final String API_VERSION = "/api/v1";

    // GlobalResponse - error messages
    public static final String GR_ERROR_NO_HANDLER = "The requested resource was not found";
    public static final String GR_ERROR_PARAMETER_TYPE = "The parameter '%s' must be of type '%s'";
    public static final String GR_ERROR_DETAILS = "[%s] %s";

    // Table messages
    public static final String DISTRICT_TABLE = "distrito";
    public static final String PRODUCT_TABLE = "producto";
    public static final String USER_TABLE = "usuario";
    public static final String FAVORITE_TABLE = "favorito";
    public static final String  LOCAL_TABLE = "local";
}
