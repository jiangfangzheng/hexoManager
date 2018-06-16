package me.jfz.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static me.jfz.util.FileUtil.readFile;

/**
 * @author Sandeepin
 * 2018/5/29 0029
 */
public class Hexo {


    public static HexoMdFile fileToHexoMdFile(String filePathName) throws IOException {
        HexoMdFile hexoMdFile = new HexoMdFile();
        // 设置 文件路径
        hexoMdFile.setFilePath(filePathName);
        // 读取 文件内容
        String mdFile = readFile(filePathName);
        // 设置 全部文件内容
        hexoMdFile.setContent(mdFile);
        // 设置 文件名 123.md
        String[] filePath = filePathName.split("\\\\");
        hexoMdFile.setFileName(filePath[filePath.length - 1]);
        String[] arr = mdFile.split("---");
//        System.out.println(arr.length);
        // TODO 设置 各属性  tags、categories获取方法待完善
        if (arr.length >= 3) {
            hexoMdFile.setArticle(arr[2]);
            String msg = arr[1];
            String[] lineArr = msg.split("\n");
            Map<String, String> map = new HashMap<String, String>(6);
            for (int i = 0; i < lineArr.length; i++) {
                String[] keyAndMap = lineArr[i].split(": ");
//                System.out.println(lineArr[i]);
//                System.out.println(keyAndMap[0]);
                if (keyAndMap.length == 2) {
                    map.put(keyAndMap[0], keyAndMap[1]);
                }
                if ("tags:".equals(keyAndMap[0])) {
                    if (i + 1 < lineArr.length) {
                        map.put("tags", lineArr[i + 1]);
                    } else {
                        map.put("tags", "");
                    }
                }
                if ("categories:".equals(keyAndMap[0])) {
                    if (i + 1 < lineArr.length) {
                        map.put("categories", lineArr[i + 1]);
                    } else {
                        map.put("categories", "");
                    }

                }
            }
            hexoMdFile.setTitle(map.get("title"));
            hexoMdFile.setUrl(map.get("url"));
            hexoMdFile.setId(map.get("id"));
            hexoMdFile.setDate(map.get("date"));
            hexoMdFile.setTags(map.get("tags"));
            hexoMdFile.setCategories(map.get("categories"));
//            System.out.println(map);
        }
        return hexoMdFile;
    }

    // 数组元素倒序
    public static String[] reverseSelf(String[] strings) {
        for (int start = 0, end = strings.length - 1; start < end; start++, end--) {
            String temp = strings[end];
            strings[end] = strings[start];
            strings[start] = temp;
        }
        return strings;
    }
}
