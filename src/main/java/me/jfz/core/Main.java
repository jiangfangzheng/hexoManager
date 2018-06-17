package me.jfz.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static me.jfz.core.Hexo.fileToHexoMdFile;
import static me.jfz.core.Hexo.getHexoMdFileMap;
import static me.jfz.core.Hexo.reverseSelf;
import static me.jfz.core.MainGui.gui;
import static me.jfz.util.FileUtil.readFilesByPath;

/**
 * @author Sandeepin
 * 2018/6/4 0004
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("poi!");
        // 获取所有文章路径
        List<String> filePathList = readFilesByPath("E:\\Coding\\Blog\\hexo\\source\\_posts");
//        System.out.println(filePathList);

//        System.out.println(mdFile);

        // 获取所有文件的内容
        Map<String, HexoMdFile> hexoMdFileMap = getHexoMdFileMap(filePathList);
        // 获取文件列表（时间+文件名）
        String[] filePathNameArr = new String[hexoMdFileMap.size()];
        int i = 0;
        for (String akey : hexoMdFileMap.keySet()) {
            filePathNameArr[i++] = akey;
        }

        System.out.println("filePathNameArr.length: " + filePathNameArr.length);
        gui(reverseSelf(filePathNameArr), filePathList);
    }
}
