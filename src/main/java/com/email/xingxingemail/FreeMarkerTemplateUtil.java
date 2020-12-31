package com.email.xingxingemail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTemplateUtil {

    public static String getTableHtml(List<List<String>> tableList) {

        String htmlText = "";
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        try {
            //加载模板路径
            configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(),"templates");
            //获取对应名称的模板
            Template template = configuration.getTemplate("table.ftl");
            Map<String, Object> map = new HashMap<>();
            map.put("tableList", tableList);
            //渲染模板为html
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return htmlText;
    }
}