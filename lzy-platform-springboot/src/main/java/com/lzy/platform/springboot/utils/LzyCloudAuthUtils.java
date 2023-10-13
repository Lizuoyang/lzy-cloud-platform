package com.lzy.platform.springboot.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.lzy.platform.base.constant.AuthConstant;
import com.lzy.platform.base.domain.LzyCloudUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @ClassName LzyCloudAuthUtils
 * @Description 鉴权工具类
 * @Author LiZuoYang
 * @Date 2022/6/17 15:30
 **/
@Slf4j
public class LzyCloudAuthUtils {
    /**
     * 获取当前用户
     *
     * @return {@link LzyCloudUser}
     */
    public static LzyCloudUser getCurrentUser() {
        try {
            // 获取当前request请求对象
            HttpServletRequest request = LzyWebUtils.getRequest();
            if (ObjectUtil.isNull(request)) {
                return null;
            }
            // 获取请求头中的user信息
            String user = request.getHeader(AuthConstant.HEADER_USER);
            if (StrUtil.isEmpty(user)) {
                return null;
            }
            // 进行解码
            String decodeUserStr = URLDecoder.decode(user, "UTF-8");
            LzyCloudUser cloudUser = JSONUtil.toBean(decodeUserStr, LzyCloudUser.class);
            return cloudUser;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
