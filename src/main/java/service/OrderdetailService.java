package service;

import entity.Orderdetail;

import java.util.List;

public interface OrderdetailService {
    int deleteByPrimaryKey(Integer odId);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer odId);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);

    List<Orderdetail> selectAll();
}
