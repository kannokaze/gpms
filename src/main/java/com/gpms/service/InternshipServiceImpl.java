package com.gpms.service;

import com.gpms.mapper.*;
import com.gpms.mapper.complex.InternshipByContentMapper;
import com.gpms.po.*;
import com.gpms.po.complex.InternshipByContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class InternshipServiceImpl {

	@Autowired
	ContentMapper contentMapper;
	
	@Autowired
	ProgressMapper progressMapper;
	
	@Autowired
	ProjectMapper projectMapper;
	
	@Autowired
	TeamMapper teamMapper;
	
	@Autowired
	TeamMemberMapper teamMemberMapper;
	
	@Autowired
	InternshipByContentMapper internshipByContentMapper;

	@Autowired
	ConfigMapper configMapper;
	
	@Autowired
	AchievementMapper achievementMapper;
	
	@Autowired
	RelationStuComMapper relationStuComMapper;

	@Autowired
	JournalMapper journalMapper;
	/**
	 * 通过学号查询实习信息
	 * @param request
	 * @param stuNo
	 * @return
	 */
	public List<InternshipByContent> searchInternshipByStuNo(HttpServletRequest request,String stuNo) {
		return internshipByContentMapper.selectInternshipByStuNo(stuNo);
	}
	
	/**
	 * 通过企业号查询实习信息
	 * @param request
	 * @param key
	 * @return
	 */
	public List<InternshipByContent> selectInternshipByComAcc(
			HttpServletRequest request, String key) {
		
		return internshipByContentMapper.selectInternshipByComAccount(key);
	}

	/**
	 * 通过教师号查询实习信息
	 * @param request
	 * @param key
	 * @return
	 */
	public List<InternshipByContent> selectInternshipByTeaNo(
			HttpServletRequest request, String key) {
		
		return internshipByContentMapper.selectInternshipByTeaNo(key);
	}
	
	/**
	 * 通过学号查询成绩
	 * @param stuNo
	 * @return
	 */
	public List<Achievement> searchAchievement(String stuNo) {
		return achievementMapper.selectByStuNo(stuNo);
	}
	
	/**
	 * 通过教师号查询
	 * @param teaNo
	 * @return
	 */
	public List<Config> getDeadlineByTeaNo(String teaNo) {
		return configMapper.getDeadlineByTeaNo(teaNo);
	}

	public boolean setDeadline(String stage, Date date,String teaNo) {
		if (configMapper.setDeadline(stage, date, teaNo) > 0) {
			return true;
		}
		return false;
	}
	public boolean setDeadlineOnComment(String stage, Date date,String teaNo) {
		if (configMapper.setDeadlineOnComment(stage, date, teaNo) > 0) {
			return true;
		}
		return false;
	}
	
	public boolean changeDeadline(Config record) {
		if (configMapper.changeDeadline(record) > 0) {
			return true;
		}
		return false;
	}
	public boolean changeDeadlineOnComment(Config record) {
		if (configMapper.changeDeadlineOnComment(record) > 0) {
			return true;
		}
		return false;
	}
	public List<Config> getDeadline(String key) {
		return configMapper.getDeadline(key);
	}

	public List<Config> getDeadlineOnComment() {
		// TODO Auto-generated method stub
		return configMapper.getDeadlineOnComment();
	}
	public List<Config> getDeadlineOnComment(String stage) {
		return configMapper.getDeadlineOnCommentByStage(stage);
	}
	public int applyJoinCom(RelationStuCom record) {
		// TODO Auto-generated method stub
		return relationStuComMapper.insertSelective(record);
	}

	public int joinCom(RelationStuCom record) {
		// TODO Auto-generated method stub
		return relationStuComMapper.updateByComAccAndStuNo(record);
	}

	public int saveJournal(Journal journal) {
		return journalMapper.saveJour(journal);
	}

	public int saveJournalNew(Journal journal) {
		// TODO Auto-generated method stub
		return journalMapper.saveJourNew(journal);
	}

	public int upJournal(Journal journal) {
		// TODO Auto-generated method stub
		return journalMapper.upJour(journal);
	}

	public int upJournalNew(Journal journal) {
		// TODO Auto-generated method stub
		return journalMapper.upJourNew(journal);
	}

	public boolean createContent(Content content1) {
		if (contentMapper.insertSelective(content1) > 0) {
			return true;
		}
		return false;
	}

	public boolean changeContent(Content content1) {
		if (contentMapper.updateByPrimaryByStuNo(content1) > 0) {
			return true;
		}
		return false;
	}

	public boolean gradeStudentByTeaNo(String teaNo,String stuNo,String point,
			String comment) {
		BigDecimal bd=new BigDecimal(point);
		if (achievementMapper.gradeStudentByTeaNo(teaNo,stuNo,bd,comment) > 0) {
			return true;
		}
		return false;
	}

	public boolean gradeStudentByComAcc(String comAcc,String stuNo ,String point,
			String comment) {
		BigDecimal bd=new BigDecimal(point);
		if (achievementMapper.gradeStudentByComAcc(comAcc,stuNo,bd,comment) > 0) {
			return true;
		}
		return false;
	}

	public int upProject(Project project) {
		return projectMapper.updateByPrimaryKeySelective(project);
	}

	public int insertProject(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.insertSelective(project);
	}
	public int delProject(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.deleteByPrimaryKey(project.getProId());
	}
	public int upTeamMember(TeamMember teamMember) {
		// TODO Auto-generated method stub
		return teamMemberMapper.updateByPrimaryKeySelective(teamMember);
	}

	public int insertTeamMember(TeamMember teamMember) {
		return teamMemberMapper.insertSelective(teamMember);
	}

	public int delTeamMember(TeamMember teamMember) {
		return teamMemberMapper.deleteByPrimaryKey(teamMember.getTmId());
	}

	public int upProgress(Progress progress) {
		// TODO Auto-generated method stub
		return progressMapper.updateByPrimaryKeySelective(progress);
	}

	public int insertProgress(Progress progress) {
		// TODO Auto-generated method stub
		return progressMapper.insertSelective(progress);
	}

	public int delProgress(Progress progress) {
		// TODO Auto-generated method stub
		return progressMapper.deleteByPrimaryKey(progress.getProgId());
	}

	public int insertTeam(Team team) {
		
		return teamMapper.insertSelective(team);
	}

	public int gradeJournalByStuNo(Journal journal) {
		// TODO Auto-generated method stub
		return journalMapper.gradeJour(journal);
	}



	
	


}
