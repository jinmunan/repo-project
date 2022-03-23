package com.cy.store.controller;

import com.cy.common.utils.JsonResult;
import com.cy.store.entity.User;
import com.cy.store.service.UserService;
import com.cy.store.service.exception.*;
import jdk.nashorn.internal.runtime.FindProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 17:15
 */
@Slf4j
@RestController
@RequestMapping(value = "/users") //将用户注册功能放到user模块
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reg")
    //@ResponseBody 表示将响应结果以json格式传到前端
    public JsonResult<Void> reg(User user) {
        //创建响应结果的对象
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        /*向session对象中完成数据的绑定(session是全局的)*/
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        log.debug("设置用户的uid至session：" + String.valueOf(getUidFromSession(session)));
        log.debug("设置用户的username至session：" + getUsernameFromSession(session));
        return new JsonResult<User>(OK, user);
    }

    @RequestMapping(value = "/changePassword")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/getByUid")
    public JsonResult<User> getByUid(HttpSession session) {
        User user = userService.getById(getUidFromSession(session));
        return new JsonResult<User>(OK, user);
    }

    @RequestMapping(value = "/changeInfo")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /**
     * 设置上传文件的最大值
     */
    public static final int AVATAR_MAXSIZE = 10 * 1024 * 1024;

    /**
     * 限制上传文件
     */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping(value = "/changeAvatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        //判断文件是否为null
        if (file.isEmpty()) throw new FileEmptyException("文件为空");
        if (file.getSize() > AVATAR_MAXSIZE) throw new FileSizeException("文件大于10MB");
        if (!AVATAR_TYPE.contains(file.getContentType())) throw new FileTypeException("文件类型不支持");
        //上传的文件
        String realPath = session.getServletContext().getRealPath("/upload");

        //File对象执行这个路径
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();//创建目录
        }
        //获取到文件名,使用uuid生成新的文件名
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static";


        String originalFilename = file.getOriginalFilename();

        log.info("原始文件名为:" + originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toLowerCase(Locale.ROOT) + suffix;
        //创建一个空文件
        File dest = new File(dir, filename);
        //将file的数据写入空文件
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadException("文件读写异常");
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }


        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/" + filename;
        log.info("生成的路径为:" + avatar);
        userService.changeAvatar(uid, avatar, username);
        return new JsonResult<>(OK, avatar);
    }
}
