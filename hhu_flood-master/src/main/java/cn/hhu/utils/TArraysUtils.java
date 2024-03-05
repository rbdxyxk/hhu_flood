package cn.hhu.utils;

import java.util.Arrays;

/**
 * @author tlj
 */
public class TArraysUtils {
    public static <T> T[] myConcatArray(T[] first,T[]...args){
        int length = first.length;
        for(T[] t:args){
            length+=t.length;
        }
        T[] result = Arrays.copyOf(first,length);

        //System.arraycopy(first,0,result,0,first.length);
        int offest=first.length;
        for(T[] t:args){
            System.arraycopy(t,0,result,offest,t.length);
            offest+=t.length;
        }
        return  result;
    }
    public static Double[][] str2PositionArray(String str){

        String[] split = str.split("(\\[|]|,)");
        Double[][] result = new Double[3][2];
        int x=0,y=0;
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if(!"".equals(temp)){
                result[x][y++] = Double.parseDouble(temp);
            }
            if(y==2){
                x++;
                y=0;
            }
        }
        return result;
    }

    
}
