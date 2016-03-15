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
	
	/* 计算站点根路径 */
	var hostPath = win.location.href.substring(0, win.location.href.indexOf(win.location.pathname));
	var projectName = win.location.pathname.substring(0, win.location.pathname.substr(1).indexOf('/') + 1);
	juasp.root = hostPath + projectName;
	
	/* 如果当前框架是顶层，就创建缓存对象 */
	if (win == win.top) {
		win.cache = new ClassMap();
	}
	
	/**
	 * 随机生成指定长度的字符串.默认为32位长度.
	 * 
	 * @param {Integer} len 可指定的字符长度.
	 */
	function _newId(len) {
		var str = "", range = 32, arr = [ '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 
				'V', 'W', 'X', 'Y', 'Z' ];

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
	 * 将文件大小数据量转换为 KB,MB... 显示方式.
	 */
	function _bytesToSize(bytes) {
		
	    if (bytes === 0) return '0 B';
	    var k = 1024,
	        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
	        i = Math.floor(Math.log(bytes) / Math.log(k));

	   return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
	}
	
	/**
	 * 获取当前页面的顶层窗口对象.
	 */
	function _getTop() {
		return win.top;
	}
	
	/**
	 * 获取当前页面框架中的内容框架页对象.
	 * 若当前是顶层页将获得当前页对象.
	 */
	function _getContent() {
		
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
		
		return _getTop().$(tag);
	}
	
	/**
	 * 获取内容框架页标签的jQuery对象.
	 */
	function $content(tag) {
		
		return _getContent().$(tag);
	}

	/**
	 * 获取缓存对象值.
	 * 
	 * @param {String} key 缓存关键字.
	 * @param {Object} newValue 若指定了newValue将返回原值后,用新值替换缓存.若新值为 null 将仅移除原缓存值.
	 */
	function _getCache(key, newValue) {

		var value = _getTop().cache.get(key);

		if ('undefined' != typeof newValue) {
			_removeCache(key);
			if (newValue != null) {
				_setCache(key, newValue);
			}
		}

		return value;
	}

	/**
	 * 设置一个缓存对象.若该缓存Key值存在,将替换原缓存对象.
	 * @param {String} key 缓存关键字.
	 * @param {Object} value 缓存对象.
	 */
	function _setCache(key, value) {
		
		_getTop().cache.set(key, value);
	}

	/**
	 * 移除一个缓存对象.
	 * @param {String} key 缓存关键字.
	 */
	function _removeCache(key) {
		
		_getTop().cache.removeKey(key);
	}

	/**
	 * 获取当前页面中的查询参数值,若无该参数将返回null.
	 * @param {String} name 查询参数名称.
	 */
	function _getQueryParam(name) {
		
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		return (r != null) ? (r[2]) : null;
	}

	/**
	 * 对一个url地址添加一个参数值,并返回新的url地址.
	 * @param {String} url 一个原url地址.
	 * @param {String} paramName 查询参数名称.
	 * @param {String} paramValue 查询参数值.
	 */
	function _addUrlParam(url, paramName, paramValue) {
		
		var u = url;
		if (url != null && url.length > 0) {
			u = u + (url.indexOf("?") > 0 ? "&" : "?") + paramName + "="
					+ paramValue;
		}
		return u;
	}
	
	function _isEmpty(value){
		
		return value == undefined || value == null || value == "";
	}

	/** 绑定常用方法至引导对象 **/
	
	juasp.$top = $top;
	juasp.$content = $content;
	juasp.getCache = _getCache;
	juasp.setCache = _setCache;
	juasp.removeCache = _removeCache;
	juasp.getQueryParam = _getQueryParam;
	juasp.addUrlParam = _addUrlParam;
	juasp.newId = _newId;
	juasp.isEmpty = _isEmpty;
	juasp.bytesToSize = _bytesToSize;
	
	juasp.SUCCESS = "success";
	juasp.ERROR = "error";
	juasp.FAILED = "failed";
	
	/**
	 * 以 POST 方式请求一个处理.
	 * 
	 * @param {url} 请求处理的地址.
	 * @param {data} 请求传入的数据. 
	 * @param {events} events 请求的回调事件. 支持: error, complete, success, failure.
	 */
	function _post(url, data, events){
		
		var cfg = { 'url': url, 'data': data, dataType: 'json' };
		
		if('undefined' == typeof events) { events = {}; }

		$.ajax({url: cfg.url,
	            type: "POST",
	            data: cfg.data,
	            dataType : cfg.dataType,
	            beforeSend : function(){
	            	// 发消命令前事件.
	            },
	            success : function(result, status){
	            	
		        	/* 先执行完成回调事件,根据返回值决定是否执行后续代码. */
		        	if(typeof events.complete == 'function'){
		        		if(!events.complete(result)) return;
		        	}
		        	
		        	/* 系统返回执行异常时的处理,为未预期异常类型. */
		        	if(result.type == 'error'){
		        		if(typeof events.error == 'function'){
		        			events.error(result);
		        		} else {
		        			_info("异常", result.message);
		        		}
		        		return;
		        	}

		        	/* 处理执行成功时的事件,若未指定事件,将显示一个成功消息. */
		        	if(result.type == 'success'){
		        		if(typeof events.success == 'function'){
		        			events.success(result);
		        		} else {
		        			_info("成功", result.message);
		        		}
		        		return;
		        	}

		        	/* 处理执行发生失败时的事件,若未指定事件,将显示一个警告消息. */
		        	if(result.type == 'failed'){
		        		if(typeof events.failure == 'function'){
		        			events.failure(result);
		        		} else {
		        			_info("失败", result.message);
		        		}
		        		return;
		        	}
		        	
		        	/* 处理未知的请求结果事件. */
	        		if(typeof events.unknown == 'function'){
	        			events.unknown(result);
	        		} else {
	        			_info("未知结果", result.message);
	        		}
	        		
	        		_info("未知的请求结果类型。");
	        		return;
		        },
	            complete: function (xhr, textStatus) {
	            	// 调用完成事件.
	            },
	            error: function (xhr, textStatus, errorThrown) {
	            	// 调用异常事件.
	            }
	   		});
	}
	
	/**
	 * 用于跳转一个指定的URL地址，并可以指定其跳转模式.
	 * 
	 * @param {String} targetUrl 跳转目标地址.
	 * @param {Integer} mode 可选值: 0 当前页跳转(默认); 1 内容页跳转; 2 主页跳转.
	 * @param {String} returnUrl 可选值：可指定的跳转返回地址.
	 * @returns void
	 */
	function _skipUrl(targetUrl, mode, returnUrl) {

		var skipWin = win;

		if (mode == 1) {
			skipWin = _getContent();
		} else if (mode == 2) {
			skipWin = _getTop();
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
	function _openTab(title, url, iconCls) {

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
	
	/**
	 * 用于打开一个页面窗口.
	 * 
	 * @param {Object} <b>opts 参数选项:</b>
	 * </br>title 窗口标题, 
	 * </br>width 宽度, 
	 * </br>height 高度, 
	 * </br>iconCls 窗口图标, 
	 * </br>url 页面地址, 
	 * </br>onClose 窗口关闭事件.
	 * 
	 */
	function _openWin(opts){
		
		var wid = _newId();

		var w = $top("<div />");
		_setCache("wid_" + wid, w);
		
		w.window({
			title : opts.title,
			width : opts.width,
			height : opts.height,
			shadow : false,
			modal : true,
			iconCls : opts.iconCls,
			closed : false,
			minimizable : false,
			onClose : function(e) {
				_removeCache("wid_" + wid);
				if (typeof opts.onClose == 'function') {
					var result = _getCache("win_result_" + wid, null);
					opts.onClose(result);
				}
			},
			onOpen : function(e) {
				w.css("overflow", "hidden");
			}
		});
		
		var url = _addUrlParam(opts.url, '_wid', wid);
		w.append('<iframe scrolling="auto" frameborder="0" src="' + url + '" ' 
				+ 'style="width:100%;height:100%;"></iframe>');

		w.window('open');
	}
	
	/**
	 * 关闭当前打开的窗口.
	 * 
	 * @param {Object} result 返回给调用者的结果对象.
	 */
	function _closeWin(result) {
		var wid = _getQueryParam("_wid");
		if (wid != null) {
			var w = _getCache("wid_" + wid, null);
			_setCache("win_result_" + wid, result);
			if (w != null) {
				w.window('close');
			}
		}
	}
	
	/**
	 * 显示一个确认对话框.
	 * 
	 * @param {String} content 显示的内容.
	 * @param {Event} onclose(r) 确认 true 或取消 false 响应的事件.
	 */
	function _confirm(content, onclose) {
		win.top.$.messager.confirm('确认', content, onclose);  
	}

	
	/**
	 * 显示一个信息框.
	 * 
	 * @param {String} title 信息框的标题.
	 * @param {String} content 显示的内容.
	 */
	function _message(title, content){

		win.top.$.messager.show({
			title: title,
			msg: content,
			timeout: 5000,
			showType: 'slide'
		});
	}
	
	function _getTipQueue(){

		var tipQueue = _getCache("tipQueue");
		if(tipQueue == null){
			tipQueue = new Array();
			_setCache("tipQueue", tipQueue);
		}
		return tipQueue;
	}
	
	function _tips(type, content){

		if (win == win.top) {
			
			var tipQueue = _getTipQueue();
			
			if(tipQueue.length > 0){
				try {
					var e = tipQueue[tipQueue.length-1];
					e.hide();
				} catch(e) {}
			}
	
			var $tip = $top('<div class="juasp-tipsfrom"><span class="juasp-' + type + 'tips">' + content + '</span></div>');
			$tip.appendTo("body");
			
			tipQueue.push($tip);
			
			setTimeout(function(){ 
				var t = tipQueue.shift();
				if(t != null){
					t.fadeOut('slow', function(){ t.remove(); });
				}
			}, 3000);
		} else {

			_getTop().tips(type, content);
		}
	}
	
	win.tips = _tips;
	
	function _infotips(content) {
		
		_tips('info', content);
	}
	
	function _warntips(content) {
		
		_tips('warn', content);
	}
	
	function _errortips(content) {
		
		_tips('error', content);
	}
	
	/**
	 * 显示一个输入框.
	 * 
	 * @param {String} title 信息框的标题.
	 * @param {String} content 显示的内容.
	 * @param {Event} onclose(r) 返回 r 值与 content 类型长度相同.
	 */
	function _prompt(title, content, onclose){
		win.top.$.messager.prompt(title, content, function(r){
			onclose(r);
		});
	}

	/** 绑定方法 **/
	
	juasp.post = _post;
	juasp.skipUrl = _skipUrl;
	juasp.openTab = _openTab;
	juasp.openWin = _openWin;
	juasp.closeWin = _closeWin;
	juasp.confirm = _confirm;
	juasp.info = _infotips;
	juasp.infotips = _infotips;
	juasp.warntips = _warntips;
	juasp.errortips = _errortips;
	juasp.prompt = _prompt;
	juasp.message = _message;
	
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