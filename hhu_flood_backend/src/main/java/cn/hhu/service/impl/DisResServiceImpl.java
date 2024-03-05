package cn.hhu.service.impl;

import cn.hhu.Bean.DisResFlood;
import cn.hhu.mode.slhPublicClass;
import cn.hhu.service.DisResService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DisResServiceImpl implements DisResService {

    @Override
    public DisResFlood CalDis(Float p1, Float p2, Float p3, Float p4, Float p5) {

        slhPublicClass lineInsert = new slhPublicClass();
        DisResFlood result = new DisResFlood();
        double[] southGate = new double[6]; double[] northGate = new double[6];
        double Flowsum =p1+p2-p4;
        Flowsum = Flowsum * p5 * 0.36;//计算时长的出入库水量,单位为：万立方米.(m3/s*小时*3600)/10000
        double ResV = lineInsert.sglLineInsertV("51112101",p3);
        if(ResV==-1||ResV==-2){
            result.setWater(-1F);
            return result;
        }
        ResV = ResV + Flowsum;//初始蓄量+时段内的出入库水量
        double ResZI = lineInsert.sglLineInsertZI("51112101", ResV);
        result.setWater((float) ResZI);
//        Label6.Text=TextInput5.Text.ToString() + "时后后库水位(m)";
//        Label9.Text = "建议" + TextInput5.Text.ToString() + "时后各水闸泄洪流量";
        if (ResZI <= 23.5)
        {
            result.setNorth(0.0F);
            result.setSouth(0.0F);
        }
        if (ResZI > 23.5 && ResZI <= 24.0)
        {
            result.setNorth(0.0F);
            result.setSouth(2500.0F);
        }
        if (ResZI > 24.0 && ResZI<=26.08)
        {
            result.setNorth(2500.0F);
            result.setSouth(2500.0F);
        }
        if (ResZI > 26.08 && ResZI <= 26.81)
        {
            result.setNorth(2500.0F);
            result.setSouth(3500.0F);
        }
        if (ResZI > 26.81 && ResZI <= 27.95)
        {
            result.setNorth(3000.0F);
            result.setSouth(4000.0F);

        }
        if (ResZI > 27.95)
        {
            result.setNorth(5000.0F);
            result.setSouth(5131.0F);
        }
        return result;
    }
}
