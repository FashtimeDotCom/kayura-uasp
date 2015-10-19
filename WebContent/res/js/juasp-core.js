/**
 * 统一应用支撑平台脚本库.
 * 
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */

juasp = {
	version : "0.1.150202",
	siteUrl :  "/"
};

(function($, win) {
	
	
	/* 如果当前框架是顶层，就创建缓存对象 */
	if (win == win.top) {
		win.cache = new ClassMap();
	}
	
	/**
	 * 随机生成指定长度的字符串.默认为32位长度.
	 * 
	 * @param {Integer} len 可指定的字符长度.
	 */
	function newId(len) {
		var str = "", range = 32, arr = [ '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ];

		if ('number' == typeof len) {
			range = len;
		}
		
		for (var i = 0; i < range; i++) {
			var pos = Math.round(Math.random() * (arr.length - 1));
			str += arr[pos];
		}
		
		return str;
	}

	/**
	 * 获取当前页面的顶层窗口对象.
	 */
	function getTop() {
		return win.top;
	}
	
	/**
	 * 获取当前页面框架中的内容框架页对象.
	 * 若当前是顶层页将获得当前页对象.
	 */
	function getContent() {
		var ctt = win;

		if (ctt == win.top) {
			return ctt;
		}

		while (ctt.name != 'contentframe') {
			ctt = win.parent;
		}

		return ctt;
	}

	/**
	 * 获取顶层框架页标签的jQuery对象.
	 */
	function $top(tag) {
		return getTop().$(tag);
	}
	
	/**
	 * 获取内容框架页标签的jQuery对象.
	 */
	function $content(tag) {
		return getContent().$(tag);
	}

	/**
	 * 获取缓存对象值.
	 * 
	 * @param {String} key 缓存关键字.
	 * @param {Object} newValue 若指定了newValue将返回原值后,用新值替换缓存.若新值为 null 将仅移除原缓存值.
	 */
	function getCache(key, newValue) {

		var value = getTop().cache.get(key);

		if ('undefined' != typeof newValue) {
			removeCache(key);
			if (newValue != null) {
				setCache(key, newValue);
			}
		}

		return value;
	}

	/**
	 * 设置一个缓存对象.若该缓存Key值存在,将替换原缓存对象.
	 * @param {String} key 缓存关键字.
	 * @param {Object} value 缓存对象.
	 */
	function setCache(key, value) {
		getTop().cache.set(key, value);
	}

	/**
	 * 移除一个缓存对象.
	 * @param {String} key 缓存关键字.
	 */
	function removeCache(key) {
		getTop().cache.removeKey(key);
	}

	/**
	 * 获取当前页面中的查询参数值,若无该参数将返回null.
	 * @param {String} name 查询参数名称.
	 */
	function getQueryParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return (r[2]);
		return null;
	}

	/**
	 * 对一个url地址添加一个参数值,并返回新的url地址.
	 * @param {String} url 一个原url地址.
	 * @param {String} paramName 查询参数名称.
	 * @param {String} paramValue 查询参数值.
	 */
	function addUrlParam(url, paramName, paramValue) {
		var u = url;
		if (url != null && url.length > 0) {
			u = u + (url.indexOf("?") > 0 ? "&" : "?") + paramName + "="
					+ paramValue;
		}
		return u;
	}

	/** 绑定常用方法至引导对象 **/
	
	juasp.$top = $top;
	juasp.$content = $content;
	juasp.getCache = getCache;
	juasp.setCache = setCache;
	juasp.removeCache = removeCache;
	juasp.getQueryParam = getQueryParam;
	juasp.addUrlParam = addUrlParam;
	juasp.newId = newId;
	
	/**
	 * 用于跳转一个指定的URL地址，并可以指定其跳转模式.
	 * 
	 * @param {String} targetUrl 跳转目标地址.
	 * @param {Integer} mode 可选值: 0 当前页跳转(默认); 1 内容页跳转; 2 主页跳转.
	 * @param {String} returnUrl 可选值：可指定的跳转返回地址.
	 * @returns void
	 */
	juasp.skipUrl = function(targetUrl, mode, returnUrl) {

		var skipWin = win;

		if (mode == 1) {
			skipWin = getContent();
		} else if (mode == 2) {
			skipWin = getTop();
		}

		var url = targetUrl;
		if (returnUrl != null && returnUrl.length > 0) {
			url = url + (targetUrl.indexOf("?") > 0 ? "&" : "?") + "returnUrl=" + returnUrl;
		}

		skipWin.location.href = url;
	}
	
	/**
	 * 在任何页面中调用此方式，都可以在主框架页中，打开一个选项卡页面.
	 * 
	 * @param {String} title 选项卡标题名称.
	 * @param {String} url 指定打开的链接地址.
	 * @param {String} icon 选项卡图标(暂不可用).
	 */
	juasp.openTab = function(title, url, iconCls) {

		/* 取到选项卡的jquery对象 */
		var tab = $top("#mainTabs");

		/* 判断指定标题的选项卡是否存在,存在则显示它 */
		if (tab.tabs('exists', title)) {
			tab.tabs('select', title);
		} else {
			var content = '<iframe name="contentframe" scrolling="auto" frameborder="0" src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			tab.tabs('add', {
				title : title,
				content : content,
				iconCls : iconCls,
				closable : true
			});
			tab.find(".panel-body").last().css("overflow", "hidden");
		}
	}
	
	juasp.openWin = function(opts, events){
		
		var wid = newId();

		var w = $top('<div/>');
		setCache("wid_" + wid, w);
		
		w.window({
		    title: opts.title,
		    width: opts.width,
		    height: opts.height,
		    shadow: true,
		    modal: true,
		    iconCls: opts.iconCls,
		    closed: true,
		    minimizable: false,
		    onClose : function(e){
				removeCache("wid_" + wid);
				var result = getCache("win_result_" + wid, null);
		    	events.onclose(result);
		    }
		});
		
		var url = addUrlParam(opts.url, '_wid', wid);
		w.append('<iframe scrolling="auto" frameborder="0" src="' + url + '" ' 
				+ 'style="width:100%;height:100%;"></iframe>');
		w.window('open');
	}
	
	juasp.closeWin = function(result) {
		var wid = getQueryParam("_wid");
		if (wid != null) {
			var w = getCache("wid_" + wid, null);
			setCache("win_result_" + wid, result);
			if (w != null) {
				w.window('close');
			}
		}
	}
	
	juasp.confirm = function(title, onclose) {
		$.messager.confirm('确认', title, onclose);  
	}
	
	juasp.hint = function(opts) {
		
		win.top.$.messager.show({
			title: '消息',
			msg: opts.msg,
			timeout: 5000,
			showType: 'slide'
		});
	}
	
}(jQuery, window));


/**
 * 定义一个 Key,Value 的集合类型.
 */
function ClassMap() {

	this.map = new Array();

	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};

	this.get = function(key) {
		for (var i = 0; i < this.map.length; i++) {
			if (this.map[i].key === key) {
				return this.map[i].value;
			}
		}
		return null;
	};

	this.set = function(key, value) {
		for (var i = 0; i < this.map.length; i++) {
			if (this.map[i].key === key) {
				this.map[i].value = value;
				return;
			}
		}
		this.map[this.map.length] = new struct(key, value);
	};

	this.removeKey = function(key) {
		var v;
		for (var i = 0; i < this.map.length; i++) {
			v = this.map.pop();
			if (v.key === key)
				continue;

			this.map.unshift(v);
		}
	};

	this.getCount = function() {
		return this.map.length;
	};

	this.isEmpty = function() {
		return this.map.length <= 0;
	};
}