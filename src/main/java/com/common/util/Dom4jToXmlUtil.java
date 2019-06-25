package com.common.util;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletResponse;

public class Dom4jToXmlUtil {

    private static final Logger logger = Logger.getLogger(Dom4jToXmlUtil.class);

    /**
     * json 数据转换对象解析
     *
     * @param xmlPath xml路径
     * @param clazz   要转换的目标对象类型
     * @return List<T>
     * @throws Exception
     */
    public static List fromXmlToBean(String xmlPath, Class clazz) throws Exception {
        // create saxReader
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(xmlPath));
        Element rootElement = document.getRootElement();

        List<Object> list = new ArrayList();

        Element element;
        for (Iterator i = rootElement.elementIterator(clazz.getSimpleName()); i.hasNext(); ) {
            element = (Element) i.next();

            // get all field
            Field[] fields = clazz.getDeclaredFields();
            Object obj = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                try {
                    element.elementTextTrim(name);
                } catch (Exception ex) {
                    continue;
                }
                if (element.elementTextTrim(name) != null && !"".equals(element.elementTextTrim(name))) {
                    // according field type set to the generated object.
                    if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                        field.set(obj, Long.parseLong(element.elementTextTrim(name)));
                    } else if (field.getType().equals(String.class)) {
                        field.set(obj, element.elementTextTrim(name));
                    } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
                        field.set(obj, Double.parseDouble(element.elementTextTrim(name)));
                    } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        field.set(obj, Integer.parseInt(element.elementTextTrim(name)));
                    } else if (field.getType().equals(java.util.Date.class)) {
                        field.set(obj, Date.parse(element.elementTextTrim(name)));
                    } else {
                        continue;
                    }
                }
            }
            list.add(obj);
        }

        return list;
    }

    /**
     * 通用List写入xml
     *
     * @param list     要保存的集合数据
     * @param clazz    实体类
     * @param path     存放路径
     * @param fileName 文件名
     */
    public static void writeXml(List list, Class clazz, String path, String fileName) {
        int flag = 1;

        // create dom tree
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(clazz.getSimpleName() + "s");
        // add element
        for (Object obj : list) {
            Element element = root.addElement(clazz.getSimpleName());
            try {
                Class per = clazz.getSuperclass();
                Field fieldsPerson[] = per.getDeclaredFields();
                for (Field field : fieldsPerson) {
                    field.setAccessible(true);
                    // append child element
                    element.addElement(field.getName()).setText(field.get(obj).toString());
                }
                //get all properties
                Field fields[] = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    // append element child element
                    Element e = element.addElement(field.getName());
                    //deal null and empty
                    if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                        if (field.getName().equals("id")) {
                            e.setText(String.valueOf(flag));
                            flag++;
                        } else {
                            e.setText(field.get(obj).toString());
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        try {
            //format xml default:ISO8859-1
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path + "/" + fileName), format);
            //cancel format xml
            //XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path + "/" +fileName));

            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * export File
     */
    public static void exportFile(HttpServletResponse response, String realPath, String fileName) {
        if (fileName != null) {
            //set file path
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream; charset=UTF-8");
                response.setContentType("application/force-download");
                // set file name
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[2048];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                OutputStream os = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    os = response.getOutputStream();
                    int read;
                    while ((read = bis.read(buffer)) != -1) {
                        os.write(buffer, 0, read);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                }
            }
        }
    }
    
    

    /**
     * test main
     */
    public static void main(String[] args) throws Exception {
        /*List<ReportApp> list = new ArrayList<>();
        ReportApp r1 = new ReportApp();
        r1.setId(1);
        r1.setName("test1");
        r1.setCreatetime("2017-01-01");
        list.add(r1);
        String realPath = "D:/test";
        String fileName = ReportApp.class.getSimpleName() + "s.xml";
        writeXml(list, ReportApp.class, realPath, fileName);

        List<ReportApp> list2 = fromXmlToBean(realPath + "/" + fileName, ReportApp.class);
        for (ReportApp r : list2) {
            System.out.println(r.toString());
            logger.info(r.toString());
        }*/
    }
}