package cn.hhu.utils;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author tlj
 */
public class writeGeoJson {
    /*
    *
    * 画多条线
    * */
    @Test
    public void writeMultiLine(){
        Double longitudeH = 118.0;
        Double longitudeL = 110.0 ;
        Double latitudeH = 38.0 ;
        Double latitudeL = 30.0 ;
        int xNum = 40 ;
        int yNum = 40 ;

        try{
            File writeName = new File("./web/static/DamBreak_CF.geojson");
            writeName.createNewFile();
            try(

                    FileWriter fileWriter = new FileWriter(writeName);
                    BufferedWriter out = new BufferedWriter(fileWriter);

                    ){
                out.write("{\n" +
                        "  \"type\": \"FeatureCollection\",\n" +
                        "  \"features\": [\n"+"{\"type\":\"Feature\",\n" +
                        "    \"properties\":{},\n" +
                        "    \"geometry\":{\n" +
                        "        \"type\":\"MultiLineString\",\n" +
                        "        \"coordinates\":\n" +
                        "        [");
                out.newLine();
                Double longitudeGap = longitudeH - longitudeL;
                Double latitudeGap = latitudeH - latitudeL;
                Double elementXL =longitudeGap/xNum;
                Double elementYL = latitudeGap/yNum;
               Double latitudeLTemp =latitudeL;
               Double longitudeLTemp = longitudeL;
                for (int j = 0; j < yNum; j++) {
                    out.write("[["+longitudeL+","+latitudeLTemp+"],"+"["+longitudeH+","+latitudeLTemp+"]],");
                    out.newLine();
                    latitudeLTemp+=elementYL;
                }
                for (int i = 0; i < xNum-1; i++) {
                    out.write("[["+longitudeLTemp+","+latitudeL+"],"+"["+longitudeLTemp+","+latitudeH+"]],");
                    out.newLine();
                    longitudeLTemp+=elementXL;
                }
                out.write("[["+longitudeLTemp+","+latitudeL+"],"+"["+longitudeLTemp+","+latitudeH+"]]");
                out.write("] }\n" +
                        "}\n" +
                        "  ]\n" +
                        "}");

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void writeMultiPolygon(){
        Double longitudeH = 118.0;
        Double longitudeL = 110.0 ;
        Double latitudeH = 38.0 ;
        Double latitudeL = 30.0 ;
        int xNum = 40 ;
        int yNum = 40 ;

        try{
            File writeName = new File("./web/static/ploygon.geojson");
            writeName.createNewFile();
            try(

                    FileWriter fileWriter = new FileWriter(writeName);
                    BufferedWriter out = new BufferedWriter(fileWriter);

            ){
                out.write("{\n" +
                        "  \"type\": \"FeatureCollection\",\n" +
                        "  \"features\": [\n"+"{\"type\":\"Feature\",\n" +
                        "    \"properties\":{},\n" +
                        "    \"geometry\":{\n" +
                        "        \"type\":\"MultiPolygon\",\n" +
                        "        \"coordinates\":\n" +
                        "        [");
                out.newLine();
                Double longitudeGap = longitudeH - longitudeL;
                Double latitudeGap = latitudeH - latitudeL;
                Double elementXL =longitudeGap/xNum;
                Double elementYL = latitudeGap/yNum;
                Double latitudeLTemp =latitudeL;
                Double longitudeLTemp = longitudeL;
                Double latitudeLTemp2 =latitudeL;
                Double longitudeLTemp2 = longitudeL;
                //for (int j = 0; j < yNum; j++) {
                //    out.write("[["+longitudeL+","+latitudeLTemp+"],"+"["+longitudeH+","+latitudeLTemp+"]],");
                //    out.newLine();
                //    latitudeLTemp+=elementYL;
                //}
                //for (int i = 0; i < xNum-1; i++) {
                //    out.write("[["+longitudeLTemp+","+latitudeL+"],"+"["+longitudeLTemp+","+latitudeH+"]],");
                //    out.newLine();
                //    longitudeLTemp+=elementXL;
                //}
                //out.write("[["+longitudeLTemp+","+latitudeL+"],"+"["+longitudeLTemp+","+latitudeH+"]]");
                for (int i = 0; i < yNum; i++) {

                    latitudeLTemp2+=elementYL;
                    for (int j = 0; j < xNum; j++) {
                        longitudeLTemp2+=elementXL;
                        out.write("[\n[\n["+longitudeLTemp+","+latitudeLTemp+"],\n"+"["+longitudeLTemp+","+latitudeLTemp2+"],\n["+longitudeLTemp2+","+latitudeLTemp2+"],\n["+longitudeLTemp2+","+latitudeLTemp+"]\n]\n],\n");
                        longitudeLTemp=longitudeLTemp2;
                    }
                    longitudeLTemp=longitudeLTemp2=longitudeL;
                    latitudeLTemp=latitudeLTemp2;

                }
                out.write("] }\n" +
                        "}\n" +
                        "  ]\n" +
                        "}");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test1(){
        Double longitudeX1 = 118.86426717449807;
        Double latitudeX1 = 34.771934788782254;
        Double longitudeX2 = 118.85150634464433 ;
        Double latitudeX2 = 34.713883113334944 ;
        Double longitudeX3 = 119.16374583477337;
        Double latitudeX3 = 34.64524710969765;
        Double X4lon =119.1765066646271;
        Double X4lat = 34.70329878514496;
        latitudeX3=-(longitudeX1-longitudeX2)*(longitudeX3-longitudeX2)/(latitudeX1-latitudeX2)+latitudeX2;
        X4lon =longitudeX3-longitudeX2+longitudeX1;
        X4lat =latitudeX1-latitudeX2+latitudeX3;
        System.out.println(X4lon+":"+X4lat);
        System.out.println(latitudeX3);
    }

    @Test
    public void writeMultiPolygon2(){

        Double longitudeX1 = 118.86426717449807;
        Double latitudeX1 = 34.771934788782254;
        Double longitudeX2 = 118.85150634464433 ;
        Double latitudeX2 = 34.713883113334944 ;
        Double longitudeX3 = 119.16374583477337;
        Double latitudeX3 = 34.64524710969765;
        Double longitudeX4 = 119.19556;
        Double latitudeX4 = 34.604079008746346;
         int  xNum = 100 ;
        int yNum = 18 ;
        Double keyY = (latitudeX1-latitudeX2)/(longitudeX1-longitudeX2);
        Double keyX = (latitudeX3-latitudeX2)/(longitudeX3-longitudeX2);

        try{
            File writeName = new File("./web/static/ploygon2.geojson");
            writeName.createNewFile();
            try(

                    FileWriter fileWriter = new FileWriter(writeName);
                    BufferedWriter out = new BufferedWriter(fileWriter);

            ){
                out.write("{\n" +
                        "  \"type\": \"FeatureCollection\",\n" +
                        "  \"features\": [\n"+"{\"type\":\"Feature\",\n" +
                        "    \"properties\":{},\n" +
                        "    \"geometry\":{\n" +
                        "        \"type\":\"MultiPolygon\",\n" +
                        "        \"coordinates\":\n" +
                        "        [");
                out.newLine();
                Double longitudeGap = (longitudeX3-longitudeX2)/xNum;
                Double latitudeGap = (latitudeX1-latitudeX2)/yNum;
                System.out.println(latitudeGap+":"+longitudeGap);
//                Double elementXL =longitudeGap/xNum;
//                Double elementYL = latitudeGap/yNum;
                Double latitudeLTemp =latitudeX2;
                Double longitudeLTemp = longitudeX2;
                Double latitudeLTemp2 =latitudeX2;
                Double longitudeLTemp2 = longitudeX2;
                Double latitudeLTemp3 =0.0;
                Double longitudeLTemp3 =0.0;
                //for (int j = 0; j < yNum; j++) {
                //    out.write("[["+longitudeL+","+latitudeLTemp+"],"+"["+longitudeH+","+latitudeLTemp+"]],");
                //    out.newLine();
                //    latitudeLTemp+=elementYL;
                //}
                //for (int i = 0; i < xNum-1; i++) {
                //    out.write("[["+longitudeLTemp+","+latitudeL+"],"+"["+longitudeLTemp+","+latitudeH+"]],");
                //    out.newLine();
                //    longitudeLTemp+=elementXL;
                //}
                //out.write("[["+longitudeLTemp+","+latitudeL+"],"+"["+longitudeLTemp+","+latitudeH+"]]");
                for (int i = 0; i < yNum; i++) {
                    longitudeLTemp2+=latitudeGap/keyY;
                    latitudeLTemp2+=latitudeGap;
                    longitudeLTemp3=longitudeLTemp2;
                    latitudeLTemp3=latitudeLTemp2;
                    for (int j = 0; j < xNum; j++) {

                        out.write("[\n[\n["+longitudeLTemp+","+latitudeLTemp+"],\n"+"["+longitudeLTemp2+","+latitudeLTemp2+"],");
                        latitudeLTemp+=keyX*longitudeGap;
                        longitudeLTemp+=longitudeGap;
                        latitudeLTemp2+=keyX*longitudeGap;
                        longitudeLTemp2+=longitudeGap;
                        out.write("\n["+longitudeLTemp2+","+latitudeLTemp2+"],\n["+longitudeLTemp+","+latitudeLTemp+"]\n]\n],\n");

                    }
//                    out.write(",\n");
                    longitudeLTemp=longitudeLTemp2=longitudeLTemp3;
                    latitudeLTemp=latitudeLTemp2=latitudeLTemp3;

                }
                out.write("] }\n" +
                        "}\n" +
                        "  ]\n" +
                        "}");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        String str = "[[1,2,3],[1,2,3]]";
        String[] split = str.split("(\\[|]|,)");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if(!"".equals(temp)){
                System.out.println(Double.parseDouble(temp));
            }
        }


    }
}
