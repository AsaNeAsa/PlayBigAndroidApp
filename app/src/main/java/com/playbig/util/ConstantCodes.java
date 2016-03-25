package com.playbig.util;

/**
 * Created by android on 27/8/15.
 */
public class ConstantCodes {




    public static final String IN_STATUS = "STATUS";
    public static final String IN_RESULT_OK = "OK";
    public static final String IN_RESULT_FAIL = "FAIL";







    //
    //** user name and password for session id
    //
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin@123*";

    //
    //** group item types in left side navigation drawer
    //

    //public static final int LEFT_NAV_HOME = 1;
    //public static final int LEFT_NAV_NEW_ARRIVALS = 2;
    public static final int LEFT_NAV_SETTINGS = 3;
    public static final int LEFT_NAV_REFFER_FRIEND = 4;
    public static final int LEFT_NAV_LOGIN = 5;
    public static final int LEFT_NAV_DYNAMIC = 6;
    public static final int LEFT_NAV_TINDER = 7;

    //
    //**message of login expired
    //
    public static final String MSG_SESSION_EXPIRED = "Session expired. Try to relogin.";

    public static class PREFERENCE_KEY {
        public static final String IS_INTRO_VIEWED = "is_intro_showed";

        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
        public static final String USER_PROFILE_PIC = "user_profile_pic";
        public static final String USER_EMAIL_ID = "user_email_id";
        public static final String IS_USER_LOGGED_IN = "user_loggedin";
    }
    //
    //** fixed catergory id in left side navigation drawer
    //
    public static final String LEFT_NAV_SETTINGS_CATEGORY_ID = "-50";
    public static final String LEFT_NAV_REFFER_FRIEND_CATEGORY_ID = "-51";
    public static final String LEFT_NAV_LOGIN_CATEGORY_ID = "-52";


    //
    //** api keys
    //

    public static final String CATALOG_CATEGORY_TREE = "catalog_category.tree";
    public static final String CUSTOMAPI_GETALLATTRIBUTES = "customeapi_api.getAllAttributes";
    public static final String CUSTOR_LIST = "customer.list";
    public static final String CUSTOR_CREATE = "customer.create";
    public static final String CUSTOR_UPDATE = "customer.update";
    public static final String CUSTOMAPI_ATTRIBUTES_CALLER = "customeapi_api.attributescaller";
    public static final String PRODUCT_LIST = "customeapi_api.getProductlist";
    public static final String LIKE_PRODUCT_LIST = "customeapi_api.likeitlist";
    public static final String PRODUCT_INFO = "catalog_product.info";
    public static final String ZIPCODE_CHECK = "customeapi_api.zipcodechecker";
    public static final String RELATED_PRODUCT_LIST = "catalog_product_link.list";
    public static final String GET_SORTABLE_LIST = "customeapi_api.getSortable";
    public static final String CART_CREATE = "cart.create";
    public static final String CART_PRODUCT_ADD = "cart_product.add";
    public static final String CUSTOMER_ADDRESS_LIST = "customer_address.list";
    public static final String COUNTRY_LIST = "country.list";
    public static final String REGION_LIST = "region.list";
    public static final String CART_PRODUCT_LIST = "cart_product.list";
    public static final String CART_PRODUCT_REMOVE = "cart_product.remove";
    public static final String CART_PRODUCT_UPDATE = "cart_product.update";
    public static final String CART_CUSTOMER_SET = "cart_customer.set";
    public static final String CART_BILLING_SHIPPING_ADDRESSE_SET = "cart_customer.addresses";
    public static final String CART_SHIPPING_LIST = "cart_shipping.list";
    public static final String CART_PAYMENT_LIST = "cart_payment.list";
    public static final String CART_CART_SET_SHIPPING_METHOD = "cart_shipping.method";
    public static final String CART_CART_SET_PAYMENT_METHOD = "cart_payment.method";
    public static final String CART_TOTAL = "cart.totals";
    public static final String CUSTOMER_ADDRESS_CREATE = "customer_address.create";
    public static final String CUSTOMER_ADDRESS_DELETE = "customer_address.delete";
    public static final String CUSTOMER_ADDRESS_UPDATE = "customer_address.update";
    public static final String CUSTOMER_GET_CREDIT = "customercredit.getcredit";
    public static final String CUSTOMER_ADD_CREDIT = "customercredit.incrcredit";
    public static final String GET_WISH_LIST = "customeapi_api.addwishlist";
    public static final String SEND_FEEDBACK = "customeapi_api.feedbackmobile";
    public static final String ORDER_LIST = "order.list";
    public static final String CART_ORDER_PLACE = "cart.order";
    public static final String ORDER_INFO = "sales_order.info";
    public static final String PRODUCT_LIKE_IT = "customeapi_api.likeit";
    public static final String STYLE_LIST = "customeapi_api.stylelist";
    public static final String CANCEL_ORDER = "customeapi_api.cancleorder";
    public static final String RETURN_ORDER = "customeapi_api.orderretrun";
    public static final String INTERNAL_CREDIT = "customeapi_api.internalc";
    public static final String FORGOT_PASSWORD = "customeapi_api.forgotpwd";
    public static final String CHANGE_PASSWORD = "customeapi_api.restepwd";
    public static final String CMS_PAGE = "customeapi_api.getCMSPage";
    public static final String SEARCH_PRODUCT_LIST = "customeapi_api.serachresults";
    public static final String INVENTORY_STOCK_CHECK = "cataloginventory_stock_item.list";

