
package com.freepaay.manage.service;

import java.util.List;

import com.freepaay.exception.FreepaayException;
import com.freepaay.manage.bean.TbUser;

/**
 * 
 * 用户相关操作接口
 * @author  rcb
 * @data  2016年3月30日
 */

public interface TbUserService
{
   
	TbUser getTbUserById(String warekindKey) throws FreepaayException;
	
	int delTbUser(String warekindKey) throws FreepaayException;

	int insertTbUser(TbUser tbUser) throws FreepaayException;

	int updateTbUser(TbUser tbUser) throws FreepaayException;
	
    List<TbUser> list() throws FreepaayException ;

    List<TbUser> listNoCache() throws FreepaayException ;
}
