Ext.define('app.view.main.MainController', {
	extend: 'Ext.app.ViewController',

	uses: ['app.view.main.region.ChangePasswordWindow', 'Ext.window.MessageBox', 'Ext.window.Toast', 'app.ux.Utils', 'app.ux.GlobalVars'],

	alias: 'controller.main',

	changePassword: function () {
		Ext.create('app.view.main.region.ChangePasswordWindow').show();
	},
	logout: function () {
		document.location.href = webRoot + 'login/logout';
	},

	onMainMenuClick: function (tree, menuData, item, index, e, eOpts) {
		var className = menuData.get('url');
		if (!className) {
			return;
		}
		var desktopContainer = this.getView().down('maincenter');
		var xtype = className.replace(/\./g, "_");
		var theTab = desktopContainer.down(xtype);
		if (!theTab) {
			theTab = desktopContainer.add(
				Ext.create(className, {
					title: menuData.get('programName'),
					glyph: menuData.get('glyph'),
					autoScroll: true,
					closable: true,
					menuData: menuData
				}));

			var programId = menuData.get("programId");
			app.ux.Utils.applyPrivileges({ programId: programId, model: 0,component:theTab});
			
			if (theTab.applyPrivileges) {
				theTab.applyPrivileges({ programId: programId, model: 0 });
			}
		}
		desktopContainer.setActiveTab(theTab);		
	},

	hiddenTopBottom: function () {
	    this.getView().down('maintop').hide();
		this.getView().down('mainbottom').hide();
		if (!this.showButton) {
			this.showButton = Ext.widget('button', {
				//glyph: 0xf013,
				view: this.getView(),
				floating: true,
				x: document.body.clientWidth - 32,
				y: 0,
				height: 10,
				width: 26,
				tooltip: '显示顶部和底部区域',
				disableMouseOver: true,
				style: 'background-color:#cde6c7;cursor:pointer;',
				listeners: {
					el: {
						click: function (el) {
							var c = Ext.getCmp(el.target.id);
							c.view.down('maintop').show();
							c.view.down('mainbottom').show();
							c.hide();
						}
					}
				}
			})
		};
		this.showButton.show();
	},

	onMainResize: function () {
		if (this.showButton && !this.showButton.hidden) {
			this.showButton.setX(document.body.clientWidth - 32);
		}
	},

	loadCurrentUser: function () {
		var me = this;
		app.ux.Utils.ajaxRequest({
			url: webRoot + '/mainPage/getCurrentLogin.handler',
			async: false,
			successCallback: function (result) {
				app.ux.GlobalVars.currentLogin = result;
				app.ux.GlobalVars.webRoot = webRoot;
				app.ux.GlobalVars.jsRoot = jsRoot;
				app.ux.GlobalVars.systemTitle = systemTitle;

				me.getView().getViewModel().set('user.name', result.userName + '(' + result.userCode + ')');
			}
		});
	}
});