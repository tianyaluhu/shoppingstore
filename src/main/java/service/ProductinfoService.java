package service;

import entity.Productinfo;

import java.util.List;

public interface ProductinfoService {

    int deleteByPrimaryKey(Integer pId);

    int insert(Productinfo record);

    int insertSelective(Productinfo record);

    List<Productinfo> getPtype();

    List<Productinfo> getProductByPtype(String pType);

    Productinfo selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Productinfo record);

    int updateByPrimaryKey(Productinfo record);

    int updateByPName(Productinfo p);

    Productinfo selectByPname(String pName);
}
