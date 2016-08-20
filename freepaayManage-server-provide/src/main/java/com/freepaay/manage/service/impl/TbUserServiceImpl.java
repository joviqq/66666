package com.freepaay.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freepaay.exception.FreepaayException;
import com.freepaay.manage.bean.TbUser;
import com.freepaay.manage.mapper.TbUserMapper;
import com.freepaay.manage.service.TbUserService;
import com.freepaay.redis.dao.RedisDao;

/**
 * 商品大类操作实现
 * 
 * @author 福福
 * @data 2016年5月18日
 */
@Service(value = "tbUserService")
public class TbUserServiceImpl implements TbUserService {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private RedisDao<TbUser> redisDao;
	@Autowired
	private RedisDao<List<TbUser>> redisDaoList;
	
	public TbUser getTbUserById(String userKey) throws FreepaayException {
		TbUser tbUser = null;
		tbUser = redisDao.getObject("manage_TbUser_userKey_" + userKey);// 从缓存中读取
		if (tbUser == null) {
			TbUser tbUsernew = new TbUser();
			tbUsernew.setUserKey(userKey);
			tbUser = tbUserMapper.selectOne(tbUsernew);
			redisDao.setObject("manage_TbUser_userKey_" + userKey,tbUser); // 删除redis缓存
			System.out.println("============Set Redis " + "manage_TbUser_userKey_" + userKey + "============");
		}else{
			System.out.println("============Get Redis " + "manage_TbUser_userKey_" + userKey + "============");
		}
		return tbUser;
	}

	@Override
	public int delTbUser(String userKey) throws FreepaayException {
		int i = 0;
		TbUser tbUser = new TbUser();
		tbUser.setUserKey(userKey);
		i = tbUserMapper.delete(tbUser);
		redisDao.deleteObject("TbUser_userKey_" + userKey);// 从缓存中读取
		System.out.println("============Delete Redis " + "manage_TbUser_userKey_" + userKey + "============");
		listClearCache();
		return i;
	}

	@Override
	public int insertTbUser(TbUser tbUser) throws FreepaayException {
		listClearCache();
		return tbUserMapper.insert(tbUser);
	}

	@Override
	public int updateTbUser(TbUser tbUser) throws FreepaayException {
		listClearCache();
		redisDao.setObject("manage_TbUser_userKey_" + tbUser.getUserKey(),tbUser); // 删除redis缓存
		System.out.println("============Set Redis " + "manage_TbUser_userKey_" + tbUser.getUserKey() + "============");
		return tbUserMapper.updateByPrimaryKey(tbUser);
	}

	@Override
	public List<TbUser> list() throws FreepaayException {
		List<TbUser> tbUserList = null;
		tbUserList = (List<TbUser>) redisDaoList.getObject("manage_TbUserList");// 从缓存中读取
		if (tbUserList == null) {
			tbUserList = tbUserMapper.selectAll();
			redisDaoList.setObject("manage_TbUserList", tbUserList); // 写入redis缓存
			System.out.println("============Set Redis " + "manage_TbUserList" + "============");
		}else{
			System.out.println("============Get Redis " + "manage_TbUserList" + "============");
		}
		return tbUserList;
	}
	
	@Override
	public List<TbUser> listNoCache() throws FreepaayException {
		List<TbUser> tbUserList= tbUserMapper.selectAll();
		redisDaoList.setObject("manage_TbUserList", tbUserList);
		return tbUserList;
	}

	public void listClearCache() throws FreepaayException{
		redisDao.deleteObject("TbUserList");
		System.out.println("============Delete Redis " + "manage_TbUserList" + "============");
	}
	
}
