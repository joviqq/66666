package com.freepaay.manage.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/8/20.
 */
public class LongIdObject implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2819397091091224403L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select TB_CUS_USER_SEQ.nextval from dual")
    private Long id;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
