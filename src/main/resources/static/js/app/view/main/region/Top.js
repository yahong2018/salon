Ext.define('app.view.main.region.Top', {

	extend: 'Ext.toolbar.Toolbar',

	alias: 'widget.maintop',

	uses: ['app.ux.ButtonTransparent', 'app.view.main.menu.ButtonMainMenu',
		'app.view.main.menu.SettingMenu', 'Ext.window.MessageBox'
	],

	defaults: {
		xtype: 'buttontransparent'
	},

	style: 'background-color : #cde6c7',

	height: 40,

	items: [{
		xtype: 'image',
		bind: {
			hidden: '{!system.iconUrl}',
			src: '{system.iconUrl}'
		}
	}, {
		xtype: 'label',
		bind: {
			text: '{system.name}'
		},
		style: 'font-size:20px;color:blue;'
	}, {
		xtype: 'label',
		style: 'color:grey;',
		bind: {
			text: '({system.version})'
		}
	}, '->', {
		text: '修改密码',
		handler: 'changePassword'
	},
	{
		glyph: 0xf06a,
		text: '刷新',
		handler: 'loadLinesRealtimeData'
	}, {
		text: '注销',
		glyph: 0xf011,
		handler: 'logout'
	}, {
		glyph: 0xf102,
		handler: 'hiddenTopBottom',
		tooltip: '隐藏顶部和底部区域',
		disableMouseOver: true
	}
	]

});