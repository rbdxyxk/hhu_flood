package cn.hhu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author tlj
 */
@WebServlet("/invokeExe")
public class invokeExe  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String realpath = req.getServletContext().getRealPath("");
        String path= req.getServletContext().getRealPath("/Exe/RSFVMWQltl.exe");
        //String path="web\\Exe\\RSFVMWQltl.exe";
       // String command= "cmd /c E: && cd E:\\Fortran\\ForResearch && start "+path;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(path);

        BufferedReader br = null;
        Process process =null;
        try{

            process =runtime.exec(path);

            //转换流
            InputStreamReader isr = new InputStreamReader(process.getErrorStream());
            //缓冲流用于打印错误信息
            br = new BufferedReader(isr);
            String line =null;
            int i=0;
            while((line=br.readLine())!=null){
                System.out.println(i+++":"+line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (process != null){
                process.destroy();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
