package com.example.mapper;

import com.example.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/18 12:19
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where id=#{id} ")
    UserDTO selectById(long id);

    int insertUser(@Param("user") UserDTO user);
}
