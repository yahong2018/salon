Ext.define('app.view.main.region.Center', {
	extend: 'Ext.tab.Panel',
	alias: 'widget.maincenter',	
		
	initComponent: function () {
		this.items = [{
		//	id:'homePage',
			glyph: 0xf015,
			title: '管理控制台',
			border: true,
			frame: false,
			bodyCls: 'panel-background',
			reorderable: false,
			items: []
		}];
		this.callParent();
	},
	listeners:{
	}
});

