package com.gpms.mapper;

import com.gpms.po.Company;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer comId);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer comId);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    List<Company> selectByAccount(String comAccount);
    
    int agreeCompany(String account);
    
    List<Company> selectAll();
    
    List<Company> selectAllByKey(Company company);
    
    Company selectByAccountAndPwd(String account,String pwd);


	int updateCompanyInfoByComAcc(Company company);
}