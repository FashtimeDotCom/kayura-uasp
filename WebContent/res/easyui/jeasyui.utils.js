/**
 * jeasyui 的功能扩展单元.
 * 
 * @author liangxia@live.com
 */

(function(win, $) {

	// 扩展 easyui tree 组件的方法.

	$.extend($.fn.tree.methods, {

		/**
		 * 扩展 tree 方法，用于获取指定节点所属的根节点对象.
		 * 
		 * @param {oject} jq 当前 tree 的 DOM jQuery对象.
		 * @param {object} node 用于查找的原始节点对象.
		 */
		getRootNode : function(jq, node) {
			var root = node;
			while (true) {
				var parent = $(jq).tree("getParent", root.target);
				if (parent == null) {
					break;
				}
				root = parent;
			}
			return root;
		}

	});

	// 重写 jeasyui 默认属性.
	
	$.extend($.fn.tree.defaults, {
		method : "post",
		loadFilter : function(r) {
			return _loadFilter(r);
		}
	});
	
	// 扩展 easyui datagrid.

	$.extend($.fn.datagrid.methods, {

	});

	$.extend($.fn.datagrid.defaults, {
		nowrap : true,
		striped : true,
		method : "post",
		pageSize : 20,
		loadFilter : function(r) {
			return _loadFilter(r);
		},
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!this.ctxmenu){
				this.ctxmenu = _createColumnMenu(this);
			}
			this.ctxmenu.menu('show', { left:e.pageX, top:e.pageY });
		}
	});
	
	// jeasyui 扩展静态方法.

	var jeasyui = {};

	/**
	 * 创建 DataGrid 表格的列控制菜单.
	 * 
	 * @param {String} tag datagrid表格标签,如: #dg .
	 */
	function _createColumnMenu(tag) {
		var cmenu = $('<div/>').appendTo('body');
		cmenu.menu({
			onClick : function(item) {
				if (item.iconCls == 'icon-ok') {
					$(tag).datagrid('hideColumn', item.name);
					cmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					$(tag).datagrid('showColumn', item.name);
					cmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
		var fields = $(tag).datagrid('getColumnFields');
		for (var i = 1; i < fields.length; i++) {
			var field = fields[i];
			var col = $(tag).datagrid('getColumnOption', field);
			cmenu.menu('appendItem', {
				text : col.title,
				name : field,
				iconCls : col.hidden ? 'icon-empty' : 'icon-ok' 
			});
		}
		return cmenu;
	}

	/**
	 * 默认的 jeasyui 数据过滤方法.
	 */
	function _loadFilter(r) {
		
		if(r.type != undefined) {
			if (r.type == juasp.SUCCESS && r.data) {
				return r.data;
			} else {
				juasp.info(r.message);
			}
		} else {
			return r;
		}
	}

	win.jeasyui = jeasyui;

}(window, jQuery));