package cn.hhu.utils;

import cn.hhu.Bean.DamScope;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtil {

    private static String filenameTemp;

    public static String getFilenameTemp() {
        return filenameTemp;
    }

    public static void setFilenameTemp(String filenameTemp) {
        FileUploadUtil.filenameTemp = filenameTemp;
    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static boolean creatTxtFile(String path, String name) throws IOException {
        boolean flag = false;
        filenameTemp = path + '\\' + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }

    /**
     * 写文件
     *
     * @param newStr 新内容
     * @throws IOException
     */
    public static boolean writeTxtFile(String newStr) throws IOException {

        System.out.println("开始写入txt文件");

        System.out.println("newStr=" +newStr);
        System.out.println("fileName=" + filenameTemp);

        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr;
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            buf.append(filein);

            System.out.println("buf=" + buf.toString());

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        System.out.println("结束写入txt文件");

        return flag;
    }

    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static List<DamScope> txt2StringByTimeInterval(File file, int start, int end) {
        List<DamScope> list = new ArrayList<>();
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            int count = 0;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                count++;
                if (count <= end && count>= start){
                    // 获取数据
                    DamScope damScope = new DamScope();
//                    System.out.println(s);
                    String[] split = s.trim().split("\\s+");
                    damScope.setUnitId(Integer.parseInt(split[0]));

                    // 第二个interval 为什么2472个结点  只能读取2472呢？
                    damScope.setX(Double.parseDouble(split[1]));
                    damScope.setY(Double.parseDouble(split[2]));
                    if (!isNum(split[3]))
                        split[3] = "0.0";
                    damScope.setBedElev(Double.parseDouble(split[3]));
                    if (!isNum(split[4]))
                        split[4] = "0.0";
                    damScope.setWaterElev(Double.parseDouble(split[4]));

                    list.add(damScope);
//                    result = result + "\n" + s;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static List<DamScope> readTxtByTimeInterval(File file, int start, int end) {
        List<DamScope> list = new ArrayList<>();
        String result = "";
        try {
            LineIterator lineIterator = FileUtils.lineIterator(file,"UTF-8");

            int times = 1;
            int count = 0;
            while (lineIterator.hasNext()) {//使用readLine方法，一次读一行
                String s = lineIterator.nextLine();
                count++;
                if (count <= end && count>= start){
                    // 获取数据
                    DamScope damScope = new DamScope();
                    System.out.println(s);
                    String[] split = s.trim().split("\\s+");
                    damScope.setUnitId(Integer.parseInt(split[0]));

                    // 第二个interval 为什么2472个结点  只能读取2472呢？
                    damScope.setX(Double.parseDouble(split[1]));
                    damScope.setY(Double.parseDouble(split[2]));
                    if (!isNum(split[3]))
                        split[3] = "0.0";
                    damScope.setBedElev(Double.parseDouble(split[3]));
                    if (!isNum(split[4]))
                        split[4] = "0.0";
                    damScope.setWaterElev(Double.parseDouble(split[4]));

                    list.add(damScope);
                    Thread.sleep(5);
//                    result = result + "\n" + s;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean isNum(String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                if (str.charAt(i) != '.' && str.charAt(i) != '-')
                    return false;
        }
        return true;
    }

}
