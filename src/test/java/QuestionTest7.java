import constants.CommonCharacterConstant;
import models.enums.FilePathDataEnum;
import org.junit.Assert;
import org.junit.Test;
import services.AdapterService;
import utils.ScannerFactoryUtil;

import java.io.IOException;
import java.util.Scanner;


public class QuestionTest7 extends CommonTest{

    Scanner scanner = ScannerFactoryUtil.getScanner(FilePathDataEnum.PATH_DATA_CSV_QUESTION_7);

    @Test
    public void testDataOffBill() throws IOException {
        //Get File In CSV to Test
        scanner.useDelimiter(CommonCharacterConstant.COMMA);
        while (scanner.hasNext()) {
            String[] result = scanner.nextLine().split(CommonCharacterConstant.COMMA);
            String outputResult = result[0];
            String expectedResult = result[1];

            Assert.assertEquals(AdapterService.getInstanceQuestionService().processQuestion7(outputResult).toString(), expectedResult);
        }
        scanner.close();
    }

}
