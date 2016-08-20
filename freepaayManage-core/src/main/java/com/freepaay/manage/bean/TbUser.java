package com.freepaay.manage.bean;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "TB_USER")
public class TbUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1712533377174749198L;

	@Id
    @Column(name = "USER_KEY")
    private String userKey;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

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
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return USER_PASSWORD
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}