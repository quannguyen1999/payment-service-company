package services.impl;

import constants.CharacterConstant;
import models.Bill;
import services.BillService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillServiceImpl implements BillService {

    private ArrayList<Bill> billArrayList = null;


    @Override
    public List<Bill> listBill() {
        return billArrayList;
    }

    @Override
    public Bill findBillById(Integer id) {
        return billArrayList
                .stream()
                .filter(t -> t.getBillNo().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Bill> findBillByArrayID(int[] ids) {
        List<Bill> resultBills = new ArrayList<>();
        for (int id : ids) {
            Bill bill = billArrayList.stream().filter(billResult -> billResult.getBillNo().equals(id)).findFirst().orElse(null);
            if(!Objects.isNull(bill)){
                resultBills.add(bill);
            }
        }
        return resultBills;
    }

    @Override
    public void initListDataBill() {
        if(Objects.isNull(billArrayList)){
            billArrayList = new ArrayList<>();
            scanner.useDelimiter(CharacterConstant.COMMA);
            while (scanner.hasNext()) {
                billArrayList.add(Bill.builder().parse(scanner.nextLine().split(CharacterConstant.COMMA)).build());
            }
            scanner.close();
        }
    }
}
