package com.freepaay.manage.bean;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "TB_ADMIN")
public class TbAdmin implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8407329061302301889L;

	@Id
    @Column(name = "ADMIN_KEY")
    private String adminKey;

    @Column(name = "ADMIN_NAME")
    private String adminName;

    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;

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
     * @return ADMIN_NAME
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * @return ADMIN_PASSWORD
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * @param adminPassword
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}