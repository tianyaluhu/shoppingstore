package controller;

import entity.Productinfo;
import entity.Userinfo;
import entity.WebInfo;
import mapper.WebInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import serviceimpl.ProductinfoServiceImpl;
import serviceimpl.UserinfoServiceImpl;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class shopCarController {
    @Autowired
    JedisPool jedisPool;

    @Autowired
    ProductinfoServiceImpl psi;

    @Autowired
    UserinfoServiceImpl usi;

    @Autowired
    WebInfoMapper wm;

    @RequestMapping("/addCar")
    public String addCar(@RequestParam String username, @RequestParam String pid) {

        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        //判断购物车里是否有商品
        if (map.isEmpty()) {
            map.put(pid, "1");
            String str1 = jedis.hmset(username, map);//存入redis里
            //判断是否添加成功
            if (str1.equals("OK")) {
                return "yes";
            } else {
                return "no";

            }

        } else {
            //true，表示购物车已经有添加的商品，只需要给商品数量加1即可
            if (map.containsKey(pid)) {
                map.put(pid, String.valueOf(Integer.parseInt(map.get(pid)) + 1));
                String str2 = jedis.hmset(username, map);//存入redis里

                if (str2.equals("OK")) {
                    return "yes";
                } else {
                    return "no";

                }

            } else {
                map.put(pid, "1");
                String str3 = jedis.hmset(username, map);//存入redis里

                if (str3.equals("OK")) {
                    return "yes";

                } else {
                    return "no";

                }

            }
        }


    }

    @RequestMapping("/getCar")
    public List<Productinfo> getCar(@RequestParam String username) {
        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        List<Productinfo> list = new ArrayList<>();
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {

                Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt(entry.getKey()));
                pi.setpNum(Integer.parseInt(entry.getValue()));
                list.add(pi);
            }
            return list;
        }


        return null;
    }


    @RequestMapping("/blurSum")
    public String blurSum(@RequestParam String username, @RequestParam String pid, @RequestParam String num) {
        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        if (!map.isEmpty()) {
            //获取对应商品数据库信息
            Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt(pid));
            //获取对应商品数量
            int pnum = pi.getpNum();
            //点击之后发送来的页面商品数量
            int num1 = Integer.parseInt(num);

            for (Map.Entry<String, String> entry : map.entrySet()) {

                if (pid.equals(entry.getKey())) {
                    if (num1 <= pnum && num1 > 0) {
                        map.put(pid, num);
                        jedis.hmset(username, map);//存入redis里
                        return "yes";
                    } else {
                        return "no";
                    }
                }

            }

        }
        return null;
    }


    @RequestMapping("/plusItemsNum")
    public String plusItemsNum(@RequestParam String pnum, @RequestParam String username, @RequestParam String pid) {
        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        if (!map.isEmpty()) {
            //获取对应商品数据库信息
            Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt(pid));
            //获取对应商品数量
            int num = pi.getpNum();
            //点击之后发送来的页面商品数量
            int pnum1 = Integer.parseInt(pnum);

            for (Map.Entry<String, String> entry : map.entrySet()) {

                if (pid.equals(entry.getKey())) {
                    if (pnum1 <= num && pnum1 > 0) {
                        map.put(pid, pnum);
                        jedis.hmset(username, map);//存入redis里
                        return "yes";
                    } else {
                        return "no";
                    }
                }

            }

        }
        return null;
    }


    @RequestMapping("/reduceItemsNum")
    public String reduceItemsNum(@RequestParam String pnum, @RequestParam String username, @RequestParam String pid) {
        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        if (!map.isEmpty()) {
            //获取对应商品数据库信息
            Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt(pid));
            //获取对应商品数量
            int num = pi.getpNum();
            //点击之后发送来的页面商品数量
            int pnum1 = Integer.parseInt(pnum);
            for (Map.Entry<String, String> entry : map.entrySet()) {

                if (pid.equals(entry.getKey())) {
                    if (pnum1 <= num && pnum1 > 0) {
                        map.put(pid, pnum);
                        jedis.hmset(username, map);//存入redis里
                        return "yes";
                    } else {
                        return "no";
                    }
                }

            }

        }
        return null;
    }


    @RequestMapping("/removeItems")
    public void removeItems(@RequestParam String username, @RequestParam String pid) {
        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {

                if (pid.equals(entry.getKey())) {
                    jedis.hdel(username, pid);
                }

            }

        }
    }

    @RequestMapping("/sendEmail")
    public String sendEmail(@RequestParam String username, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Userinfo ui = usi.selectByname(username);
        int randomNum = (int) ((Math.random() * 9 + 1) * 100000);
        sendEmail(ui.getEmail(), randomNum, req, resp);
        return String.valueOf(randomNum);
    }


    public void sendEmail(String emailCount, int randomNum, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String from = "2601842711@qq.com";//你自己的邮箱
        String host = "smtp.qq.com";//本机地址
        //Properties可以确定系统的属性,就是为了寻找我们的host
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");//开启代理

        Authenticator aut = new Authenticator() {//创建Authenticator内部类来填入代理的用户名前缀和密码

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("2601842711", "qtiddxykosgydjic");//填用户名和代理密码

            }

        };

        //创建Session会话,Session是java.mail API最高入口
        Session session = Session.getDefaultInstance(properties, aut);
        //MimeMessage获取session对象,就可以创建要发送的邮箱内容和标题
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));//设置你自己的邮箱
            message.addRecipients(Message.RecipientType.TO, emailCount);//设置接受邮箱
            message.setSubject("验证码");//设置邮箱标题
            message.setText("您本次的验证码是:" + randomNum);//邮箱内容
            Transport.send(message);//发送邮箱

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/deleteProductNum")
    public String deleteProductNum(@RequestParam String username, @RequestParam String pid, @RequestParam String pnum) {
        //获取redis数据库
        Jedis jedis = jedisPool.getResource();
        //获取username之前缓存的购物车里的所有商品数据
        Map<String, String> map = jedis.hgetAll(username);
        Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt(pid));
        int num = pi.getpNum();
        int pnum1 = Integer.parseInt(pnum);
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (pid.equals(entry.getKey())) {
                    //删除购物车产品
                    int num1 = num - pnum1;
                    if (num1 >= 0) {
                        jedis.hdel(username, pid);
                        Productinfo pi1 = new Productinfo();
                        pi1.setpId(Integer.parseInt(pid));
                        pi1.setpNum(num1);
                        int i = psi.updateByPrimaryKeySelective(pi1);
                        return "yes";
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping("/getWebData")
    public List<WebInfo> getWebData() {
        return wm.selectAll();
    }
}
