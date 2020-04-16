package serviceimpl;

import entity.Orderdetail;
import mapper.OrderdetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrderdetailService;

import java.util.List;

@Service
public class OrderdetailServiceImpl implements OrderdetailService {

    @Autowired
    OrderdetailMapper om;
    @Override
    public int deleteByPrimaryKey(Integer odId) {
        return om.deleteByPrimaryKey(odId);
    }

    @Override
    public int insert(Orderdetail record) {
        return om.insert(record);
    }

    @Override
    public int insertSelective(Orderdetail record) {
        return om.insertSelective(record);
    }

    @Override
    public Orderdetail selectByPrimaryKey(Integer odId) {
        return om.selectByPrimaryKey(odId);
    }

    @Override
    public int updateByPrimaryKeySelective(Orderdetail record) {
        return om.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orderdetail record) {
        return om.updateByPrimaryKey(record);
    }

    @Override
    public List<Orderdetail> selectAll() {
        return om.selectAll();
    }
}
