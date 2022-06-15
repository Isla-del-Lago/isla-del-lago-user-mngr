package com.isladellago.usermanager.util;

public final class PathUtils {

    public static final class Health {
        public static final String ROOT_PATH = "/api/v1/user";

        public static final String HEALTH_PATH = "/health";
    }

    public static final class User {
        public static final String ROOT_PATH = "/api/v1/user";

        public static final String CREATE_USER = "/create";
        public static final String GET_USER_BY_EMAIL = "/email";
    }

    public static final class Token {
        public static final String ROOT_PATH = "/api/v1/user";

        public static final String LOGIN = "/login";
    }

    public static final class SecurityManagerClient {

        public static final String ROOT_PATH = "/api/v1/token";

        public static final String VALIDATE_TOKEN_PATH = ROOT_PATH + "/validate";
        public static final String CREATE_TOKEN_PATH = ROOT_PATH;
    }
}
