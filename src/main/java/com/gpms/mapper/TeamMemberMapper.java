package com.gpms.mapper;

import com.gpms.po.TeamMember;

public interface TeamMemberMapper {
    int deleteByPrimaryKey(Integer tmId);

    int insert(TeamMember record);

    int insertSelective(TeamMember record);

    TeamMember selectByPrimaryKey(Integer tmId);

    int updateByPrimaryKeySelective(TeamMember record);

    int updateByPrimaryKey(TeamMember record);
}