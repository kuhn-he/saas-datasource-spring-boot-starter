package com.github.kuhn_he.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.kuhn_he.entity.TAccount;
import com.github.kuhn_he.mapper.TAccountMapper;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private TAccountMapper tAccountMapper;

	@RequestMapping(value="/name",method={RequestMethod.GET})
	@ResponseBody
	public TAccount getAccount(@RequestParam(value = "name", required = true) String name) {
		return tAccountMapper.selectOne(new QueryWrapper<TAccount>().eq("name",name));
	}
}
