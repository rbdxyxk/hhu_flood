package cn.hhu.mapper;

import cn.hhu.Bean.ParaForecast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/25 21:31
 */

@Mapper
@Repository
public interface ParaForecastMapper {
    @Select("select distinct(ParamId),ParaName,IsOnLine,CalRegNM,A,DATT,B,EX,IM,KI,KG,CI,CG,CS,L,X,K,SM,WUM,WLM,WDM,C from  forcastflood.para_forecast where PMakeP<>'' and ParamId like 'SLHRES%' order by ParamId desc")
    List<ParaForecast> getParaForecastsPMakePNotNull();



}
