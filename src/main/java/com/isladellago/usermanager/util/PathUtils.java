package com.isladellago.usermanager.util;

public final class PathUtils {

    public static final class PathParam {

        public static final String JWT_TOKEN = "jwt-token";

    }

    public static final class Token {

        public static final String VALIDATE_PATH = "/validate/{" + PathParam.JWT_TOKEN + "}";
    }
}
