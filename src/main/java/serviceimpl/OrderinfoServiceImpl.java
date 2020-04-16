package serviceimpl;

import entity.Orderinfo;
import mapper.OrderinfoMapper;
import mapper.OrderinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrderinfoService;
@Service
public class OrderinfoServiceImpl implements OrderinfoService {

    @Autowired
    OrderinfoMapper om;


    @Override
    public int deleteByPrimaryKey(String username) {
        return om.deleteByPrimaryKey(username);
    }

    @Override
    public int insert(Orderinfo record) {
        return om.insert(record);
    }

    @Override
    public int insertSelective(Orderinfo record) {
        return om.insertSelective(record);
    }

    @Override
    public Orderinfo selectByUsername(String username) {
        return om.selectByUsername(username);
    }
}
