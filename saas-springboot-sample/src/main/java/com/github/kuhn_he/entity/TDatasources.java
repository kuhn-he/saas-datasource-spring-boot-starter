package com.github.kuhn_he.entity;

import java.io.Serializable;
import java.util.Date;

@lombok.Data
public class TDatasources implements Serializable{

	/**数据源标识**/
	private String dsKey;
	/**数据源地址**/
	private String dsUrl;
	/**数据源用户名**/
	private String dsUsername;
	/**数据源密码**/
	private String dsPassword;
	/**驱动类名**/
	private String driverClassName;
}
