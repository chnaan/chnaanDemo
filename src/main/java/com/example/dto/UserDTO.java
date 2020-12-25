package com.example.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 *
 * @author chaonan.xu
 * @version: 1.0
 * @date 2020/12/18 14:30
 */
@Data
public class UserDTO {

    int id;

    String name;

    long age;

    Timestamp createTime;
}
