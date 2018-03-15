/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package net.xtlog.system.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xtlog.system.test.entity.TestData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import net.xtlog.system.test.service.TestDataService;

/**
 * 测试数据Controller
 * @author ThinkGem
 * @version 2018-02-07
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testData")
public class TestDataController extends BaseController {

	@Autowired
	private TestDataService testDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TestData get(String id, boolean isNewRecord) {
		return testDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:testData:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestData testData, Model model) {
		model.addAttribute("testData", testData);
		return "modules/test/testDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:testData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TestData> listData(TestData testData, HttpServletRequest request, HttpServletResponse response) {
		Page<TestData> page = testDataService.findPage(new Page<TestData>(request, response), testData); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:testData:view")
	@RequestMapping(value = "form")
	public String form(TestData testData, Model model) {
		model.addAttribute("testData", testData);
		return "modules/test/testDataForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("test:testData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TestData testData) {
		testDataService.save(testData);
		return renderResult(Global.TRUE, "保存数据成功！");
	}
	
	/**
	 * 停用数据
	 */
	@RequiresPermissions("test:testData:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(TestData testData) {
		testData.setStatus(TestData.STATUS_DISABLE);
		testDataService.updateStatus(testData);
		return renderResult(Global.TRUE, "停用数据成功");
	}
	
	/**
	 * 启用数据
	 */
	@RequiresPermissions("test:testData:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(TestData testData) {
		testData.setStatus(TestData.STATUS_NORMAL);
		testDataService.updateStatus(testData);
		return renderResult(Global.TRUE, "启用数据成功");
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("test:testData:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TestData testData) {
		testDataService.delete(testData);
		return renderResult(Global.TRUE, "删除数据成功！");
	}
	
}