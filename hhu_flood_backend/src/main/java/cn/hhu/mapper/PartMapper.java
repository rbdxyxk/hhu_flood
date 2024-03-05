package cn.hhu.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PartMapper {
    @Select("select CalRegNM from forcastflood.para_forecast where ParaName=#{Para_Name}")
    List <String> select_part(String Para_Name);
}
