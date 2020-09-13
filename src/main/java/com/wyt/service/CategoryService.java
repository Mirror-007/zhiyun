package com.wyt.service;

import java.util.List;

import com.wyt.entity.Category;

public interface CategoryService {

	//查询所有的类别信息
	public List<Category> queryAll();

	//根据填写的一级类别名查询是否已经存在
	public Category queryByName(String gradeName);

	//添加一级类别
	public void addFirstGrade(Category ca);

	//查询所有的一级类别
	public List<Category> queryAllGradeOne(int i);

	//添加二级类别
	public void addSecondGrade(Category ca);

	//根据二级类别名查询二级类别
	public Category queryBySecondName(String secName, int i);

	//根据二级类别名查询二级类别
	public void deleteOne(String aid);

	//查询所有二级类别
	public List<Category> queryAllGradeTwo();

	//前台：查询所属类别一样的二级类别
	public List<Category> queryGradeTwoBelongToOne(String parentCateName);

	public List<Category> queryByGrade1(String grade1,int startIndex , int endIndex);

}
