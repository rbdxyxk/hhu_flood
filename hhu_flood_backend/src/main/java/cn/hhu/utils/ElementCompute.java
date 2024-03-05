package cn.hhu.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author tlj
 */
public class ElementCompute {
    public static int getElementNumber(int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double x , Double y){

            System.out.println(timeInterval+":"+latitudeL+":"+x+":"+y);
            Double longitudeGap = longitudeH - longitudeL;
            Double latitudeGap = latitudeH - latitudeL;
            x = x -longitudeL;
            y = y - latitudeL;
            Double elementXL =longitudeGap/40;
            Double elementYL = latitudeGap/40;
            int numX = (int) (x/elementXL);
            int numY = (int) (y/elementYL);
            int element = (numY-1)*40+numX+1;
            System.out.println(numX+":"+numY+":"+element);
            return element;

    }
    public static int[] getElements(int timeInterval , Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double x , Double y){

        System.out.println(timeInterval+":"+latitudeL+":"+x+":"+y);
        Double longitudeGap = longitudeH - longitudeL;
        Double latitudeGap = latitudeH - latitudeL;
        x = x -longitudeL;
        y = y - latitudeL;
        Double elementXL =longitudeGap/40;
        Double elementYL = latitudeGap/40;
        int numX = (int) (x/elementXL);
        int numY = (int) (y/elementYL);
        //int element = (numX+1)*(numY+1);
        //System.out.println(numX+":"+numY+":"+element);
        int[] elements = {numX+1,numY+1};
        return elements;

    }

    public static int[] getElements(int timeInterval , String positions, Double x , Double y,int xSize, int ySize){

        String[] split = positions.split(",");
        Double[] position = new Double[8];
        int p = 0;
        for (int i = 0; i < split.length&&p<position.length; i++) {
            String temp = split[i];
            if(!"".equals(temp)){
                position[p++] = Double.parseDouble(temp);
            }
        }
        List<Integer> list = new LinkedList<>();
//        System.out.println(position[0]);
        double longitudeX1 = position[0];
        double latitudeX1 = position[1];
        double longitudeX2 = position[2] ;
        double latitudeX2 = position[3] ;
        double longitudeX3 = position[4];
        double latitudeX3 = position[5];
        double longitudeX4 = position[6];
        double latitudeX4 = 34.604079008746346;

//        System.out.println(position[6]);

        //求线的方程
        double k1 = (latitudeX1-latitudeX2)/(longitudeX1-longitudeX2);
        double k2 = (latitudeX3-latitudeX2)/(longitudeX3-longitudeX2);
        double b1 =latitudeX1-k1*longitudeX1;
        double b2 =latitudeX3-k2*longitudeX3;
        double b3 =latitudeX3-k1*longitudeX3;
        double b4 =latitudeX1-k2*longitudeX1;
        if(((y-b1)/k1)> x){

            return null;
        }else if(((y-b3)/k1)< x){

            return null;
        }else if((k2*x+b4)<y){

            return null;
        }else if((k2*x+b2)>y){

            return null;
        }

        //计算点导线的距离
        double Gap1 = Math.abs(k1*x-y+b1)/Math.sqrt(1+Math.pow(k1,2));
        double Gap2 = Math.abs(k2*x-y+b2)/Math.sqrt(1+Math.pow(k2,2));

        //计算每个单元的长宽
        double line1 =Math.sqrt(Math.pow(longitudeX1-longitudeX2,2)+Math.pow(latitudeX1-latitudeX2,2));
        double line2 =Math.sqrt(Math.pow(longitudeX3-longitudeX2,2)+Math.pow(latitudeX3-latitudeX2,2));
        double xGap = line2/xSize;
        double yGap = line1/ySize;
        //计算单元
        int xNum =(int)(Gap1/xGap)+1;
        int yNum =(int)(Gap2/yGap)+1;

        int[] elements = {xNum,yNum};
        return elements;

    }

