package com.freepaay.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freepaay.exception.FreepaayException;
import com.freepaay.manage.bean.TbUserDetails;
import com.freepaay.manage.mapper.TbUserDetailsMapper;
import com.freepaay.manage.service.TbUserDetailsService;
import com.freepaay.redis.dao.RedisDao;

/**
 * 商品大类操作实现
 * 
 * @author 福福
 * @data 2016年5月18日
 */
@Service(value = "tbUserDetailsService")
public class TbUserDetailsServiceImpl implements TbUserDetailsService {
	@Autowired
	private TbUserDetailsMapper tbUserDetailsMapper;
	@Autowired
	private RedisDao<TbUserDetails> redisDao;
	@Autowired
	private RedisDao<List<TbUserDetails>> redisDaoList;
	
	public TbUserDetails getTbUserDetailsById(String userKey) throws FreepaayException {
		TbUserDetails tbUserDetails = null;
		tbUserDetails = redisDao.getObject("manage_TbUserDetails_userKey_" + userKey);// 从缓存中读取
		if (tbUserDetails == null) {
			TbUserDetails tbUserDetailsnew = new TbUserDetails();
			tbUserDetailsnew.setUserKey(userKey);
			tbUserDetails = tbUserDetailsMapper.selectOne(tbUserDetailsnew);
			redisDao.setObject("manage_TbUserDetails_userKey_" + userKey,tbUserDetails); // 删除redis缓存
			System.out.println("============Set Redis " + "manage_TbUserDetails_userKey_" + userKey + "============");
		}else{
			System.out.println("============Get Redis " + "manage_TbUserDetails_userKey_" + userKey + "============");
		}
		return tbUserDetails;
	}

	@Override
	public int delTbUserDetails(String userKey) throws FreepaayException {
		int i = 0;
		TbUserDetails tbUserDetails = new TbUserDetails();
		tbUserDetails.setUserKey(userKey);
		i = tbUserDetailsMapper.delete(tbUserDetails);
		redisDao.deleteObject("TbUserDetails_userKey_" + userKey);// 从缓存中读取
		System.out.println("============Delete Redis " + "manage_TbUserDetails_userKey_" + userKey + "============");
		listClearCache();
		return i;
	}

	@Override
	public int insertTbUserDetails(TbUserDetails tbUserDetails) throws FreepaayException {
		listClearCache();
		return tbUserDetailsMapper.insert(tbUserDetails);
	}

	@Override
	public int updateTbUserDetails(TbUserDetails tbUserDetails) throws FreepaayException {
		listClearCache();
		redisDao.setObject("manage_TbUserDetails_userKey_" + tbUserDetails.getUserKey(),tbUserDetails); // 删除redis缓存
		System.out.println("============Set Redis " + "manage_TbUserDetails_userKey_" + tbUserDetails.getUserKey() + "============");
		return tbUserDetailsMapper.updateByPrimaryKey(tbUserDetails);
	}

	@Override
	public List<TbUserDetails> list() throws FreepaayException {
		List<TbUserDetails> tbUserDetailsList = null;
		tbUserDetailsList = (List<TbUserDetails>) redisDaoList.getObject("manage_TbUserDetailsList");// 从缓存中读取
		if (tbUserDetailsList == null) {
			tbUserDetailsList = tbUserDetailsMapper.selectAll();
			redisDaoList.setObject("manage_TbUserDetailsList", tbUserDetailsList); // 写入redis缓存
			System.out.println("============Set Redis " + "manage_TbUserDetailsList" + "============");
		}else{
			System.out.println("============Get Redis " + "manage_TbUserDetailsList" + "============");
		}
		return tbUserDetailsList;
	}
	
	@Override
	public List<TbUserDetails> listNoCache() throws FreepaayException {
		List<TbUserDetails> tbUserDetailsList= tbUserDetailsMapper.selectAll();
		redisDaoList.setObject("manage_TbUserDetailsList", tbUserDetailsList);
		return tbUserDetailsList;
	}

	public void listClearCache() throws FreepaayException{
		redisDao.deleteObject("TbUserDetailsList");
		System.out.println("============Delete Redis " + "manage_TbUserDetailsList" + "============");
	}
	
}
