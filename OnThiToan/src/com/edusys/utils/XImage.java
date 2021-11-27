
package com.edusys.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImage {
    public static void save(File src){
        File f = new File("Hinh/CauHoi",src.getName());
        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();// tạo thư mục nếu chưa tồn tại
        }
        try{
            // copy ảnh vào thư mục dethi
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(f.getAbsolutePath());
            Files.copy(from, to,StandardCopyOption.REPLACE_EXISTING);
        }catch(Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File("chuyende",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
