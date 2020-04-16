package mapper;

import entity.Orderdetail;

import java.util.List;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Integer odId);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer odId);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);

    List<Orderdetail> selectAll();
}