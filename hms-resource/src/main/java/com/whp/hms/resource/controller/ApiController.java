package com.whp.hms.resource.controller;

import com.whp.hms.core.entity.ImgLog;
import com.whp.hms.core.util.CommonResponse;
import com.whp.hms.core.util.DateUtil;
import com.whp.hms.mapper.ManagerDao;
import com.whp.hms.resource.service.ImgLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Resource
    private ImgLogService service;

    @Resource
    private ManagerDao managerDao;

    @Value("${server.servlet.context-path}")
    private String project;

    @Value("${server.port}")
    private String port;

    @Value("${system.realm}")
    private String realm;

    @Value(value = "${system.imgUpLoadPath}")
    private String imgUpLoadPath;

    @ResponseBody
    @PostMapping("/upload")
    public Object imgUpload(@RequestParam("uId") Integer userId, @RequestParam("upload") MultipartFile file) {
        try {
            logger.info("-------------上传图片-------------");
            if (file.isEmpty()) {
                return CommonResponse.error("文件不能为空!");
            }
            logger.info("userId{}------FILE{}", userId, file);
            String fileOriginName = file.getOriginalFilename();
            if (fileOriginName == null) {
                return CommonResponse.error("文件名异常!");
            }

            /*if (!userId.equals(manager.getId()) || userId == null) {
                return CommonResponse.error("无此用户!");
            }*/

            if (userId == null || managerDao.queryById(userId) == null) {
                return CommonResponse.error("无此用户!");
            }

            String suffix = fileOriginName.substring(fileOriginName.lastIndexOf("."));
            if (suffix.equals(".png") || suffix.equals(".jpg") || suffix.equals(".jpeg")) {
                String fileName = uploadImg(file);
                String newFileName = getShowName(fileName);
                ImgLog fileLog = new ImgLog(newFileName, userId, DateUtil.getNowTime());
                if (service.insert(fileLog) == null) {
                    return CommonResponse.error("上传失败");
                }
                return CommonResponse.success(newFileName);
            }
            return CommonResponse.error("上传文件类型不对,请上传图片文件!如.jpg、.png、.jpeg为后缀的文件!");
        } catch (Exception e) {
            logger.error("上传图片失败：" + e.getMessage());
            return CommonResponse.error(e.getMessage());
        }
    }

    /**
     * 获取图片资源的完整路径
     */
    private String getShowName(String fileName) {
        StringBuilder builder;
        builder = new StringBuilder("http://");
        builder.append(realm)
                .append(":")
                .append(port)
                .append("/")
                .append(project)
                .append("/static/")
                .append(fileName);
        return builder.toString();
    }

    private String uploadImg(MultipartFile file) throws IOException {
        String fileName = getFileName(file.getOriginalFilename());
        String realPath = imgUpLoadPath + "/" + fileName;
        isExist(imgUpLoadPath);
        File dest = new File(realPath);
        file.transferTo(dest);
        return fileName;
    }

    private void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return 新文件名
     */
    private String getFileName(String fileOriginName) {
        if (fileOriginName == null) {
            return null;
        }
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String suffix = fileOriginName.substring(fileOriginName.lastIndexOf("."));
        return fileName + suffix;
    }
}
