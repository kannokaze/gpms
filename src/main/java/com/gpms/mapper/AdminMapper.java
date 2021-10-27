package com.gpms.mapper;

import com.gpms.po.Admin;

import java.util.List;

public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);
    
    Admin searchByAccount(String account);

	Admin selectByAccountAndPwd(String account, String pwd);

	List<Admin> searchAdminInfoByAdminAcc(String comAcc);

	int updateAdminInfoByAdminAcc(Admin admin);
}