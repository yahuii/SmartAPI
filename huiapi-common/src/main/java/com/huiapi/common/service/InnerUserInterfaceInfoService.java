package com.huiapi.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huiapi.common.model.entity.InterfaceInfo;
import com.huiapi.common.model.entity.User;
import com.huiapi.common.model.entity.UserInterfaceInfo;

/**
* @author 顾琴
* @description 针对表【user_interface_info(接口信息)】的数据库操作Service
* @createDate 2024-05-15 19:55:56
*/
public interface InnerUserInterfaceInfoService{


    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}