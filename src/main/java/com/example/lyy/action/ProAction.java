package com.example.lyy.action;

import com.example.lyy.dao.ProviderMapper;
import com.example.lyy.dao.UserMapper;
import com.example.lyy.pojo.Provider;
import com.example.lyy.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/pro")
public class ProAction {
    @Resource
    ProviderMapper providerMapper;
    @RequestMapping("/findPros")
    public String findPros(Model model,
                                     @RequestParam(value = "pn",defaultValue="1") Integer pageNow,
                                     @RequestParam(required=false,defaultValue="5") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        List<Provider> pros = providerMapper.getProviderList();
        PageInfo<Provider> pageInfo = new PageInfo<Provider>(pros,5);
        model.addAttribute("providers",pros);
        model.addAttribute("pageInfo", pageInfo);
        return "providerList";
    }
    @RequestMapping("/del_pro")
    public String del_pro(@RequestParam(value = "id") int id){
        System.out.println("正在删除供应商信息："+id);
        providerMapper.deletePro(id);
        return "frame";
    }
    @RequestMapping("/update_pro")
    public String update_pro(@RequestParam(value = "id") int id,
                         @RequestParam(value = "proCode") String proCode,
                         @RequestParam(value = "proName") String proName,
                         @RequestParam(value = "proContact") String proContact,
                         @RequestParam(value = "proPhone") String proPhone,
                         @RequestParam(value = "proAddress") String proAddress,
                             @RequestParam(value = "proFax") String proFax){
        System.out.println("修改"+proCode+"---"+proName);
        Provider provider = new Provider();
        provider.setId(id);
        provider.setProAddress(proAddress);
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        providerMapper.updateProvider(provider);
        return "redirect:/pro/findPros";

    }
    @RequestMapping("/sendToAdd")
    public String sendToAdd(){
        return "providerAdd";
    }
    @RequestMapping("/add_pro")
    public String add_pro(@RequestParam(value = "proCode") String proCode,
                          @RequestParam(value = "proName") String proName,
                          @RequestParam(value = "proContact") String proContact,
                          @RequestParam(value = "proPhone") String proPhone,
                          @RequestParam(value = "proAddress") String proAddress,
                          @RequestParam(value = "proFax") String proFax){
        System.out.println("添加："+proCode+"---"+proName);
        Provider provider = new Provider();
        provider.setProAddress(proAddress);
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        providerMapper.addProvider(provider);
        return "redirect:/pro/findPros";

    }
    @RequestMapping("/pro_byadd")
    public String pro_byadd(Model model,@RequestParam(value = "proCode") String proCode
                            ,@RequestParam(value = "proName") String proName){
        System.out.println("查询"+proCode+"-------"+proName);
        List<Provider> providerByCondition = providerMapper.getProviderByCondition(proCode, proName);
        PageHelper.startPage(1, 5);
        PageInfo<Provider> pageInfo = new PageInfo<Provider>(providerByCondition,5);
        model.addAttribute("providers", providerByCondition);
        model.addAttribute("pageInfo", pageInfo);
        return "providerList";
    }
}
