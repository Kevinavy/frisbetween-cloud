package com.kevinavy.authenticationcenter.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevinavy.authenticationcenter.security.model.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SecurityPermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT" +
            "  A.* " +
            "FROM" +
            "  permission A" +
            "  JOIN request_permission_relation B ON A.id = B.permission_id" +
            "  JOIN request C ON B.request_id = C.id " +
            "WHERE" +
            "  C.url = #{requestPath}")
    List<Permission> getPermissionByRequestPath(String requestPath);

    @Select("SELECT" +
            "  url " +
            "FROM" +
            "  request")
    List<String> getRequestPathNeedPermission();
}
