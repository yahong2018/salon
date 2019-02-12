Ext.define('app.view.main.Main', {
	extend: 'Ext.container.Container',
	xtype: 'app-main',
	requires: [
		'app.view.main.MainController', 'app.view.main.MainModel'
	],

	uses: ['app.view.main.region.Center', 'app.view.main.region.Top',
		'app.view.main.region.Bottom', 'app.view.main.menu.MainMenuTree'
	],

	controller: {
	//	id: 'main-controller',
		type: 'main'
	},
	viewModel: {
		type: 'main'
	}, 

	initComponent: function () {
		Ext.setGlyphFontFamily('FontAwesome');
	
		this.callParent();
	},

	layout: {
		type: 'border'
	},

	listeners: {
		resize: function (container) {
			container.getController().onMainResize();
		},
		afterrender: function () {
			var mainView = this;
			mainView.getViewModel().set('system.name', systemTitle);

			Ext.TaskManager.start({
				interval: 1000,
				run: function () {
					var the_div = Ext.query('div[system-time-div]')[0];
					var msg = '北京时间 ' + Ext.util.Format.date(new Date(), "Y-m-d H:i:s");
					Ext.getDom(the_div).innerText = msg;
				}
			});

			var runner = mainView.getViewModel().get('system.taskRunner');
			var interval = mainView.getViewModel().get('system.autoRefreshInterval') * 1000 * 60;
			var task = runner.newTask({
				interval: interval,
				run: function () {
					console.log('start refresh:' + Ext.util.Format.date(new Date(), "Y-m-d H:i:s"));
				}
			});
			mainView.getViewModel().set('system.autoRefreshTask', task);
			task.start();
		}
	},

	items: [{
		xtype: 'maintop',
		region: 'north'
	}, {
		xtype: 'mainbottom',
		region: 'south'
	}, {
		xtype: 'mainmenutree',
		region: 'west',
		title: '导航菜单',
		width: 220,
		collapsible: true,
		split: true
	}, {
		region: 'center',
		xtype: 'maincenter',
	}]
});