    //@Test
    public static List<Integer> computeElements( Double latitudeL , Double longitudeL , Double latitudeH, Double longitudeH, Double x0 , Double y0 , Double x1 , Double y1,int xSize, int ySize) {
        //存储
        Set<Integer> set = new HashSet<>();
        //List<Integer> list = new LinkedList<>();
        if(longitudeL>longitudeH||latitudeL>latitudeL){
            //限制条件
            return null;
        }
        Double longitudeGap = longitudeH - longitudeL;
        Double latitudeGap = latitudeH - latitudeL;
        x0 = x0 - longitudeL;
        y0 = y0 - latitudeL;
        x1 = x1 - longitudeL;
        y1 = y1 - latitudeL;
        Double k = (y1-y0)/(x1-x0);
        Double b = y0-k*x0;

        //单元宽度
        Double elementXL =longitudeGap/xSize;
        //单元高度
        Double elementYL = latitudeGap/ySize;
        //初始单元X,Y
        int numX0 = (int) (x0/elementXL)+1;
        int numY0 = (int) (y0/elementYL)+1;
        int numX1 = (int) (x1/elementXL)+1;
        int numY1 = (int) (y1/elementYL)+1;
        Double y = 0.0 ;



        //int last =  0;
        set.add((numY0-1)*xSize+numX0);
        set.add((numY1-1)*xSize+numX1);
        if(numX1>numX0){
            int pre = numY0 ;
            int last = 0 ;
            //类型1
            Double x = numX0*elementXL;
            if(k>0){
                for (int i = numX0 ; i < numX1; i++) {

                    y=k*x+b;
                    last=(int)(y/elementYL)+1;
                    while(pre<=last){
                        set.add((pre-1)*40+i);
                        pre++;
                    }
                    //需要回复一位
                    pre--;
                    x+=elementYL;

                }
                while(pre<=numY1){
                    set.add((pre-1)*40+numX1);
                    pre++;
                }
            }else{
                for (int i = numX0 ; i < numX1; i++) {

                    y=k*x+b;
                    last=(int)(y/elementYL)+1;
                    while(pre>=last){
                        set.add((pre-1)*40+i);
                        pre--;
                    }
                    //需要回复一位
                    pre++;
                    x+=elementXL;

                }
                while(pre>=numY1){
                    set.add((pre-1)*40+numX1);
                    pre--;
                }
            }


        }else {
            int pre = numY1 ;
            Double x = numX1*elementXL;
            if(k>0){
                for (int i = numX1 ; i < numX0 ; i++) {
                    y=k*x+b;
                    numY1=(int)(y/elementYL)+1;
                    while(pre<=numY1){
                        set.add((pre-1)*40+i);
                        pre++;
                    }
                    //需要回复一位
                    pre--;
                    x+=elementXL;
                }
                while(pre<=numY0){
                    set.add((pre-1)*40+numX0);
                    pre++;
                }
            }else{
                for (int i = numX1 ; i < numX0 ; i++) {
                    y=k*x+b;
                    numY1=(int)(y/elementYL)+1;
                    while(pre<=numY1){
                        set.add((pre-1)*40+i);
                        pre++;
                    }
                    //需要回复一位
                    pre--;
                    x+=elementXL;
                }
                while(pre<=numY0){
                    set.add((pre-1)*40+numX0);
                    pre++;
                }
            }

        }
        List<Integer>  list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    /*
    * BigDecimal 求开根号 来自csdn
    * */
    public static BigDecimal sqrt(BigDecimal value, int scale) {
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;

    }

    public static List<Integer>  computeElements2(String positions,Double startX,Double startY,Double endX,Double endY,int xSize,int ySize){
        List<Integer> list = null;
        try {
            System.out.println("开始直线了");
            String[] split = positions.split(",");
            Double[] position = new Double[6];
            int p = 0;
            for (int i = 0; i < split.length&&p<position.length; i++) {
                String temp = split[i];
                if(!"".equals(temp)){
                   position[p++] = Double.parseDouble(temp);
                }
            }
            System.out.println(Arrays.toString(position));
//        System.out.println(position[0]);
            BigDecimal longitudeX1 = new BigDecimal(position[0]);
            BigDecimal latitudeX1 = new BigDecimal(position[1]);
            BigDecimal longitudeX2 = new BigDecimal(position[2]);
            BigDecimal latitudeX2 = new BigDecimal(position[3]);
            BigDecimal longitudeX3 = new BigDecimal(position[4]);
            BigDecimal latitudeX3 = new BigDecimal(position[5]);
            double longitudeX4 = 119.19556;
            double latitudeX4 = 34.604079008746346;
            System.out.println("点一");
            //求线的方程
            //double k1 = (latitudeX1-latitudeX2)/(longitudeX1-longitudeX2);
            BigDecimal k1 = latitudeX1.subtract(latitudeX2).divide(longitudeX1.subtract(longitudeX2),90,BigDecimal.ROUND_HALF_UP);
            //double k2 = (latitudeX3-latitudeX2)/(longitudeX3-longitudeX2);
            BigDecimal k2 = latitudeX3.subtract(latitudeX2).divide(longitudeX3.subtract(longitudeX2),90,BigDecimal.ROUND_HALF_UP);
            //double b1 =latitudeX1-k1*longitudeX1;

            BigDecimal b1 = latitudeX1.subtract(k1.multiply(longitudeX1));
            //double b2 =latitudeX3-k2*longitudeX3;
            BigDecimal b2 = latitudeX3.subtract(k2.multiply(longitudeX3));
            //对坐标做处理使情况单一
            if(startX>endX){
                Double temp = startX;
                startX = endX;
                endX = temp ;
                temp = endY ;
                endY = startY;
                startY = temp;

            }
            BigDecimal startX1 = new BigDecimal(startX);
            BigDecimal endY1 =  new BigDecimal(endY);
            BigDecimal endX1 = new BigDecimal(endX);
            BigDecimal startY1 =  new BigDecimal(startY);
            //先计算初始点单元号为接下来的计算做准备
            //计算每个单元的长宽
            //double line1 =Math.sqrt(Math.pow(longitudeX1-longitudeX2,2)+Math.pow(latitudeX1-latitudeX2,2));


            BigDecimal line1 =sqrt(longitudeX1.subtract(longitudeX2).multiply(longitudeX1.subtract(longitudeX2)).add(latitudeX1.subtract(latitudeX2).multiply(latitudeX1.subtract(latitudeX2))),90);
            //double line2 =Math.sqrt(Math.pow(longitudeX3-longitudeX2,2)+Math.pow(latitudeX3-latitudeX2,2));
            BigDecimal line2 =sqrt(longitudeX3.subtract(longitudeX2).multiply(longitudeX3.subtract(longitudeX2)).add(latitudeX3.subtract(latitudeX2).multiply(latitudeX3.subtract(latitudeX2))),90);
            System.out.println(line1+":"+line2);
            //double xGap = line2/xSize;
            //double yGap = line1/ySize;
            System.out.println(new BigDecimal(xSize));
            BigDecimal xGap = line2.divide(new BigDecimal(xSize),90,BigDecimal.ROUND_HALF_UP);
            BigDecimal yGap = line1.divide(new BigDecimal(ySize),90,BigDecimal.ROUND_HALF_UP);
            System.out.println(xGap);
            //每次偏移的长度
            //double longitudeGap = (longitudeX3-longitudeX2)/xSize;
            //double latitudeGap = (latitudeX1-latitudeX2)/ySize;
            BigDecimal longitudeGap = longitudeX3.subtract(longitudeX2).divide(new BigDecimal(xSize),90,BigDecimal.ROUND_HALF_UP);
            BigDecimal latitudeGap = latitudeX1.subtract(latitudeX2).divide(new BigDecimal(ySize),90,BigDecimal.ROUND_HALF_UP);
            //计算起始点到直线的距离
            //double Gap1 = Math.abs(k1*startX-startY+b1)/Math.sqrt(1+Math.pow(k1,2));
            //double Gap2 = Math.abs(k2*startX-startY+b2)/Math.sqrt(1+Math.pow(k2,2));
            BigDecimal Gap1 = k1.multiply(startX1).subtract(startY1).add(b1).abs().divide(sqrt(new BigDecimal(1).add(k1.multiply(k1)),90),90,BigDecimal.ROUND_HALF_UP);
            BigDecimal Gap2 = k2.multiply(startX1).subtract(startY1).add(b2).abs().divide(sqrt(new BigDecimal(1).add(k2.multiply(k2)),90),90,BigDecimal.ROUND_HALF_UP);
            //计算终点到直线的距离
            ///double Gap3 = Math.abs(k1*endX-endY+b1)/Math.sqrt(1+Math.pow(k1,2));
            BigDecimal Gap3 = k1.multiply(endX1).subtract(endY1).add(b1).abs().divide(sqrt(new BigDecimal(1).add(k1.multiply(k1)),90),90,BigDecimal.ROUND_HALF_UP);
            BigDecimal Gap4 = k2.multiply(endX1).subtract(endY1).add(b2).abs().divide(sqrt(new BigDecimal(1).add(k2.multiply(k2)),90),90,BigDecimal.ROUND_HALF_UP);
            //计算单元
//        int xNum =(int)(Gap1/xGap)+1;
//        int yNum =(int)(Gap2/yGap)+1;
//        int xNum0= (int)(Gap3/xGap)+1;
            int xNum = (int)(Gap1.divide(xGap,90,BigDecimal.ROUND_HALF_UP).doubleValue())+1;
            int yNum = (int)(Gap2.divide(yGap,90,BigDecimal.ROUND_HALF_UP).doubleValue())+1;
            int xNum0 = (int)(Gap3.divide(xGap,90,BigDecimal.ROUND_HALF_UP).doubleValue())+1;
            int yNum0 = (int)(Gap4.divide(xGap,90,BigDecimal.ROUND_HALF_UP).doubleValue())+1;

            System.out.println(xNum+":"+yNum+":"+xNum0+":"+yNum0);
            //计算所画线方程
            //double k3 = (endY-startY)/(endX-startX);
            //double b3 =startY - k3*startX;
            BigDecimal k3 = endY1.subtract(startY1).divide(endX1.subtract(startX1),90,BigDecimal.ROUND_HALF_UP);

            BigDecimal b3 = startY1.subtract(k3.multiply(startX1));
            //存储的容器
            list = new LinkedList<>();

            if(k3.compareTo(k2)>0){
                //情况一
                //起始计算点
                //double x = longitudeX2 + xNum*longitudeGap;
                BigDecimal x = longitudeX2.add(new BigDecimal(xNum).multiply(longitudeGap));
                BigDecimal k =new BigDecimal(String.valueOf(k1));
                int pre = yNum;
                for (int i = xNum; i < xNum0; i++) {
                    //double y = k2*x+b2;
                    BigDecimal y = k2.multiply(x).add(b2);

                    //求平行直线方程 k = k1
                    //double b=y-k1*x;
                    BigDecimal b = y.subtract(k1.multiply(x));
                    // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
                    //double xTemp = (b3-b)/(k-k3);
                    //double yTemp= (b3*k-b*k3)/(k-k3);
                    BigDecimal xTemp = b3.subtract(b).divide(k.subtract(k3),90,BigDecimal.ROUND_HALF_UP);
                    BigDecimal yTemp = b3.multiply(k).subtract(b.multiply(k3)).divide(k.subtract(k3),90,BigDecimal.ROUND_HALF_UP);
                    //System.out.println(xTemp+":"+yTemp);
                    //int last = (int)(Math.abs(k2*xTemp-yTemp+b2)/Math.sqrt(1+Math.pow(k2,2))/xGap)+1;
                    int last =(int)(k2.multiply(xTemp).subtract(yTemp).add(b2).abs().divide(sqrt(new BigDecimal(1).add(k2.multiply(k2)),90),90,BigDecimal.ROUND_HALF_UP).divide(xGap,90,BigDecimal.ROUND_HALF_UP).doubleValue())+1;
                   System.out.println(last);

                    while(last>=pre){
                        list.add((pre-1)*xSize+i);
                        pre++;
                    }
                    x=x.add(longitudeGap);
                    //回调pre

                    pre--;
                }
                while(pre<=yNum0){
                    list.add((pre-1)*100+xNum0);
                    pre++;
                }


            }else {
    //            //情况二
    //            //起始计算点
    //            double x = longitudeX2 + xNum*latitudeGap;
    //            double k =k1;
    //            int pre = yNum;
    //
    //            for (int i = xNum; i < xNum0; i++) {
    //                double y = k2*x+b2;
    //                //求平行直线方程 k = k1
    //                double b=y-k1*x;
    //                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
    //                double xTemp = (b3-b)/(k-k3);
    //                double yTemp= (b3*k-b*k3)/(k-k3);
    //
    //                int last = (int)(Math.abs(k2*xTemp-yTemp+b2)/Math.sqrt(1+Math.pow(k2,2))/xGap)+1;
    //                while(last<=pre){
    //                    list.add((pre-1)*xSize+i);
    //                    pre--;
    //                }
    //                x+=longitudeGap;
    //                //回调pre
    //                pre++;
    //            }
                //情况一
                //起始计算点
                //double x = longitudeX2 + xNum*latitudeGap;
                BigDecimal x = longitudeX2.add(new BigDecimal(xNum).multiply(longitudeGap));
                BigDecimal k =new BigDecimal(String.valueOf(k1));
                int pre = yNum;

                for (int i = xNum; i >= xNum0; i++) {
                    //double y = k2*x+b2;
                    BigDecimal y = k2.multiply(x).add(b2);
                    //求平行直线方程 k = k1
                    //double b=y-k1*x;
                    BigDecimal b = y.subtract(k1.multiply(x));
                    // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
                    //double xTemp = (b3-b)/(k-k3);
                    //double yTemp= (b3*k-b*k3)/(k-k3);
                    BigDecimal xTemp = b3.subtract(b).divide(k.subtract(k3),90,BigDecimal.ROUND_HALF_UP);
                    BigDecimal yTemp = b3.multiply(k).subtract(b.multiply(k3)).divide(k.subtract(k3),90,BigDecimal.ROUND_HALF_UP);
                    //System.out.println(xTemp+":"+yTemp);
                    //int last = (int)(Math.abs(k2*xTemp-yTemp+b2)/Math.sqrt(1+Math.pow(k2,2))/xGap)+1;
                    int last =(int)(k2.multiply(xTemp).subtract(yTemp).add(b2).abs().divide(sqrt(new BigDecimal(1).add(k2.multiply(k2)),90),90,BigDecimal.ROUND_HALF_UP).divide(xGap,90,BigDecimal.ROUND_HALF_UP).doubleValue())+1;
                    System.out.println(last);
                    while(last<=pre){
                        list.add((pre-1)*xSize+i);
                        pre--;
                    }
                    x=x.add(longitudeGap);
                    //回调pre

                    pre++;
                }
            }
            Collections.sort(list);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<Integer>  computeElements(String positions,Double startX,Double startY,Double endX,Double endY,int xSize,int ySize){

        String[] split = positions.split(",");
        Double[] position = new Double[8];
        int p = 0;
        for (int i = 0; i < split.length&&p<position.length; i++) {
            String temp = split[i];
            if(!"".equals(temp)){
                position[p++] = Double.parseDouble(temp);
            }
        }
        List<Integer> list = new LinkedList<>();
//        System.out.println(position[0]);
        double longitudeX1 = position[0];
        double latitudeX1 = position[1];
        double longitudeX2 = position[2] ;
        double latitudeX2 = position[3] ;
        double longitudeX3 = position[4];
        double latitudeX3 = position[5];
        double longitudeX4 = position[6];
        double latitudeX4 = 34.604079008746346;

//        System.out.println(position[6]);

        //求线的方程
        double k1 = (latitudeX1-latitudeX2)/(longitudeX1-longitudeX2);
        double k2 = (latitudeX3-latitudeX2)/(longitudeX3-longitudeX2);
        double b1 =latitudeX1-k1*longitudeX1;
        double b2 =latitudeX3-k2*longitudeX3;
        //对坐标做处理使情况单一
        if(startX>endX){
            Double temp = startX;
            startX = endX;
            endX = temp ;
            temp = endY ;
            endY = startY;
            startY = temp;

        }
        //计算所画线方程
        double k3 = (endY-startY)/(endX-startX);
        double b3 =startY - k3*startX;
        //先计算初始点单元号为接下来的计算做准备
        //计算每个单元的长宽
        double line1 =Math.sqrt(Math.pow(longitudeX1-longitudeX2,2)+Math.pow(latitudeX1-latitudeX2,2));
        double line2 =Math.sqrt(Math.pow(longitudeX3-longitudeX2,2)+Math.pow(latitudeX3-latitudeX2,2));
        double xGap = line2/xSize;
        double yGap = line1/ySize;
        //每次偏移的长度
        double longitudeGap = (longitudeX3-longitudeX2)/xSize;
        double latitudeGap = (latitudeX1-latitudeX2)/ySize;
        //计算起始点到直线的距离
        //处理边界
        int startArea=0;
        if(startX<Math.min(longitudeX1,longitudeX2)||((startY-b1)/k1)> startX){//在lin1之外
            System.out.println("在line1之外");
            double temp = startX*k2;
            double bTemp=latitudeX1-k2*longitudeX1;
            if(startY>(temp+bTemp)){
                //startArea=1;//暂时不更新
                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
                double kTemp = (latitudeX1-startY)/(longitudeX1-startX);
                if(kTemp<k1){
                    startX = (b3-bTemp)/(k2-k3);
                    startY = (b3*k2-bTemp*k3)/(k2-k3);
                    if(startX>longitudeX4){
                        return list;
                    }
                }else{
                    startX = (b3-b1)/(k1-k3);
                    startY = (b3*k1-b1*k3)/(k1-k3);
                    if(startY<latitudeX2){
                        return list;
                    }
                }

            }else if(startY<(temp+b2)){
                //tartArea=7;
                startX = (b3-b2)/(k2-k3);
                startY = (b3*k2-b2*k3)/(k2-k3);

            }else {
                startArea=8;
                startY = (b3*k1-b1*k3)/(k1-k3);
                if(startY>=latitudeX1){
//                    startY=(b3*k2-bTemp*k3)/(k2-k3);
//                    startX = (b3-bTemp)/(k2-k3);
                    return list;
                }else if(startY<=latitudeX2){
//                    startY=(b3*k2-b2*k3)/(k2-k3);
//                    startX = (b2-bTemp)/(k2-k3);
                    return list;
                }else{
                    startX = (b3-b1)/(k1-k3);
                    System.out.println(startX+":"+startY);
                }


            }

        }else {
            double temp = startX*k2;
            double bTemp=latitudeX1-k2*longitudeX1;
            if(startY>(temp+bTemp)){

                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
                startX = (b3-bTemp)/(k2-k3);
                startY = (b3*k2-bTemp*k3)/(k2-k3);
                if(startX>longitudeX4||startX<longitudeX1){
                    return null;
                }
            }else if(startY<(temp+b2)){

                startX = (b3-b2)/(k2-k3);
                startY = (b3*k2-b2*k3)/(k2-k3);
                if(startX>longitudeX4||startX<longitudeX1){
                    return null;
                }
            }
         }

//        double b4 =latitudeX3-k1*longitudeX3;
//        if(endX>Math.max(longitudeX3,longitudeX4)||((endY-b4)/k1)< endX){//在lin1之外
//            double temp = startX*k2;
//            double bTemp=latitudeX1-k2*longitudeX1;
//            if(endY>(temp+bTemp)){
//                if(startArea==1||startArea==2){
//                    return null;
//                }
////                else if(startArea==8){
////
////                }
//                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
//                endX = (b3-bTemp)/(k2-k3);
//                endY = (b3*k2-bTemp*k3)/(k2-k3);
//            }else if(endY<(temp+b2)){
//                startArea=7;
//                startX = (b3-b2)/(k2-k3);
//                startY = (b3*k2-b2*k3)/(k2-k3);
//            }else {
//                startArea=8;
//                startY = (b3*k1-b1*k3)/(k1-k3);
//                if(startY>=latitudeX1){
////                    startY=(b3*k2-bTemp*k3)/(k2-k3);
////                    startX = (b3-bTemp)/(k2-k3);
//                    return null;
//                }else if(startY<=latitudeX2){
////                    startY=(b3*k2-b2*k3)/(k2-k3);
////                    startX = (b2-bTemp)/(k2-k3);
//                    return null;
//                }else{
//                    startX = (b3-b1)/(k1-k3);
//                    System.out.println(startX+":"+startY);
//                }
//
//
//            }
//
//        }else {
//            double temp = startX*k2;
//            double bTemp=latitudeX1-k2*longitudeX1;
//            if(startY>(temp+bTemp)){
//                startArea=2;
//                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
//                startX = (b3-bTemp)/(k2-k3);
//                startY = (b3*k2-bTemp*k3)/(k2-k3);
//            }else if(startY<(temp+b2)){
//                startArea=6;
//                startX = (b3-b2)/(k2-k3);
//                startY = (b3*k2-b2*k3)/(k2-k3);
//            }
//        }



        double Gap1 = Math.abs(k1*startX-startY+b1)/Math.sqrt(1+Math.pow(k1,2));
        double Gap2 = Math.abs(k2*startX-startY+b2)/Math.sqrt(1+Math.pow(k2,2));
        //计算终点到直线的距离
        double Gap3 = Math.abs(k1*endX-endY+b1)/Math.sqrt(1+Math.pow(k1,2));
        double Gap4 = Math.abs(k2*endX-endY+b2)/Math.sqrt(1+Math.pow(k2,2));
        //计算单元
        int xNum =(int)(Gap1/xGap)+1;
        int yNum =(int)(Gap2/yGap)+1;
        int xNum0=(int)(Gap3/xGap)+1;
        int yNum0=(int)(Gap4/yGap)+1;
        //System.out.println(xNum+":"+yNum);

        //存储的容器

        if(xNum<0||xNum>xSize){
            return list;
        }
        if(k3>k2){
            //情况一
            //起始计算点
            double x = longitudeX2 + xNum*longitudeGap;
            double k =k1;
            int pre = yNum;
//            xNum0=Math.min(xNum0,xSize);
            for (int i = xNum; i < xNum0; i++) {
                double y = k2*x+b2;
                //System.out.println(x+":"+y);
                //求平行直线方程 k = k1
                double b=y-k1*x;
                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
                double xTemp = (b3-b)/(k-k3);
                double yTemp= (b3*k-b*k3)/(k-k3);

                //System.out.println(xTemp+":"+yTemp);
                int last = (int)(Math.abs(k2*xTemp-yTemp+b2)/Math.sqrt(1+Math.pow(k2,2))/yGap)+1;
                System.out.println(last);
                if(i==xSize){
                    xNum0 = xSize;
                    yNum0 = last;
                    System.out.println("last="+last+":xNum0="+xNum0);
                    break;
                }
                if(last>ySize){
                    yNum0 = ySize;
                    break;
                }
                while(last>=pre){
                    list.add((pre-1)*xSize+i);
                    pre++;
                }
                x+=longitudeGap;
                //回调pre

                pre--;
            }
            while(pre<=yNum0){
                list.add((pre-1)*100+xNum0);
                pre++;
            }

        }else {
            //情况二
            //起始计算点
            double x = longitudeX2 + xNum*latitudeGap;
            double k =k1;
            int pre = yNum;

            for (int i = xNum; i < xNum0&&i<xSize; i++) {
                double y = k2*x+b2;
                System.out.println(x+":"+y);
                //求平行直线方程 k = k1
                double b=y-k1*x;
                // 求两条直线交点 公式：x=(b2-b1)/(k1-k2)  y=(b2*k1-b1*k2)/(k1-k2)
                double xTemp = (b3-b)/(k-k3);
                double yTemp= (b3*k-b*k3)/(k-k3);
                if(yTemp<Math.max(latitudeX2,latitudeX3)&&(k2*xTemp+b2)>yTemp){
                    System.out.println("ee");
                    yNum0 = 1 ;
                    xNum0 =i;
                    break;
                }
                int last = (int)(Math.abs(k2*xTemp-yTemp+b2)/Math.sqrt(1+Math.pow(k2,2))/yGap)+1;

                if(i==xSize){
                    yNum0 = last;
                    break;
                }
                while(last<=pre){
                    list.add((pre-1)*xSize+i);
                    pre--;
                }
                x+=longitudeGap;
                //回调pre
                pre++;
            }
            while(pre>=yNum0){
                list.add((pre-1)*100+xNum0);
                pre--;
            }

        }
        Collections.sort(list);
        return list;
    }


    @Test
    public void test1(){
        //let longitudeH = 118;
        //let longitudeL = 110 ;
        //let latitudeH = 38 ;
        //let latitudeL = 30 ;
        List<Integer> l = computeElements(30.0, 110.0, 38.0, 118.0, 111.0, 31.0, 113.0,33.0 , 40, 40);
        System.out.println(l);
    }

}
