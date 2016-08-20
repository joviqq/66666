package com.freepaay.manage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TB_USER_DETAILS")
public class TbUserDetails implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6852504872009746367L;

	@Id
    @Column(name = "USER_DETAILS_KEY")
    private String userDetailsKey;

    @Column(name = "USER_KEY")
    private String userKey;

    @Column(name = "USER_CHINESE_NAME")
    private String userChineseName;

    @Column(name = "USER_MOBILE")
    private String userMobile;

    @Column(name = "USER_COUNTRY")
    private String userCountry;

    @Column(name = "USER_PROVINCE")
    private String userProvince;

    @Column(name = "USER_CITY")
    private String userCity;

    @Column(name = "USER_ADDRESS")
    private String userAddress;

    @Column(name = "USER_QQ")
    private String userQq;

    @Column(name = "USER_WEIXIN")
    private String userWeixin;

    @Column(name = "LAST_ACCESS_TIME")
    private Date lastAccessTime;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return USER_DETAILS_KEY
     */
    public String getUserDetailsKey() {
        return userDetailsKey;
    }

    /**
     * @param userDetailsKey
     */
    public void setUserDetailsKey(String userDetailsKey) {
        this.userDetailsKey = userDetailsKey;
    }

    /**
     * @return USER_KEY
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * @return USER_CHINESE_NAME
     */
    public String getUserChineseName() {
        return userChineseName;
    }

    /**
     * @param userChineseName
     */
    public void setUserChineseName(String userChineseName) {
        this.userChineseName = userChineseName;
    }

    /**
     * @return USER_MOBILE
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * @param userMobile
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * @return USER_COUNTRY
     */
    public String getUserCountry() {
        return userCountry;
    }

    /**
     * @param userCountry
     */
    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    /**
     * @return USER_PROVINCE
     */
    public String getUserProvince() {
        return userProvince;
    }

    /**
     * @param userProvince
     */
    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    /**
     * @return USER_CITY
     */
    public String getUserCity() {
        return userCity;
    }

    /**
     * @param userCity
     */
    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    /**
     * @return USER_ADDRESS
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * @param userAddress
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * @return USER_QQ
     */
    public String getUserQq() {
        return userQq;
    }

    /**
     * @param userQq
     */
    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    /**
     * @return USER_WEIXIN
     */
    public String getUserWeixin() {
        return userWeixin;
    }

    /**
     * @param userWeixin
     */
    public void setUserWeixin(String userWeixin) {
        this.userWeixin = userWeixin;
    }

    /**
     * @return LAST_ACCESS_TIME
     */
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * @param lastAccessTime
     */
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}