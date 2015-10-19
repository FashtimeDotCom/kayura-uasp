/**
 * 
 */


(function(win,$){
	
	var jeasyui = {};
	
	/**
	 * 创建 DataGrid 表格的列控制菜单.
	 */
	jeasyui.createColumnMenu = function(tag){
		var cmenu = $('<div/>').appendTo('body');
		cmenu.menu({
			onClick: function(item){
				if (item.iconCls == 'icon-ok'){
					$(tag).datagrid('hideColumn', item.name);
					cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				} else {
					$(tag).datagrid('showColumn', item.name);
					cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
					});
				}
			}
		});
		var fields = $(tag).datagrid('getColumnFields');
		for(var i=1; i<fields.length; i++){
			var field = fields[i];
			var col = $(tag).datagrid('getColumnOption', field);
			cmenu.menu('appendItem', {
				text: col.title,
				name: field,
				iconCls: 'icon-ok'
			});
		}
		return cmenu;
	}
	
	win.jeasyui = jeasyui;
	
}(window,jQuery));