package com.wyt.service;

import java.util.List;

import com.wyt.dao.CategoryDao;
import com.wyt.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	//查询所有的类别信息
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Category> queryAll() {
		List<Category> list = categoryDao.queryAll();
		return list;
	}

	//根据填写的一级类别名查询是否已经存在
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Category queryByName(String gradeName) {
		Category cate = categoryDao.queryByName(gradeName);
		return cate;
	}

	//添加一级类别
	@Override
	public void addFirstGrade(Category ca) {
		categoryDao.insertFirstGrade(ca);
	}

	//查询所有的一级类别
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Category> queryAllGradeOne(int i) {
		List<Category> list = categoryDao.queryAllGradeOne(i);
		return list;
	}

	//添加二级类别
	@Override
	public void addSecondGrade(Category ca) {
		categoryDao.insertSecondGrade(ca);
	}

	//根据二级类别名查询二级类别
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Category queryBySecondName(String secName, int i) {
		Category cate = categoryDao.queryBySecondName(secName,i);
		return cate;
	}

	//删除一个类别
	@Override
	public void deleteOne(String aid) {
		categoryDao.deleteOne(aid);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Category> queryAllGradeTwo() {
		List<Category> list = categoryDao.queryAllGradeTwo();
		return list;
	}

	//前台：查询所属类别一样的二级类别
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Category> queryGradeTwoBelongToOne(String parentCateName) {
		List<Category> list = categoryDao.queryGradeTwoBelongToOne(parentCateName);
		return list;
	}


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Category> queryByGrade1(String grade1 , int startIndex , int endIndex) {
		List<Category> bk = categoryDao.queryByGrade1(grade1 , startIndex,endIndex);
		return bk;
	}


}
