package com.freepaay.manage.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "TB_ADMIN_DETAILS")
public class TbAdminDetails implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3403350193021012424L;

	@Id
    @Column(name = "ADMIN_DETAILS_KEY")
    private String adminDetailsKey;

    @Column(name = "ADMIN_KEY")
    private String adminKey;

    @Column(name = "ADMIN_CHINESE_NAME")
    private String adminChineseName;

    @Column(name = "ADMIN_MOBILE")
    private String adminMobile;

    @Column(name = "ADMIN_QQ")
    private String adminQq;

    @Column(name = "ADMIN_WEIXIN")
    private String adminWeixin;

    @Column(name = "LAST_ACCESS_TIME")
    private Date lastAccessTime;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return ADMIN_DETAILS_KEY
     */
    public String getAdminDetailsKey() {
        return adminDetailsKey;
    }

    /**
     * @param adminDetailsKey
     */
    public void setAdminDetailsKey(String adminDetailsKey) {
        this.adminDetailsKey = adminDetailsKey;
    }

    /**
     * @return ADMIN_KEY
     */
    public String getAdminKey() {
        return adminKey;
    }

    /**
     * @param adminKey
     */
    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    /**
     * @return ADMIN_CHINESE_NAME
     */
    public String getAdminChineseName() {
        return adminChineseName;
    }

    /**
     * @param adminChineseName
     */
    public void setAdminChineseName(String adminChineseName) {
        this.adminChineseName = adminChineseName;
    }

    /**
     * @return ADMIN_MOBILE
     */
    public String getAdminMobile() {
        return adminMobile;
    }

    /**
     * @param adminMobile
     */
    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }

    /**
     * @return ADMIN_QQ
     */
    public String getAdminQq() {
        return adminQq;
    }

    /**
     * @param adminQq
     */
    public void setAdminQq(String adminQq) {
        this.adminQq = adminQq;
    }

    /**
     * @return ADMIN_WEIXIN
     */
    public String getAdminWeixin() {
        return adminWeixin;
    }

    /**
     * @param adminWeixin
     */
    public void setAdminWeixin(String adminWeixin) {
        this.adminWeixin = adminWeixin;
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