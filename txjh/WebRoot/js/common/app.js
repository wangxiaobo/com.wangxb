var AppUtil = {};

AppUtil.showTab = function(node){
	var tabs = window.top.mini.get("mainTabs");

	var id = "tab$" + node.id;
	var tab = tabs.getTab(id);
	if (!tab) {
		tab = {};
		tab._nodeid = node.id;
		tab.name = id;
		tab.title = node.text;
		tab.showCloseButton = true;
		// 这里拼接了url，实际项目，应该从后台直接获得完整的url地址
		tab.url = node.url;
		
/*
 * tabs.on("tabload", function(e) { $(".mini-tabs-body").css("height","100%");
 * });
 */
		tabs.addTab(tab);
	}
	tabs.activeTab(tab);
}

// 用户信息浏览
AppUtil.editUser =  function(user,title) {
		mini.open({
			url : "/txjh/pages/editUser.html?userId="+user.userId,
			title : title,
			width : 800,
			height : 600,
			showMaxButton: true,
			onload : function() {
				var iframe = this.getIFrameEl();
				iframe.contentWindow.SetData(user);
			},
			ondestroy : function(action) {
				
			}
		});
}
$("img.imgeShow").live("click", function() {
	var url = $(this).attr("src");
	url = url.split("-");
	window.open(url[0]);    
});

