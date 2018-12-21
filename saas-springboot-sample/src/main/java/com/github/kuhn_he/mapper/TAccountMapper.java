package com.github.kuhn_he.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.kuhn_he.entity.TAccount;
import com.github.kuhn_he.saas.ds.annotation.SAAS;

@SAAS("x-datasource-key")
@Mapper
public interface TAccountMapper extends BaseMapper<TAccount> {

}
