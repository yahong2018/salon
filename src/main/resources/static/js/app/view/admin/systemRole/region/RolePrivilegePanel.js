Ext.define('app.view.admin.systemRole.region.RolePrivilegePanel', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.RolePrivilegePanel',
	requires: ['Ext.data.TreeStore', 'app.model.admin.SystemMenuTreeModel', 'app.model.admin.ProgramPrivilegeModel'],
	frame: true,
	rootVisible: false,
	store: {
		type: 'tree',
		model: 'app.model.admin.SystemMenuTreeModel',
		proxy: {
			type: 'ajax',
			url: webRoot + '/admin/systemRoles/getAllMenuWithPrivilege.handler',
			reader: {
				type: 'json',
				typeProperty: 'dataType'
			}
		},
		listeners: {
			load: function () {
				var grid = this.ownerTree.up('app_view_admin_systemRole_SystemRole').down('SystemRoleGrid');
				if (grid.store.getCount() > 0 && !grid.dataProcessed) {
					grid.dataProcessed = true;
					grid.getSelectionModel().select(0);
				}
			}
		}
	},
	tbar: {
		items: [
			{
				xtype: 'button',
				text: '全选',
				menu: [{
					text: '选择全部',
					handler: 'selectAllWhole'
				}, {
					text: '全选下级',
					handler: 'selectAllChilren',
				}]
			},
			{
				text: '反选',
				menu: [{
					text: '反选全部',
					handler: 'revertSelectAllWhole'
				}, {
					text: '反选下级',
					handler: 'revertSelectAllChilren',
				}]
			},
			{ text: '更新权限', handler: 'updatePrivilege' },
		]
	},
	listeners: {
		checkchange: 'checkchange'
		//itemclick: 'onMainMenuClick'
	}

});