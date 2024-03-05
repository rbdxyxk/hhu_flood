package cn.hhu;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
 public class generater {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/waterFlood?serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("tlj") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D://src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.hhu") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .entity("pojo")
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://src/main/java/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("ca_pd","cb_nread","cc_cp","cd_pline","ce_tandt","cf_gfandnf","cg1_b","cg2_bandc","cg2_cl","cg2_cw","cg2_g","cg2_l","cg2_sg","cg2_w","ch_1a_q","ch_1b_q","ch_2a_z","ch_2b_z","ch_3a_g","ch_3b_g","ch_4a_s","ch_4b_s","ch_4c_s","ch_4d_s","ch_5a_w","ch_5b_w","ch_6a_wl","ch_6b_wl","ch_7a_jet","ch_7b_jet","ch_7c_jet","ch_7d_jet","ci_zq","cj_val0","ck_zout","mod_fruit","output","road") // 设置需要生成的表名
                            ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
