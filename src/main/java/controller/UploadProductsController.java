package controller;

import entity.Productinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import serviceimpl.ProductinfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class UploadProductsController {
    @Autowired
    ProductinfoServiceImpl pisi;

    @RequestMapping("/addProducts")
    public String addProduction(@RequestParam String p_code, @RequestParam String p_name, @RequestParam String p_type, @RequestParam String brand,
                                @RequestParam String p_num, @RequestParam String price, @RequestParam String intro,  @RequestParam(value = "pic") MultipartFile multipartFile, HttpServletRequest req) throws IOException {
        //localhost:8080/shoppingstore/image/a.jsp
        String originalFilename=multipartFile.getOriginalFilename();//获取图片原始名称
        Long l=System.currentTimeMillis();//获取毫秒数
        String newName=l+originalFilename;
        String lastName="/image/"+newName;//最终存储在数据库中的值(pic)的名字
        String realPath=req.getSession().getServletContext().getRealPath("/");//获取项目名+/
        InputStream inputStream=multipartFile.getInputStream();//输入流，用来读取图片

        //在项目根目录下创建image文件夹，用来保存图片
        File file=new File(realPath+"\\image\\"+newName);

        if(!file.exists()){
            file.getParentFile().mkdir();//如果这个文件夹不存在，创建文件夹
        }

        if(!multipartFile.isEmpty()) {
            InputStream isr = new BufferedInputStream(inputStream);//字节输入流转缓冲输入流,
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));//字节输出流转缓冲输出流
            int str = 0;
            while ((str = isr.read()) != -1) {
                os.write(str);
            }
            os.flush();
            os.close();
            isr.close();

        }
            Productinfo p = new Productinfo();
            p.setpCode(Integer.parseInt(p_code));
            p.setpName(p_name);
            p.setpType(p_type);
            p.setBrand(brand);
            p.setpNum(Integer.parseInt(p_num));
            p.setPrice(Double.parseDouble(price));
            p.setIntro(intro);
            p.setStatus(1);
            p.setPic(lastName);
            int i=pisi.insert(p);
            if (i>0) {
                return "yes";
            }else{
                return "no";
            }
        }


}
