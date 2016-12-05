package com.dhl.serv.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    // Spring profile for development and production, see http://jhipster.github.io/profiles/
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    // Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    // Spring profile used when deploying to Heroku
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    // Spring profile used to disable swagger
    public static final String SPRING_PROFILE_SWAGGER = "swagger";
    // Spring profile used to disable running liquibase
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    public static final String SYSTEM_ACCOUNT = "system";

    public static final String PATH_PROJECT = System.getProperty("user.dir");
    public static final String PATH_PROJECT_IMAGE_UPLOAD = System.getProperty("user.dir")+"/src/main/webapp/content/images/upload/";
    public static final String PATH_PROJECT_IMAGE_USER_DEFAULT = System.getProperty("user.dir")+"/src/main/webapp/content/images/image_profile_default.jpeg";
    public static final String PATH_PROJECT_IMAGE_USER_DEFAULT_NEW = System.getProperty("user.dir")+"/src/main/webapp/content/images/upload/image_profile_default.jpeg";
//    public static final String IMAGE_PROFILE_DEFAULT = "image_profile_default.jpeg";
//    public static final String PATH_PROJECT_IMAGE_USER_DEFAULT_NEW = System.getProperty("user.dir")+"/src/main/webapp/content/images/";
    public static final String PATH_PROJECT_IMAGE_UPLOAD_WEB = "/content/images/upload/";

    private Constants() {
    }
}
