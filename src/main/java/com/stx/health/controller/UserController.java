package com.stx.health.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stx.health.common.R;
import com.stx.health.pojo.User;
import com.stx.health.pojo.UserLabel;
import com.stx.health.pojo.UserStatus;
import com.stx.health.service.UserLabelService;
import com.stx.health.service.UserService;
import com.stx.health.service.UserStatusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserLabelService userLabelService;

    @Resource
    private UserStatusService userStatusService;

    /**
     * 用户登录
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){return userService.login(request, user);}

    /**
     * 用户登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        return userService.logout(request);
    }

    /**
     * 用户注册
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    public R<String> saveUser(HttpServletRequest request, @RequestBody User user){return userService.saveUser(request, user);}

    /**
     * 用户头像添加或修改
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/saveAvatar")
    public R<String> saveAvatar(HttpServletRequest request, @RequestBody User user){return userService.saveAvatarByUserId(request, user);}

    /**
     * 更新目前的用户状态
     * @return
     */
    @GetMapping("/getUser")
    public R<User> getUserAgain(){return userService.selectUserById();}

    /**
     * 查询用户
     * @param page
     * @param pageSize
     * @param userName
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> selectUser(int page, int pageSize, String userName){return userService.UserQueryByUserName(page, pageSize, userName);}

    /**
     * 用户添加个人标签
     * @param name
     * @return
     */
    @PostMapping("/saveUserLabel")
    public R<String> saveUserLabel(String name){return userLabelService.saveUserLabel(name);}

    /**
     * 用户修改个人标签
     * @param userLabel
     * @return
     */
    @PostMapping("/updateUserLabel")
    public R<String> updateUserLabel(@RequestBody UserLabel userLabel){return userLabelService.updateUserLabelById(userLabel);}

    /**
     * 用户删除个人标签
     * @param id
     * @return
     */
    @DeleteMapping("/removeUserLabel")
    public R<String> removeUserLabel(Long id){return userLabelService.removeUserLabelByUserId(id);}

    /**
     * 获得用户标签
     * @return
     */
    @GetMapping("/getUserLabel")
    public R<List<UserLabel>> getAllTheUserLabels(){return userLabelService.getAllTheUserLabels();}

    /**
     * 发送好友请求
     * @param user1Id
     * @param user2Id
     * @return
     */
    @PostMapping("/sendUserStatus")
    public R<String> sendUserStatus(Long user1Id, Long user2Id){return userStatusService.addStatus(user1Id, user2Id);}

    /**
     * 获得已通过的好友
     * @return
     */
    @GetMapping("/getUserStatus2")
    public R<List<UserStatus>> getUserStatus2(){return userStatusService.getUserStatus2ByUserId();}

    /**
     * 获得正在通过验证或已拒绝的好友请求
     * @return
     */
    @GetMapping("/getUserStatus13")
    public R<List<UserStatus>> getUserStatus13(){return userStatusService.getUserStatus13ByUserId();}

    /**
     * 删除好友
     * @param userStatusId
     * @return
     */
    @DeleteMapping("/removeUserStatus")
    public R<String> removeUserStatus(Long userStatusId){return userStatusService.removeUserStatusByTowUserId(userStatusId);}

}
