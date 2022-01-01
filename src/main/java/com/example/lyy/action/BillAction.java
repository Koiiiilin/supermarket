package com.example.lyy.action;


import com.example.lyy.dao.BillMapper;
import com.example.lyy.dao.ProviderMapper;
import com.example.lyy.pojo.Bill;
import com.example.lyy.pojo.Provider;
import com.example.lyy.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillAction {
    @Resource
    BillMapper billMapper;
    @Resource
    ProviderMapper providerMapper;

    @RequestMapping("/findBills")
    public String findPros(Model model,
                           @RequestParam(value = "pn",defaultValue="1") Integer pageNow,
                           @RequestParam(required=false,defaultValue="5") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        List<Bill> bills = billMapper.findBills();
        PageInfo<Bill> pageInfo = new PageInfo<Bill>(bills,5);
//        System.out.println(bills.get(0).getProvider());
//        System.out.println(bills.get(0).getProductDesc());
        System.out.println(bills.get(0).getId());
        model.addAttribute("bills",bills);
        model.addAttribute("pageInfo", pageInfo);
        return "billlist";
    }
    @RequestMapping("/del_bill")
    public String del_bill(@RequestParam(value = "id") int id){
        System.out.println("正在删除订单信息："+id);
        billMapper.deleteBill(id);
        return "redirect:/bill/findBills";
    }

    @RequestMapping("/update_bill")
    public String update_bill(@RequestParam(value = "id") int id,
                             @RequestParam(value = "billCode") String billCode,
                             @RequestParam(value = "productName") String productName,
                             @RequestParam(value = "productDesc") String productDesc,
                             @RequestParam(value = "productCount") BigDecimal  productCount,
                             @RequestParam(value = "totalPrice") BigDecimal totalPrice,
                             @RequestParam(value = "isPayment") int isPayment,
                              @RequestParam(value = "proName") String proName){
        System.out.println("修改"+billCode+"---"+productName);
        Bill bill = new Bill();
        bill.setId(id);
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setIsPayment(isPayment);
        List<Provider> provider = providerMapper.getProviderByCondition("", proName);
        bill.setProviderId(provider.get(0).getId());
        billMapper.updateBill(bill);
        return "redirect:/bill/findBills";
    }
    @RequestMapping("/sendToAdd")
    public String sendToAdd(){

        return "billadd";
    }
    @RequestMapping("/addBill")
    public String addBill(@RequestParam(value = "billCode") String billCode,
                          @RequestParam(value = "productName") String productName,
                          @RequestParam(value = "productDesc") String productDesc,
                          @RequestParam(value = "productCount") BigDecimal  productCount,
                          @RequestParam(value = "totalPrice") BigDecimal totalPrice,
                          @RequestParam(value = "isPayment") int isPayment,
                          @RequestParam(value = "proName") String proName){
        System.out.println("新增"+billCode+"---"+productName);
        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setIsPayment(isPayment);
        List<Provider> provider = providerMapper.getProviderByCondition("", proName);
        bill.setProviderId(provider.get(0).getId());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-3-21");
            bill.setCreationDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        billMapper.addBill(bill);
        return "redirect:/bill/findBills";
    }
    @RequestMapping("/findByAdd")
    public String findByAdd(Model model,@RequestParam(value = "billCode") String billCode,
                            @RequestParam(value = "productName")String productName){
        List<Bill> billByCondition = billMapper.getBillByCondition(productName, billCode);
        PageHelper.startPage(1, 5);
        PageInfo<Bill> pageInfo = new PageInfo<Bill>(billByCondition,5);
        model.addAttribute("bills", billByCondition);
        model.addAttribute("pageInfo", pageInfo);
        return "billlist";
    }
}
