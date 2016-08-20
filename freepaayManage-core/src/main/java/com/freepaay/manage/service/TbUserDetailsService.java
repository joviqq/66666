
package com.freepaay.manage.service;

import java.util.List;

import com.freepaay.exception.FreepaayException;
import com.freepaay.manage.bean.TbUserDetails;

/**
 * 
 * 用户相关操作接口
 * @author  rcb
 * @data  2016年3月30日
 */

public interface TbUserDetailsService
{
   
	TbUserDetails getTbUserDetailsById(String warekindKey) throws FreepaayException;
	
	int delTbUserDetails(String warekindKey) throws FreepaayException;

	int insertTbUserDetails(TbUserDetails TbUserDetails) throws FreepaayException;

	int updateTbUserDetails(TbUserDetails TbUserDetails) throws FreepaayException;
	
    List<TbUserDetails> list() throws FreepaayException ;

    List<TbUserDetails> listNoCache() throws FreepaayException ;
}
