package com.example.hw.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件上传下载处理
 */
public class FileUtil {

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new Exception("未选择需上传的文件");
        }

        String filePath = new File("file/").getAbsolutePath();
        File fileUpload= new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }

        String fileName = "file" + System.currentTimeMillis() + file.getOriginalFilename();
        fileUpload = new File(filePath, fileName);
        if (fileUpload.exists()) {
            throw new Exception("上传的文件已存在");
        }

        try {
            file.transferTo(fileUpload);

            return fileName;
        } catch (IOException e) {
            throw new Exception("上传文件到服务器失败：" + e.toString());
        }
    }

    public static void download(String name, HttpServletResponse response) throws Exception {
        File file = new File("file/" + File.separator + name);

        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }
}
