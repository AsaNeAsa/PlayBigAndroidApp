package com.playbig.network;

import java.util.HashMap;

public class WebServiceConfigs {


    public enum WebService {
        REGISTER,
        LOG_IN,
        POST_LIST,
        CREATE_POST,
        VERSION_TRANSPORT
    }

    private static HashMap<WebService, WebServiceModel> list;

    public static HashMap<WebService, WebServiceModel> getMethods() {

        if (list == null) {
            list = new HashMap<WebService, WebServiceModel>();
            list.put(WebService.REGISTER, new WebServiceModel(REGISTER, Method.POST, true));
            list.put(WebService.LOG_IN, new WebServiceModel(LOG_IN, Method.POST, true));
            list.put(WebService.POST_LIST, new WebServiceModel(POST_LIST, Method.POST, true));
            list.put(WebService.CREATE_POST, new WebServiceModel(CREATE_POST, Method.POST, true));
            list.put(WebService.VERSION_TRANSPORT, new WebServiceModel(VERSION_TRANSPORT, Method.GET, true));
        }
        return list;
    }

    //====================== LINKS =========================
    public static final String BASE_URL = "http://27.109.10.154:8080/TransportMe/";
    //  public static final String BASE_URL = "http://192.168.3.17:8080/TransportMe/";
    public static final String REGISTER = BASE_URL + "register.php";
    public static final String LOG_IN = BASE_URL + "Logint";
    public static final String POST_LIST = BASE_URL + "post_list.php";
    public static final String CREATE_POST = BASE_URL + "create_post.php";
    public static final String VERSION_TRANSPORT = BASE_URL + "Version_transport";


    //====================== CONSTANTS =========================
    public interface Method {
        public final int GET = 1;
        public final int POST = 2;
    }

    public static final String FILE_FILE = "file:";
    public static final String FILE_IMAGE = "img:";
    public static final String FILE_VIDEO = "video:";

//    public enum WebService {
//        GET_HOSPITAL_LIST, GET_DEPARTMENT_LISTS, GET_HOSPITAL_CONSULTANTS_LISTS, GET_NEWS_LIST, GET_SPECIALITIES_LIST, GET_HOSPITAL_SERVICES_LIST, GET_ABOUT_US
//    }
//
//    private static HashMap<WebService, WebServiceModel> list;
//
//    public static HashMap<WebService, WebServiceModel> getMethods() {
//
//        if (list == null) {
//            list = new HashMap<WebService, WebServiceModel>();
//            list.put(WebService.GET_HOSPITAL_LIST, new WebServiceModel(GET_HOSPITAL_LIST, Method.GET, true));
//            list.put(WebService.GET_DEPARTMENT_LISTS, new WebServiceModel(GET_DEPARTMENT_LISTS, Method.GET, true));
//            list.put(WebService.GET_HOSPITAL_CONSULTANTS_LISTS, new WebServiceModel(GET_HOSPITAL_CONSULTANTS_LISTS, Method.GET, true));
//            list.put(WebService.GET_NEWS_LIST, new WebServiceModel(GET_NEWS_LIST, Method.GET, true));
//            list.put(WebService.GET_SPECIALITIES_LIST, new WebServiceModel(GET_SPECIALITIES_LIST, Method.GET, true));
//            list.put(WebService.GET_HOSPITAL_SERVICES_LIST, new WebServiceModel(GET_HOSPITAL_SERVICES_LIST, Method.GET, true));
//            list.put(WebService.GET_ABOUT_US, new WebServiceModel(GET_ABOUT_US, Method.GET, true));
//        }
//        return list;
//    }
//
//    //====================== LINKS =========================
//    public static final String BASE_URL = "http://www.bonsecours.ie/";
//    public static final String GET_HOSPITAL_LIST = BASE_URL + "hospitals.json";
//    public static final String GET_SPECIALITIES_LIST = BASE_URL + "specialities.json";
//    public static final String GET_DEPARTMENT_LISTS = BASE_URL + "hospitaldepartments/%s.json";
//    public static final String GET_HOSPITAL_CONSULTANTS_LISTS = BASE_URL + "hospitalconsultants/%s.json";
//    public static final String GET_NEWS_LIST = BASE_URL + "hospitalnews/%s.json";
//    public static final String GET_HOSPITAL_SERVICES_LIST = BASE_URL + "hospitalservices/%s.json";
//    public static final String GET_ABOUT_US = BASE_URL + "bonsecours.json";
//
//    //====================== CONSTANTS =========================
//    public interface Method {
//        public final int GET = 1;
//        public final int POST = 2;
//    }
//
//    public static final String FILE_FILE = "file:";
//    public static final String FILE_IMAGE = "img:";
//    public static final String FILE_VIDEO = "video:";

}