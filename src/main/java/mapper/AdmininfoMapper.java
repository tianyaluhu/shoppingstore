package mapper;

import entity.Admininfo;

public interface AdmininfoMapper {
    int deleteByPrimaryKey(Integer adId);

    int insert(Admininfo record);

    int insertSelective(Admininfo record);

    Admininfo selectByPrimaryKey(Integer adId);

    Admininfo selectByUsername(String adName);

    int updateByPrimaryKeySelective(Admininfo record);

    int updateByPrimaryKey(Admininfo record);
}