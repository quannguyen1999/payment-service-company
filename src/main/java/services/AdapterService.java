package services;

import models.enums.FilePathDataEnum;
import services.impl.BillServiceImpl;
import services.impl.CustomerServiceImpl;
import services.impl.MenuServiceImpl;
import services.impl.PaymentServiceImpl;
import services.impl.QuestionServiceImpl;
import utils.PropertiesUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class AdapterService {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static volatile BillService INSTANCE_BILL_SERVICE = null;
    private static volatile CustomerService INSTANCE_CUSTOMER_SERVICE = null;

    private static volatile PaymentService INSTANCE_PAYMENT_SERVICE = null;

    private static volatile QuestionService INSTANCE_QUESTION_SERVICE = null;

    private static volatile MenuService INSTANCE_MENU_SERVICE = null;

    public static BillService getInstanceBillService() {
        if (Objects.isNull(INSTANCE_BILL_SERVICE)) {
            synchronized (BillService.class) {
                if (Objects.isNull(INSTANCE_BILL_SERVICE)) {
                    INSTANCE_BILL_SERVICE = new BillServiceImpl();
                }
            }
        }
        return INSTANCE_BILL_SERVICE;
    }

    public static MenuService getInstanceMenuService() {
        if (Objects.isNull(INSTANCE_MENU_SERVICE)) {
            synchronized (MenuService.class) {
                if (Objects.isNull(INSTANCE_MENU_SERVICE)) {
                    INSTANCE_MENU_SERVICE = new MenuServiceImpl();
                }
            }
        }
        return INSTANCE_MENU_SERVICE;
    }

    public static CustomerService getInstanceCustomerService() {
        if (Objects.isNull(INSTANCE_CUSTOMER_SERVICE)) {
            synchronized (CustomerService.class) {
                if (Objects.isNull(INSTANCE_CUSTOMER_SERVICE)) {
                    INSTANCE_CUSTOMER_SERVICE = new CustomerServiceImpl();
                }
            }
        }
        return INSTANCE_CUSTOMER_SERVICE;
    }

    public static QuestionService getInstanceQuestionService() {
        if (Objects.isNull(INSTANCE_QUESTION_SERVICE)) {
            synchronized (CustomerService.class) {
                if (Objects.isNull(INSTANCE_QUESTION_SERVICE)) {
                    INSTANCE_QUESTION_SERVICE = new QuestionServiceImpl();
                }
            }
        }
        return INSTANCE_QUESTION_SERVICE;
    }


    public static PaymentService getInstancePaymentService() {
        if (Objects.isNull(INSTANCE_PAYMENT_SERVICE)) {
            synchronized (CustomerService.class) {
                if (Objects.isNull(INSTANCE_PAYMENT_SERVICE)) {
                    INSTANCE_PAYMENT_SERVICE = new PaymentServiceImpl();
                }
            }
        }
        return INSTANCE_PAYMENT_SERVICE;
    }


    public static void initAllService(){
        //Init Scanner CSV
        getInstanceBillService();
        getInstanceMenuService();
        getInstanceCustomerService();
        getInstanceQuestionService();
        getInstancePaymentService();

        //Get data in csv
        INSTANCE_CUSTOMER_SERVICE.initListDataCustomer();
        INSTANCE_BILL_SERVICE.initListDataBill();

        //Init Prop
        PropertiesUtil.getProperties(FilePathDataEnum.PATH_DATA_PROP_MESSAGE);





    }



}
