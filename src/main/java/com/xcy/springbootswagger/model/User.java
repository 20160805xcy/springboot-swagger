package com.xcy.springbootswagger.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "user_name")
    @ApiModelProperty(value = "姓名")
    private String userName;

    @Column(name = "user_age")
    @ApiModelProperty(value = "年龄")
    private Integer userAge;

    @Column(name = "user_address")
    @ApiModelProperty(value = "地址")
    private String userAddress;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return user_name - 姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置姓名
     *
     * @param userName 姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取年龄
     *
     * @return user_age - 年龄
     */
    public Integer getUserAge() {
        return userAge;
    }

    /**
     * 设置年龄
     *
     * @param userAge 年龄
     */
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    /**
     * 获取地址
     *
     * @return user_address - 地址
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置地址
     *
     * @param userAddress 地址
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}