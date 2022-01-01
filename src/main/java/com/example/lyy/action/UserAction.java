package com.example.lyy.action;

import com.example.lyy.dao.UserMapper;
import com.example.lyy.pojo.User;
import com.example.lyy.service.IMailService;
import com.example.lyy.type.UserToken;
import com.example.lyy.utils.SaltUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserAction {
    @Resource
    UserMapper userMapper;
    @Resource
    private IMailService mailService;

// 传统登录
    @RequestMapping("/dologin")
    public String login(@RequestParam(value = "userName") String name,
            @RequestParam(value = "password") String pwd, HttpSession session){
        System.out.println("---------- "+name+" ------ "+pwd);
        User u = userMapper.findUserByNameAndPWD(name,pwd);
        if (u != null){
            session.setAttribute("userinfo", u);
            return "frame";
        }
        session.setAttribute("error","用户名或密码不正确");
        return "login";
    }


// 利用shiro完成登录
    @RequestMapping("/userlogin")
    public String userlogin(@RequestParam(value = "userName") String name,
                        @RequestParam(value = "password") String pwd,
                            HttpSession session) {
        try {
            Subject subject = SecurityUtils.getSubject();
            System.out.println(name+" "+pwd);
            UserToken token = new UserToken(name, pwd,"Custom");
            subject.login(token);
            return "redirect:/frame";
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
        return "redirect:/";
    }

//    普通退出
    @RequestMapping("/quit")
    public String quitUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("userinfo");
        System.out.println("-------用户退出---------");
        return "/login";
    }
// shiro退出
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    @RequestMapping("/findAllByPage")
    public String findAllUsersByPage(Model model,
                                     @RequestParam(value = "pn",defaultValue="1") Integer pageNow,
                                     @RequestParam(required=false,defaultValue="5") Integer pageSize){
        PageHelper.startPage(pageNow, pageSize);
        List<User> users = userMapper.getUserList();
        System.out.println(users.size());
        PageInfo<User> pageInfo = new PageInfo<User>(users,5);
        List<Integer> roleList=userMapper.getUserRole();
        model.addAttribute("users", users);
        model.addAttribute("roleList",roleList);
        model.addAttribute("pageInfo", pageInfo);
        return "userlist";
    }
    @RequestMapping("/user_update")
    public String update(@RequestParam(value = "id") int id,
            @RequestParam(value = "userCode") String userCode,
                      @RequestParam(value = "userName") String userName,
                      @RequestParam(value = "userPassword") String userPassword,
                          @RequestParam(value = "phone") String phone,
                          @RequestParam(value = "address") String address){
        System.out.println("修改"+userCode+"---"+userName);
        User user = new User();
        user.setId(id);
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setUserPassword(userPassword);
        userMapper.updateUser(user);
        return "frame";

    }
    @RequestMapping("/add_user")
    public String addUser(@RequestParam(value = "userCode") String userCode,
                         @RequestParam(value = "userName") String userName,
                         @RequestParam(value = "userPassword") String userPassword,
                         @RequestParam(value = "phone") String phone,
                         @RequestParam(value = "address") String address,
                            @RequestParam(value ="createBy") int createBy,
                          @RequestParam(value = "gender")int gender,
                          @RequestParam(value = "userRole") int userRole){
        System.out.println("添加："+userCode+"---"+userName);
        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setUserPassword(userPassword);
        user.setCreatedBy(createBy);
        user.setGender(gender);
        user.setUserRole(userRole);
        userMapper.addUser(user);
        return "frame";

    }
    @RequestMapping("sendToChange")
    public String sendToChange(){
        return "password";
    }
    @RequestMapping("/change_pwd")
    public String change_pwd(@RequestParam(value = "id") int id,
                             @RequestParam(value = "password") String password, HttpSession session){
        System.out.println("修改用户密码： "+id+" 新密码： "+password);
        //处理业务调用dao
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        User user = userMapper.findUserById(id);
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        user.setUserPassword(md5Hash.toHex());
        System.out.println("新密码： "+md5Hash.toHex());
        userMapper.updateUser(user);
        session.setAttribute("error","用户信息修改，请重新登录");
        return "redirect:/";

    }
    @RequestMapping("/del_user")
    public String del_user(@RequestParam(value = "id") int id){
        System.out.println("正在删除用户信息："+id);
        userMapper.deleteUser(id);
        return "frame";
    }
    @RequestMapping("/findByAdd")
    public String findByAdd(Model model,@RequestParam(value = "queryname") String queryname){
        List<User> userByAdd = userMapper.findUserByAdd(queryname);
        PageHelper.startPage(1, 5);
        PageInfo<User> pageInfo = new PageInfo<User>(userByAdd,5);
        List<Integer> roleList=userMapper.getUserRole();
        model.addAttribute("users", userByAdd);
        model.addAttribute("roleList",roleList);
        model.addAttribute("pageInfo", pageInfo);
        return "userlist";
    }


    @RequestMapping("/sendEmail")
    public String sendEmail( @RequestParam(value = "theme") String theme,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "content") String content){
            mailService.sendSimpleMail(email,theme,content);
            return "frame";
    }

}
