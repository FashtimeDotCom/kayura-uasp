/**
 * 使用 WebUploader 的封装文件/图片上传组件.
 * 
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */


(function($, win) {
	
	var KB = 1024 * 1024;
    
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
		var $list = $("#" + opts.listid);

		var uploader = WebUploader.create($.extend({ 
			pick : '#' + targetId,
			formData : opts.formData
		}, opts.innerOptions));

        uploader.on('fileQueued', function (file) {
        	
            $list.append('<div id="' + file.id + '" class="item">' +
                '<div class="info">' + file.name + '</div>' +
                '<div class="state">等待上传...</div>' +
                '<div class="del"></div>' +
            	'</div>');
            
        });
        
        uploader.on('uploadProgress', function (file, percentage) {
        	
        	$("上传中...").appendTo($list);
        	
/*            var $li = target.find('#' + file.id);
            var $percent = $li.find('.progress .bar');
 
            // 避免重复创建
            if (!$percent.length) {
                $percent = $('<span class="progress">' +
                    '<span  class="percentage"><span class="text"></span>' +
                    '<span class="bar" role="progressbar" style="width: 0%">' +
                    '</span></span>' +
                	'</span>').appendTo($li).find('.bar');
            }
 
            $li.find('div.state').text('上传中');
            $li.find(".text").text(Math.round(percentage * 100) + '%');
            $percent.css('width', percentage * 100 + '%');*/
        });
        
        uploader.on('uploadSuccess', function (file, response) {

        	$("fileId:" + file.id + " 上传完成.").appendTo($list);
        	
/*            target.find('#' + file.id).find('div.state').text('已上传');
            var fileEvent = {
                queueId: file.id,
                name: file.name,
                size: file.size,
                type: file.type,
                filePath: response.filePath
            };
            opts.onComplete(fileEvent);*/
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
					options: $.extend({}, webuploader.defaults, options),
					data: {}
				});
				init(this);
			}
		});		
	};
	
	// 上传组件支持方法.
	webuploader.methods = {
		
	};
	
	// 上传组件默认属性.
	webuploader.defaults = {
		onAllComplete : function () { },
		onComplete : function () { } ,
		listId : "",
        formData: { },						// 文件上传请求的参数表，每次发送都会发送此对象中的参数。
		innerOptions : {
			swf: appPath + '/res/webuploader/Uploader.swf',
			server: appPath + '/file/upload.json',
			auto: true, 					// 设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
	        fileNumLimit: 5,				// 验证文件总数量, 超出则不允许加入队列。
	        fileSizeLimit: 5 * KB,			// 验证文件总大小是否超出限制, 超出则不允许加入队列。
	        fileSingleSizeLimit: 5 * KB,	// 验证单个文件大小是否超出限制, 超出则不允许加入队列。
			fileVal : "file",				// 设置文件上传域的name。
			method: "POST",					// 文件上传方式，POST或者GET。
			duplicate: true
		}
	};
	
	
	$.fn.uploader = webuploader;
	
}(jQuery, window));