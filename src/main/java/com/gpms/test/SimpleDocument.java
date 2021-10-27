package com.gpms.test;

import com.gpms.util.CustomXWPFDocument;
import com.gpms.util.WordUtil;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SimpleDocument {

    public static void main(String[] args) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("@1@", "11111");
        param.put("@2@", "123456789");
        param.put("@3@", "test3");
        param.put("@4@", "test4");
        param.put("@5@", "test5");
        param.put("@6@", "test6");
        param.put("@7@", "test7");
        param.put("@8@", "11111");
        param.put("@9@", "yq");
        param.put("@10@", "11111");       //param.put("@5@", new Date().toString());

      /*  Map<String, Object> header = new HashMap<String, Object>();
        header.put("width", 100);
        header.put("height", 150);
        header.put("type", "jpg");
        header.put("content", WordUtil.inputStream2ByteArray(new FileInputStream("./src/new.jpg"), true));
        param.put("@6@", header);*/


        CustomXWPFDocument doc = WordUtil.generateWord(param, "temp.docx");
        FileOutputStream fopts = new FileOutputStream("temp2.docx");
        doc.write(fopts);
        fopts.close();
    }
}