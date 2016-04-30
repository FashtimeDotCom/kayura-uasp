
jctx = (function(win, $) {
	
	var rootPath = "";
	var isfirst = true;
	var searchvalue = "";
	var actions = {
			addcompany : false,
			adddepart : false,
			addposition : false,
			remove : false
		};
	
	function _init(path){
		
		rootPath = path;
		
		$('#tv').tree({
			url : rootPath + "/org/tree.json",
			onClick : function(node) {
				_clickNode(node);
			},
			onLoadSuccess : function(node, data){
				$(this).tree("collapseAll");
				var root = $(this).tree('find', "ROOT");
				$(this).tree("expand", root.target);
			},
			onContextMenu: function(e, node){
				e.preventDefault();
				$(this).tree('select', node.target);
				_clickNode(node);
				$('#mm').menu('show', { left: e.pageX, top: e.pageY });
			}
		});
		
		$("#search").textbox({
			iconCls:'icon-search',
			onChange : function(n, o){
				_search(n);
			}
		});
		
		$("#search").textbox('addClearBtn');
	}
	
	function _initActions(){
		
		actions.addcompany = false;
		actions.adddepart = false;
		actions.addposition = false;
		actions.remove = false;
	}
	
	function _applyActions(_actions){

		$('#mm').menu((_actions.addcompany?'showItem':'hideItem'), $("#mmaddcompany"));
		$('#mm').menu((_actions.adddepart?'showItem':'hideItem'), $("#mmadddepart"));
		$('#mm').menu((_actions.addposition?'showItem':'hideItem'), $("#mmaddposition"));
		$('#mm').menu((_actions.remove?'showItem':'hideItem'), $("#mmremove"));

		$("#tbaddcompany").linkbutton(_actions.addcompany?'enable':'disable');
		$("#tbadddepart").linkbutton(_actions.adddepart?'enable':'disable');
		$("#tbaddposition").linkbutton(_actions.addposition?'enable':'disable');
	}

	function _clickNode(node) {

		selectNode = node;
		_initActions();

		// 0 根, 1 公司, 2 部门, 3 岗位;
		var type = node.attributes['type'];
		if (type == 0) {
			actions.addcompany = true;
		} else if (type == 1) {
			actions.addcompany = true;
			actions.adddepart = true;
		} else if (type == 2) {
			actions.adddepart = true;
			actions.addposition = true;
		}

		if (type != 0 && node.children.length == 0) {
			actions.remove = true;
		}

		_applyActions(actions);
		_findItems(node.id);
	}

	function _search(value) {

		searchvalue = value;
		_findItems(selectNode.id);
	}

	function _findItems(nodeId) {

		var id = nodeId;
		if (nodeId == "ROOT") {
			id = "";
		}

		if (isfirst) {

			$('#tg').datagrid({
				url : rootPath + "/org/find.json",
				queryParams : {
					"id" : id
				}
			});
		} else {

			$('#tg').datagrid('load', {
				"id" : id,
				"keyword" : searchvalue
			});
			$('#tg').datagrid('unselectAll');
		}

		isfirst = false;
	}

	function _addcompany(){
		
		var openUrl = rootPath + "/org/company/new?pid=" + selectNode.id + "&pname=" + selectNode.text;
		juasp.openWin({
			url : openUrl,
			width : "450px",
			height : "500px",
			title : "创建公司",
			onClose : function(r) {
				if (r.result == 1) {
					
				}
			}
		});
	}
	
	return {
		init : _init,
		search : _search,
		addcompany : _addcompany
	}

}(window, jQuery));