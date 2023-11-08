package com.example.yjm.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetProjectRoot {
    public static boolean deleteImg(String imagePath){
        String currentWorkingDir = System.getProperty("user.dir");
        currentWorkingDir += "\\src\\main\\resources\\static\\images\\"+imagePath;
        System.out.println(currentWorkingDir);
        // 删除图片
        try {
            Path path = Paths.get(currentWorkingDir);

            // 删除文件
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("图片删除失败: " + e.getMessage());
        }
        return true;
    }
    public static String getRoot(){
        String currentWorkingDir = System.getProperty("user.dir");
        currentWorkingDir += "\\src\\main\\resources\\static\\images\\";
        return currentWorkingDir;
    }
}
