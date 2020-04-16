package serviceimpl;

import entity.Productinfo;
import mapper.ProductinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProductinfoService;

import java.util.List;
@Service
public class ProductinfoServiceImpl implements ProductinfoService {
    @Autowired
    ProductinfoMapper pm;
    @Override
    public int deleteByPrimaryKey(Integer pId) {
        return pm.deleteByPrimaryKey(pId);
    }

    @Override
    public int insert(Productinfo record) {
        return pm.insert(record);
    }

    @Override
    public int insertSelective(Productinfo record) {
        return pm.insertSelective(record);
    }

    @Override
    public List<Productinfo> getPtype() {
        return pm.getPtype();
    }

    @Override
    public List<Productinfo> getProductByPtype(String pType) {
        return pm.getProductByPtype(pType);
    }

    @Override
    public Productinfo selectByPrimaryKey(Integer pId) {
        return pm.selectByPrimaryKey(pId);
    }

    @Override
    public int updateByPrimaryKeySelective(Productinfo record) {
        return pm.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Productinfo record) {
        return pm.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPName(Productinfo p) {
        return pm.updateByPName(p);
    }

    @Override
    public Productinfo selectByPname(String pName) {
        return pm.selectByPname(pName);
    }


}
