package com.stx.health.controller;

import com.stx.health.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${health.imgPath}")
    private String imgBasePath;

    @Value("${health.videoPath}")
    private String videoBasePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除

        //原始文件名
        String originalFilename = file.getOriginalFilename();//abc.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg

        File dir = null;
        if (suffix.equals(".mp4")){
            dir = new File(videoBasePath);
        }else {
            //创建一个目录对象
            dir = new File(imgBasePath);
        }

        //判断当前目录是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            if (suffix.equals(".mp4")){
                file.transferTo(new File(videoBasePath + fileName));
            }else {
                //将临时文件转存到指定位置
                file.transferTo(new File(imgBasePath + fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(fileName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {

            if(name == null){
                name = "8a620944-857e-4532-98f7-a1394109e732.jpg";
            }

            String notPointSuffix = name.substring(name.indexOf("."), name.length());

            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = null;

            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = null;

            if (notPointSuffix.equals(".mp4")){
                fileInputStream = new FileInputStream(new File(videoBasePath + name));
                outputStream = response.getOutputStream();
                response.setContentType("video/mp4");
            }else {
                fileInputStream = new FileInputStream(new File(imgBasePath + name));
                outputStream = response.getOutputStream();
                response.setContentType("image/jpeg");
            }

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
