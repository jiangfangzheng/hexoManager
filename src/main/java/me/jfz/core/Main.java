package me.jfz.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static me.jfz.core.Hexo.fileToHexoMdFile;
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
        List<String> filePathList = readFilesByPath("E:\\Coding\\Blog\\hexo\\source\\_posts");
//        System.out.println(filePathList);

//        System.out.println(mdFile);
        Map<String, HexoMdFile> hexoMdFileMap = new TreeMap<>();
        for (String fileName : filePathList) {
            HexoMdFile hexoMdFile = fileToHexoMdFile(fileName);
            String key = hexoMdFile.getDate() + "   " + hexoMdFile.getFileName();
            hexoMdFileMap.put(key, hexoMdFile);
//            System.out.println(hexoMdFile.getFileName() + "\t\t\t" + hexoMdFile.getDate());
        }
        String[] filePathNameArr = new String[hexoMdFileMap.size()];
        int i = 0;
        for (String akey : hexoMdFileMap.keySet()) {
            filePathNameArr[i++] = akey;
        }
//        for (String a : filePathNameArr) {
//            System.out.println(a);
//        }

        System.out.println("filePathNameArr.length: " + filePathNameArr.length);
        gui(reverseSelf(filePathNameArr), hexoMdFileMap);
    }
}
