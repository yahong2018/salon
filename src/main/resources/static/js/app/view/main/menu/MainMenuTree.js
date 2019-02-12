Ext.define('app.view.main.menu.MainMenuTree', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.mainmenutree',
	requires: ['Ext.data.TreeStore','app.model.admin.SystemMenuTreeModel'],
	frame: true,
	rootVisible: false,
	store: {
		type:'tree',
		model: 'app.model.admin.SystemMenuTreeModel',
		proxy: {
			type: 'ajax',
			url: webRoot + '/mainPage/getUserMenu.handler',
			reader: {
				type: 'json'
			}
		}
	},
	listeners: {
		itemclick: 'onMainMenuClick'
	}
});