package com.shirologin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author pengyong
 * @date 2020/5/10 - 13:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserBean {

    private int id;
    private String name;
    private String password;
    private String perms;

}
