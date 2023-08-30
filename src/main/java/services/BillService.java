package services;

import models.Bill;
import models.enums.FilePathDataEnum;
import utils.ScannerFactoryUtil;

import java.util.List;
import java.util.Scanner;

public interface BillService {
    Scanner scanner = ScannerFactoryUtil.getScanner(FilePathDataEnum.PATH_DATA_CSV_BILL);

    List<Bill> listBill();

    Bill findBillById(Integer id);

    List<Bill> findBillByArrayID(int[] ids);

    void initListDataBill();




}
