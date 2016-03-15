/**
 * 使用 WebUploader 的封装文件/图片上传组件.
 * 
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */

(function($, win) {

	var $queue = null;
	var MB = 1024 * 1024, GB = MB * 1024;
    
	// 计算 Web 项目路径.
	var hostPath = win.location.href.substring(0, win.location.href.indexOf(win.location.pathname));
	var projectName = win.location.pathname.substring(0, win.location.pathname.substr(1).indexOf('/') + 1);
	var appPath =  hostPath + projectName;
	
	function newid() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	}
	
	// ...
	function init(target){
		
        if (!WebUploader.Uploader.support()) {
            var error = "上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器。";
            $(target).text(error);
            return;
        }

		var opts = $.data(target, 'uploader').options;
		var targetId = target.id;
		var target = $(target);

		if ($queue == null) {
			$queue = $('<div class="webuploader-filequeues-list"></div>');
			$queue.appendTo("body");
		}
		
		var uploader = WebUploader.create($.extend({ 
			pick : '#' + targetId,
			formData : $.extend({}, opts.innerOptions.formData, opts.formData)
		}, opts.innerOptions));
		
        uploader.on('uploadFinished', function () {

        	if(opts.onFinished()) {
        		opts.onFinished();
        	}
        });
        
        uploader.on('filesQueued', function (files) {

        	for(var i in files){
        		$queue.append("<li id='li_" + files[i].id + "' class='webuploader-filequeues-item'>" + files[i].name + "</li>"); 
        	}
        });
        
        uploader.on('uploadSuccess', function (file, response) {
        	
        	$queue.find("#li_" + file.id).text(file.name + " 上传完成.");
            setTimeout(function(){
            	$queue.find("#li_" + file.id).remove();
            },3000);
        });
        
        uploader.on('uploadProgress', function (file, percentage) {
        	
        	$queue.find("#li_" + file.id).text(file.name + " 上传中 " + Math.round(percentage * 100) + " %");
        });
        
        uploader.on('error', function(type){
        	
            /**
             * type {String} 错误类型。
             * Q_EXCEED_NUM_LIMIT 在设置了fileNumLimit且尝试给uploader添加的文件数量超出这个值时派送。
             * Q_EXCEED_SIZE_LIMIT 在设置了Q_EXCEED_SIZE_LIMIT且尝试给uploader添加的文件总大小超出这个值时派送。
             * Q_TYPE_DENIED 当文件类型不满足时触发。
             */
        	
        	var msg = "";
        	switch (type) {
			case Q_EXCEED_NUM_LIMIT:
				msg = "上传的文件数量已经超出.";
				break;
			case Q_EXCEED_SIZE_LIMIT:
				msg = "上传的文件大小已经超出.";
				break;
			case Q_TYPE_DENIED:
				msg = "上传的文件类型不支持.";
				break;
			default:
				msg = "未知错误.";
				break;
			}
        	
			juasp.info(msg);
        });
        
        uploader.on('uploadAccept', function(o, r){
        	
        });
	}

	var webuploader = function(options, param){
		if (typeof options == 'string'){
			return webuploader.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'uploader');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'uploader', {
					options: $.extend({}, webuploader.defaults, options)
				});
				init(this);
			}
		});
	};
	
	// 上传组件支持方法.
	webuploader.methods = {
		setFormData: function(target, param){
			var state = $.data(this, 'uploader');
			if (state){
				var opts = state.options;
				opts.formData = $.extend({}, opts.formData, param);
			}
		}
	};
	
	// 上传组件默认属性.
	webuploader.defaults = {
		onFinished : function () { },
		onSuccess : function (file, res) { } ,
        formData: {				// 附件上传可接收的参数范本示例.
        	bizId : '',			// ● 绑定业务表单Id.
        	category : '',		// ● 该附件在表单中的分类.
        	folderId : '',		// ● 可指定添加到的文件夹.在指定了bizId时,该值将被忽略.
        	serial : 0,			// ● 排序码.
        	allowChange : 0,	// ● 是否允许编辑.
        	isEncrypt : 0,		// ● 是否加密文件.
        	tags : ''			// ● 自定义标签,使用逗号间隔.
        },
		fileQueueId : '',		// 文件上传阵列的区域标签Id.
		innerOptions : {
			swf: appPath + '/res/webuploader/Uploader.swf',
			server: appPath + '/file/upload.json',
			auto: true, 					// 设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
	        fileNumLimit: 99,				// 验证文件总数量, 超出则不允许加入队列。
	        fileSizeLimit: 100 * MB,		// 验证文件总大小是否超出限制, 超出则不允许加入队列。
	        fileSingleSizeLimit: 50 * MB,	// 验证单个文件大小是否超出限制, 超出则不允许加入队列。
			fileVal : "file",				// 设置文件上传域的name。
			method: "POST",					// 文件上传方式，POST或者GET。
			duplicate: true
		}
	};
	
	$.fn.uploader = webuploader;
	
}(jQuery, window));