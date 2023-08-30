import org.junit.BeforeClass;
import services.AdapterService;

public class CommonTest {

    @BeforeClass
    public static void initData(){
        //Init
        AdapterService.initAllService();

        //Get Account Admin
        AdapterService.getInstanceCustomerService().setCurrentCustomerLogin(AdapterService.getInstanceCustomerService().findCustomerById(0));
    }
}
