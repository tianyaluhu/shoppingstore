package controller;

import entity.Admininfo;
import entity.Userinfo;
import mapper.AdmininfoMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceimpl.AdmininfoServiceImpl;
import serviceimpl.UserinfoServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UserinfoController {
    @Autowired
    UserinfoServiceImpl usi;

    @Autowired
    AdmininfoServiceImpl asi;

    @RequestMapping("/register")
    public int registerInsert(@RequestParam String username,@RequestParam String password,@RequestParam String email){
        Userinfo ui=new Userinfo();
        ui.setUsername(username);
        String md5Pwd= DigestUtils.md5Hex(password.getBytes());
        ui.setPassword(md5Pwd);
        ui.setEmail(email);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ui.setRegisterTime(sdf.format(date));
        Userinfo ui2=usi.selectByname(username);
        int index=0;
        if (ui2==null){
             index= usi.insert(ui);
        }
        return index;
    }

    /*自己写的，也可以运行成功
    @RequestMapping("/login")
    public int loginInsert(@RequestParam String username, @RequestParam String password, @RequestParam String brand, HttpServletRequest request, HttpServletResponse response){
        String md5Pwd= DigestUtils.md5Hex(password.getBytes());
        Userinfo ui2=usi.selectBynameAndPwd(username,md5Pwd);
        int index=0;
        if (ui2!=null){
            if (brand!=null){
                Cookie cookie=new Cookie("username",username+"&"+password);
                cookie.setPath("/");
                cookie.setMaxAge(60*60*5);
                response.addCookie(cookie);
            }else{
                Cookie cookie=new Cookie("user",username+"&");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        index=1;
        }
        return index;
    }*/
    @RequestMapping("/login")
    public String loginInsert(@RequestParam String username, @RequestParam String password, @RequestParam String flag, HttpServletResponse response){
        Userinfo ui=usi.selectByname(username);
        if(ui==null){
            return "empty";
        }else{
            if(ui.getPassword().equals(DigestUtils.md5Hex(password.getBytes()))){
                if (flag.equals("yes")){
                    Cookie cookie=new Cookie("flag","true");
                    cookie.setMaxAge(60*60*5);
                    response.addCookie(cookie);
                    Cookie cookie1=new Cookie("username",username);
                    cookie1.setMaxAge(60*60*5);
                    response.addCookie(cookie1);
                    Cookie cookie2=new Cookie("password",password);
                    cookie2.setMaxAge(60*60*5);
                    response.addCookie(cookie2);
                }else{
                    Cookie cookie=new Cookie("flag","false");
                    cookie.setMaxAge(60*60*5);
                    response.addCookie(cookie);
                }
                return "ok";
            }else{
                return "error";
            }
        }
    }


    @RequestMapping("/forgetPwd")
    public String loginforget(@RequestParam String username, @RequestParam String password1, @RequestParam String password2){
        Userinfo ui=usi.selectByname(username);
        if(ui==null){
            return "empty";
        }else{
            if (password1.equals(password2)) {
                Userinfo ui2 = new Userinfo();
                ui2.setuId(ui.getuId());
                String md5Pwd= DigestUtils.md5Hex(password1.getBytes());
                ui2.setPassword(md5Pwd);
                int index=usi.updateByPrimaryKeySelective(ui2);
                if (index>0){
                    return "ok";
                }

                    return "no";

            }else{
                return "error";
            }
        }
    }

    @RequestMapping("/admin")
    public String admin(@RequestParam String username, @RequestParam String password){
        System.out.println("测试代码更新");
        Admininfo ad=asi.selectByUsername(username);
        String md5Pwd= DigestUtils.md5Hex(password.getBytes());
        if(ad!=null) {
            if (md5Pwd.equals(ad.getAdPassword())){
                return "yes";
            }else{
                return "no";
            }
        }else{
            return "empty";
        }
    }


}
