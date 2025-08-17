package com.cavoshcoffee.backend.config;

public class Constant {
    // API Version
    public static final String API_VERSION = "/api/v1";

    // GlobalResponse - error messages
    public static final String GR_ERROR_NO_HANDLER = "The requested resource was not found";
    public static final String GR_ERROR_PARAMETER_TYPE = "The parameter '%s' must be of type '%s'";
    public static final String GR_ERROR_DETAILS = "[%s] %s";

    // Distrito messages
    public static final String DISTRICT_TABLE = "distrito";
    public static final String DISTRICT_ALL_RETRIEVED = "All distritos retrieved";
    public static final String DISTRICT_RETRIEVED = "Distrito retrieved - id: ";
    public static final String DISTRICT_GENERAL_ERROR = "Error retrieving distritos";
    public static final String DISTRICT_NOT_FOUND = "Distrito not found - id: ";

}