    //
    //** fixed values for api
    //

    public static final String WEBSITE_ID = "website_id";
    public static final String STORE_ID = "store_id";
    public static final String GROUP_ID = "group_id";

    public static final String WEBSITE_ID_VALUE = "1";
    public static final String STORE_ID_VALUE = "1";
    public static final String GROUP_ID_VALUE = "1";

    public static final String ORDER_BY_DESC = "DESC";
    public static final String ORDER_BY_ASC = "ASC";
    public static final String DEFALUT_SORTING_IN_FILTER = "created_at";

    //
    //** fix value of category id for home and new arrival category
    //

    public static final String HOME_CATEGORY_ID = "74";
    public static final String NEW_ARRIVALS_CATEGORY_ID = "59";

    //
    //** number of items will see in single page in home fragment
    //
    public static final int NUMBER_OF_ITEMS = 20;

    //
    //** default wallet money for first time signup user
    //
    public static final int FIRST_TIME_REWARD_WALLER_MONEY = 500;


    //
    //** defalut number of quantity add in product
    //
    public static final String QUANTITY = "1";


    //
    //** filter types in right side navigation drawer
    //

    public static final String KEY_FILTER_TYPE_USUAL = "Usual";
    public static final String KEY_FILTER_TYPE_PERSONALIZED = "Personalized";


    //
    //** header and type keys in right side navigation drawer api
    //

    public static final String TYPE_HEADER = "header";
    public static final String TYPE_ITEM = "item";

    //
    //** fragments title
    //

    public static final String FRAGMENT_HOME_TITLE = "Home";
    public static final String FRAGMENT_NEW_ARRIVALS_TITLE = "New Arrivals";
    public static final String FRAGMENT_REFFER_FREIND_TITLE = "Refer a Friend";
    public static final String FRAGMENT_SETTINGS_TITLE = "My Account";
    public static final String FRAGMENT_LOGIN_TITLE = "Login";


    //
    //** Bundles keys
    //
    public static final String BUNDLE_KEY_PRODUCT_ID = "product_id";
    public static final String BUNDLE_KEY_ORDER_ID = "order_id";
    public static final String BUNDLE_KEY_PRODUCT_POSITION = "product_position";
    public static final String BUNDLE_KEY_WISHLIST_ID = "wishlist_id";

    //
    //** keys for product info api
    //
    public static final String PRODCUTINFO_KEY_PRODUCT_ID = "product_id";
    public static final String PRODCUTINFO_KEY_PRODUCT_SKU = "sku";
    public static final String PRODCUTINFO_KEY_PRICE = "price";
    public static final String PRODCUTINFO_KEY_SPECIAL_PRICE = "special_price";
    public static final String PRODCUTINFO_KEY_NAME = "name";
    public static final String PRODCUTINFO_KEY_DESCRIPTION = "short_description";
    public static final String PRODCUTINFO_KEY_JSONCONFIG = "jsonconfig";
    public static final String PRODCUTINFO_KEY_ATTRIBUTES = "attributes";
    public static final String PRODCUTINFO_KEY_MEDIA_GALLERY = "media_gallery";
    public static final String PRODCUTINFO_KEY_FILE = "file";
    public static final String PRODCUTINFO_KEY_OPTIONS = "options";
    public static final String PRODCUTINFO_KEY_CODE = "code";
    public static final String PRODCUTINFO_KEY_COLOR = "color";
    public static final String PRODCUTINFO_KEY_SIZE = "size";
    public static final String PRODCUTINFO_KEY_SPECIAL_FROM_DATE = "special_from_date";
    public static final String PRODCUTINFO_KEY_SPECIAL_TO_DATE = "special_to_date";
    public static final String PRODCUTINFO_KEY_CURRENTDATE = "currentDate";
    public static final String PRODCUTINFO_KEY_TYPE = "type";
    public static final String PRODCUTINFO_KEY_URL = "ProductUrl";
    public static final String PRODCUTINFO_OPTIONS_QTY = "options_qty";

