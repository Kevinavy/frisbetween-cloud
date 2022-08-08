package com.kevinavy.authenticationcenter.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevinavy.authenticationcenter.security.model.Permission;
import com.kevinavy.authenticationcenter.security.model.Role;
import com.kevinavy.authenticationcenter.security.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SecurityUserMapper extends BaseMapper<User> {
    @Select("SELECT" +
            "  * " +
            "FROM" +
            "  user " +
            "WHERE" +
            "  username = #{username}")
    User findUserByUsername(String username);

    @Select("SELECT" +
            "  A.* " +
            "FROM" +
            "  role A" +
            "  JOIN user_role_relation B ON A.id = B.role_id" +
            "  JOIN user C ON C.id = B.user_id " +
            "WHERE" +
            "  C.username = #{username}")
    List<Role> findRoleByUsername(String username);

    @Select("SELECT" +
            "  A.* " +
            "FROM" +
            "  permission A" +
            "  JOIN role_permission_relation B ON A.id = B.permission_id" +
            "  JOIN role C ON C.id = B.role_id" +
            "  JOIN user_role_relation D ON C.id = D.role_id" +
            "  JOIN user E ON E.id = D.user_id " +
            "WHERE" +
            "  E.username = #{username}")
    List<Permission> findPermissionsByUsername(String username);

    @Update("UPDATE user" +
            "SET " +
            "  username = #{username}," +
            "  password = #{password} ," +
            "  last_login_time = #{lastLoginTime}" +
            "  create_time = #{createTime}" +
            "  update_time = #{updateTime}" +
            "  is_delete = #{isDelete}" +
            "  is_locked = #{isLocked}" +
            "WHERE" +
            "  id = #{id}")
    Integer updateUser(User user);




}
