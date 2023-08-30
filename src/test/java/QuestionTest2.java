import org.junit.Assert;
import org.junit.Test;
import services.AdapterService;

import java.io.IOException;


public class QuestionTest2 extends CommonTest{

    @Test
    public void testDataOffBill() throws IOException {
        Assert.assertEquals(AdapterService.getInstanceBillService().listBill().size(), 3);
    }

}
