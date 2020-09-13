package com.wyt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wyt.entity.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryDao extends Mapper<Category> {

	//查询所有的类别信息
	public List<Category> queryAll();

	//根据填写的一级类别名查询是否已经存在
	public Category queryByName(String gradeName);

	//添加一级类别
	public void insertFirstGrade(Category ca);

	//查询所有的一级类别
	public List<Category> queryAllGradeOne(int i);

	//添加二级类别
	public void insertSecondGrade(Category ca);

	//根据二级类别名查询二级类别
	public Category queryBySecondName(@Param("name") String secName, @Param("i") int i);

	//删除一个类别
	public void deleteOne(String aid);

	//查询所有二级类别
	public List<Category> queryAllGradeTwo();

	//前台：查询所属类别一样的二级类别
	public List<Category> queryGradeTwoBelongToOne(String parentCateName);

	public List<Category> queryByGrade1(@Param("yiji") String grade1, @Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

}
