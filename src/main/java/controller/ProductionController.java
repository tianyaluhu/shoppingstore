package controller;

import entity.Orderdetail;
import entity.Productinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceimpl.OrderdetailServiceImpl;
import serviceimpl.ProductinfoServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductionController {

    @Autowired
    ProductinfoServiceImpl pisi;

    @Autowired
    OrderdetailServiceImpl odsi;

    @RequestMapping("/getPtype")
    public List<Productinfo> getPtype(){
        return pisi.getPtype();
    }

    @RequestMapping("/getProductByPtype")
    public List<Productinfo> getProductByPtype(@RequestParam String pType){
        return pisi.getProductByPtype(pType);
    }



    @RequestMapping("/updateProducts")
    public String updateProducts(@RequestParam String pName, @RequestParam String pNum,@RequestParam String price,@RequestParam String sale){
        Productinfo pi=pisi.selectByPname(pName);
        if(pi!=null) {
            Productinfo p = new Productinfo();
            p.setpId(pi.getpId());
            p.setpName(pName);
            p.setpNum(Integer.parseInt(pNum));
            p.setPrice(Double.parseDouble(price));
            p.setSale(Double.parseDouble(sale));
            int i=pisi.updateByPrimaryKeySelective(p);
            if (i>0) {
                return "yes";
            }else{
                return "no";
            }
        }else{
            return "error";
        }

    }


    //商品上下架获取商品信息
    @RequestMapping("/takeoff")
    public Map<String,List<Productinfo>> getProductsByptype() {
        List<Productinfo> list=pisi.getPtype();
        Map<String,List<Productinfo>> map=new HashMap<>();
        for (int i=0;i<list.size();i++){
            String pType=list.get(i).getpType();
            List<Productinfo> list1=pisi.getProductByPtype(pType);
            map.put(pType,list1);
        }
        return map;
    }


    @RequestMapping("/down")
    public void down(@RequestParam String pName){
        Productinfo p=new Productinfo();
        p.setpName(pName);
        p.setStatus(1);
        pisi.updateByPName(p);
    }

    @RequestMapping("/up")
    public void up(@RequestParam String pName){
        Productinfo p=new Productinfo();
        p.setpName(pName);
        p.setStatus(0);
        pisi.updateByPName(p);
    }

    @RequestMapping("/unDeliverOrders")
    public List<Orderdetail> unDeliverOrders(){
       return odsi.selectAll();
    }

    @RequestMapping("/deleteOrder")
    public int deleteOrder(@RequestParam Integer odId){
        Orderdetail od=new Orderdetail();
        od.setOdId(odId);
        od.setStatus(1);
        return odsi.updateByPrimaryKeySelective(od);
    }


    @RequestMapping("/backDeliverOrders")
    public List<Orderdetail> backDeliverOrders(){
        return odsi.selectAll();
    }
}
