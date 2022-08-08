package com.kevinavy.authenticationcenter.mapper;

//import com.kevinavy.competitionsys.model.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kevinavy.authenticationcenter.security.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE user SET last_login_time = #{loginTime} WHERE username = #{username}")
    void updateLoginTime(String username, Timestamp loginTime);

    @Update("UPDATE user SET is_delete = #{deleteFlag} WHERE username = #{username}")
    void updateIsDelete(String username, Integer deleteFlag);

    @Update("UPDATE user SET password = #{password} WHERE username = #{username}")
    void updatePassword(String username, String password);

    @Update("UPDATE user SET password = #{name} WHERE username = #{username}")
    void updateName(String username, String name);

    @Update("UPDATE user SET password = #{clazz} WHERE username = #{username}")
    void updateClazz(String username, String clazz);

    @Update("UPDATE user SET password = #{institute} WHERE username = #{username}")
    void updateInstitute(String username, String institute);

    @Insert("INSERT INTO user_role_relation(user_id, role_id) VALUES (#{id}, 5)")
    void setCommonRole(Integer id);

//    @Select("SELECT * FROM user WHERE username = #{username}")
//    User findUserByUsername(String username);
}
