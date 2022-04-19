package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


/**
 * Created by Jinmunan
 * 2022/1/14
 * 10:57
 */

@RestController
@CrossOrigin //跨域
public class FileUploadController {
    /**
     * 文件上传
     */
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        try {
            //封装fastDFSFile
            FastDFSFile fastDFSFile = new FastDFSFile(
                    file.getOriginalFilename(),
                    file.getBytes(),
                    StringUtils.getFilenameExtension(file.getOriginalFilename())
            );
            String[] upload = FastDFSUtil.upload(fastDFSFile);
            /**
             * 拼接访问地址 http://121.41.2.7:8080/group1/M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
             * 返回值说明:
             *  第一个是存储文件的组名
             *  第二个是新创建的文件名
             */
            if (upload != null) {
                return FastDFSUtil.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
