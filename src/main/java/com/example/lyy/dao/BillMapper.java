package com.example.lyy.dao;


import com.example.lyy.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillMapper {
    public List<Bill> getBillByName(String name);
//    商品名称+供应商id+是否付款
    public List<Bill> getBillByCondition(@Param("productName") String productName, @Param("billCode") String billCode);
    public List<Bill> getBillListInfo(Bill bill);
    public List<Bill> findBills();
    public int deleteBill(int id);
    //    map类型
    public List<Bill> findBillByMap(Map<String, Object> condition);
    public int updateBill(Bill bill);
    public int addBill(Bill bill);
}