    //
    //** preference keys
    //

    public static final String PREFERENCE_KEY_CATEGORY_JSON = "category_json";
    public static final String PREFERENCE_KEY_SESSIONID = "sessionid";
    public static final String PREFERENCE_KEY_SORTBY = "sortby";
    public static final String PREFERENCE_KEY_CUSTOMER_ID = "customer_id";
    public static final String PREFERENCE_KEY_CUSTOMER_NAME = "customer_name";
    public static final String PREFERENCE_KEY_IS_LOGIN = "islogin";
    public static final String PREFERENCE_KEY_CUSTOMER_EMAIL = "customer_emailid";
    public static final String PREFERENCE_KEY_IS_FACEBOOK_USER = "isfacebookuser";
    public static final String PREFERENCE_KEY_CART_PRODUCT_LIST = "cart_product_list";


    //
    //** cart_id, items_count
    //
    public static String CART_ID;
    public static int CART_ITEMS_COUNT = 0;

    //
    //** extras keys
    //
    public static final String EXTRA_ADDRESS_MODAL = "address_modal";
    public static final String EXTRA_ADDRESS_ID = "address_id";
    public static final String EXTRA_PAGE_INDICATOR = "page_indicator";
    public static final String EXTRA_PRODUCT_ID = "product_id";
    public static final String EXTRA_PRODUCT_SKU = "product_sku";
    public static final String EXTRA_PRODUCT_NAME = "product_name";
    public static final String EXTRA_SELECTED_PRODUCT_LIST = "selected_product_list";
    public static final String EXTRA_CART_TOTAL = "total_cart_amount";
    public static final String EXTRA_CATEGORY_ID = "category_id";
    public static final String EXTRA_UPLOADED_IMAGE = "uploaded_image_file";
    public static final String EXTRA_LAST_ACTIVITY = "last_activity";

    //
    //** keys
    //

    public static final String KEY_CASH_ON_DELIVERY = "cashondelivery";
    public static final String KEY_CHECKMO = "checkmo";
    public static final String KEY_CONFIGURABLE = "configurable";
    public static final String KEY_MYBAG = "mybag";
    public static final String KEY_ORDERDETAILS = "orderdetails";
    public static final String KEY_GET_WISHLIST = "list";
    public static final String KEY_REMOVE_WISHLIST = "remove";
    public static final String KEY_ADD_WISHLIST = "add";
    public static final String KEY_ORDER_ID = "order_id";
    public static final String KEY_SELECT_PRODUCT_LIST = "select_product_list";

    //
    //** activity list
    //
    public static final String ACTIVITY_HOME = "activity_home_key";
    public static final String ACTIVITY_PRODUCT_DETAILS = "activity_product_details_key";
    public static final String ACTIVITY_IMAGE_CAPTURE = "activity_image_capture_key";


    //
    //** local broadcast keys
    //

    public static final String BROAD_CAST_KEY_WHISHLIST = "wishlist";
    public static final String BROAD_CAST_KEY_COUNT_ON_MENU = "count";
    public static final String BROAD_CAST_LOGIN_DONE = "login_done";
    public static final String BROAD_CAST_PRODUCT_WISHLISTED_PRODUCT_DETAILS = "item_wishlisted_productdetails";
    public static final String BROAD_CAST_USERNAME_CHANGED = "username_changed";

    //
    //** facebook on activtiy result
    //

    public static final int ACTION_FACEBOOK = 150;

    //
    //** default view params
    //

    public static final int DEFAULT_VIEW_GRID = 1;
    public static final int DEFAULT_VIEW_TINDER = 2;

    //
    //** exception
    //

    public static final boolean HANDLE_FORCE_CLOSE_EXCEPTION = true;


    //
    //** post api URL
    //

    public static final String CRASH_API_URL = "http://candidlycouture.com/test/imgupload/crashemail.php";
    public static final String IMAGE_UPLOAD_API_URL = "http://candidlycouture.com/imgupload/ImgUpload.php";


    public static final String CRASH_API_KEY_EMAIL = "email";
    public static final String CRASH_API_KEY_OS = "os";
    public static final String CRASH_API_KEY_LOG = "log";

    public static final String CRASH_API_KEY_EMAIL_VALUE = "ronak.joshi@openxcellinc.com";
    public static final String CRASH_API_KEY_OS_VALUE = "os";


    //
    //** order status keys
    //

    public static final String ORDER_PENDING = "pending";
    public static final String ORDER_CANCELED = "canceled";
    public static final String ORDER_COMPLETED = "complete";
    public static final String ORDER_PROCESSING = "processing";


    //
    //** result code for add new address
    //

    public static final int RESULT_CODE_ADD_NEW_ADDRESS = 333;
}
