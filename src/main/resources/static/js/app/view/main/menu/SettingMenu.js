/**
 * 显示在顶部的按钮菜单，可以切换至标准菜单，菜单树
 */
Ext.define('app.view.main.menu.SettingMenu', {
	extend: 'app.ux.ButtonTransparent',
	alias: 'widget.settingmenu',

	text: '设置',
	glyph: 0xf013,
	tooltip: '偏好设置',

	initComponent: function () {
		this.menu = [];
		this.menu.push({
			text: '菜单样式',
			menu: [{
				xtype: 'segmentedbutton',
				reference: 'menuType',
				//defaultUI : 'default',
				value: 'toolbar',
				items: [{
					text: '标准菜单',
					value: 'toolbar'
				}, {
					text: '树形菜单',
					value: 'tree'
				}, {
					text: '按钮菜单',
					value: 'button'
				}]
			}]
		})
		this.callParent();
	}

})