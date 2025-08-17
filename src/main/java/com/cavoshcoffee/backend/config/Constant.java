package com.cavoshcoffee.backend.config;

public class Constant {
    // API Version
    public static final String API_VERSION = "/api/v1";

    // GlobalResponse - error messages
    public static final String GR_ERROR_NO_HANDLER = "The requested resource was not found";
    public static final String GR_ERROR_PARAMETER_TYPE = "The parameter '%s' must be of type '%s'";
    public static final String GR_ERROR_DETAILS = "[%s] %s";

    // Messages for all entities
    public static final String ALL_RETRIEVED = "All %s retrieved";
    public static final String RETRIEVED_ATTRIBUTE = "%s %s - %s: %s";
    public static final String GENERAL_ERROR = "Error retrieving %s";

    // Table messages
    public static final String DISTRICT_TABLE = "distrito";
    public static final String PRODUCT_TABLE = "producto";

    // Specific messages for entities
    public static String str_allRetrieved(String table) {
        return String.format(ALL_RETRIEVED, table);
    }

    public static String str_retrieved(String table, Long id) {
        return String.format(RETRIEVED_ATTRIBUTE, table, "retrieved", "id", id);
    }

    public static String str_generalError(String table) {
        return String.format(GENERAL_ERROR, table);
    }

    public static String str_notFound(String table, Long id) {
        return String.format(RETRIEVED_ATTRIBUTE, table, "not found", "id", id);
    }
}
