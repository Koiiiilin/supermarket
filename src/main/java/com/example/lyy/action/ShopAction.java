package com.example.lyy.action;

import com.example.lyy.dao.BookMapper;
import com.example.lyy.dao.ShopMapper;
import com.example.lyy.pojo.*;
import com.example.lyy.type.UserToken;
import com.example.lyy.utils.SaltUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopAction {
    @Resource
    ShopMapper shopMapper;
    @Resource
    BookMapper bookMapper;
    @RequestMapping("/shop_Register")
    public String Register(@RequestParam(value = "name") String name,
                        @RequestParam(value = "pwd") String pwd){
        System.out.println("-------用户注册---------");
        System.out.println(name+"------"+pwd);
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        Shopper shopper=new Shopper();
        shopper.setPwd(pwd);
        shopper.setName(name);
        //2.将随机盐保存到数据
        shopper.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(pwd,salt,1024);
        shopper.setPwd(md5Hash.toHex());
        System.out.println("密码： "+md5Hash.toHex());
        shopMapper.registerShop(shopper);
        return "redirect:/userLogin";
    }

    @RequestMapping("/shoplogout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/userLogin";
    }

    @RequestMapping("/bookList")
    public String getallBook(HttpSession session,@RequestParam(value = "pn",defaultValue="1") Integer pageNow,
                             @RequestParam(required=false,defaultValue="4") Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Book> books = bookMapper.findAllProduct();
        PageInfo<Book> pageInfo = new PageInfo<Book>(books,4);
        session.setAttribute("pageInfo", pageInfo);
        session.setAttribute("books",books);
        return "shopIndex";
    }
    // 利用shiro完成登录
    @RequestMapping("/shoplogin")
    public String shoplogin(@RequestParam(value = "name") String name,
                            @RequestParam(value = "pwd") String pwd,
                            HttpSession session) {
        try {
            //获取主体对象
            Subject subject = SecurityUtils.getSubject();
            System.out.println(name+" "+pwd);
            subject.login(new UserToken(name, pwd,"Shop"));
            return "forward:/shop/bookList";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        session.setAttribute("error","用户名或密码不正确");
        return "redirect:/userLogin";
    }
    @RequestMapping("/addTocart")
    public String addToCart(@RequestParam(value = "id")int id,HttpSession session) throws ParseException {
        OrderItem orderItem = new OrderItem();
        Book book = bookMapper.findBookByid(id);
        Order order = new Order();
        order.setStatus(0);
        order.setPrice(book.getPrice());
        order.setCreateTime(new Date());
        System.out.println(order.getCreateTime());
        Shopper shopInfo = (Shopper) session.getAttribute("shopInfo");
        order.setUserId(shopInfo.getId());
        order.setOrderId(String.format("Order" + new Date()));
        bookMapper.addOrder(order);
        orderItem.setName(book.getName());
        orderItem.setPrice(book.getPrice());
        orderItem.setCount(1);
        orderItem.setTotalPrice(book.getPrice());
        orderItem.setOrderId(order.getOrderId());
        bookMapper.addTocart(orderItem);
        return "forward:/shop/bookList";
    }
    @RequestMapping("/getOrder")
    public String getOrder(HttpSession session,Model model,@RequestParam(value = "pn",defaultValue="1") Integer pageNow,
                           @RequestParam(required=false,defaultValue="4") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        Shopper shopInfo = (Shopper) session.getAttribute("shopInfo");
        List<Order> orderbyUid = bookMapper.findOrderbyUid(shopInfo.getId());
        System.out.println(orderbyUid.get(0).getCreateTime());
        PageInfo<Order> pageInfo = new PageInfo<Order>(orderbyUid,4);
        session.setAttribute("pageInfo", pageInfo);
        model.addAttribute("orders",orderbyUid);
        return "order";
    }
    @RequestMapping("/info")
    public String info(@RequestParam(value = "id")String id,Model model,@RequestParam(value = "pn",defaultValue="1") Integer pageNow,
                       @RequestParam(required=false,defaultValue="4") Integer pageSize,HttpSession session){
        PageHelper.startPage(pageNow, pageSize);
        List<OrderItem> items = bookMapper.findOrderItembyid(id);
        PageInfo<OrderItem> pageInfo = new PageInfo<OrderItem>(items,4);
        session.setAttribute("pageInfo", pageInfo);
        model.addAttribute("items",items);
        return "detail";
    }



}
