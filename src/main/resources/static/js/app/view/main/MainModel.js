/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('app.view.main.MainModel', {
	extend: 'Ext.app.ViewModel',
	alias: 'viewmodel.main',

	requires: ['Ext.util.TaskRunner'],

	data: {
		name: 'app',

		// 系统信息
		system: {
			name: '智汇星红管理信息开发平台',
			version: '01.2015.01.06',
			iconUrl: '',
			warnMessage:'暂无警告消息',
			currentTime:'',
			taskRunner: new Ext.util.TaskRunner(),
			autoRefreshTask:{},	
			autoRefreshInterval:2,			 
		},

		// 用户单位信息和用户信息
		user: {			
			company: '合一网络科技有限公司',
			department: '研发部',
			name: '朱穗波'
		},

		// 服务单位和服务人员信息
		service: {
			company: '深圳市智汇星红科技信息有限公司',
			name: '刘永红',
			phonenumber: '180-2620-8100',
			qq: '491337961',
			email: 'SoftwareChina@qq.com',
			copyright: '智汇星红'
		},

		menuType: {
			value: 'toolbar'
		}, // 菜单的位置，'button' , 'toolbar' , 'tree'

		// 系统菜单的定义，这个菜单可以是从后台通过ajax传过来的
		systemMenu: [],		
	},

	formulas: {
		PipeWarningMessage: {
			single: '暂无警报消息',
			get: function(get) {
				return get('Pipe.Warning.Message');
			}
		},
		AutoRefreshInterval:{
			get:function(get){
				return get('system.autoRefreshInterval');
			},
			set:function(value){
				debugger;
				if(value<2){
					value=2;
				}
				var task = this.get('system.autoRefreshTask');				
				this.data.system.autoRefreshInterval = value;
				value = value * 1000 * 60;
				task.restart(value);				
			}
		}
	},

	// 根据data.systemMenu生成菜单条和菜单按钮下面使用的菜单数据
	getMenus: function() {
		var items = [];
		var menuData = this.get('systemMenu'); // 取得定义好的菜单数据
		Ext.Array.each(menuData, function(group) { // 遍历菜单项的数组
			var submenu = [];
			// 对每一个菜单项，遍历菜单条的数组
			Ext.Array.each(group.items, function(menuitem) {
				submenu.push({
					mainmenu: 'true',
					moduleName: menuitem.module,
					text: menuitem.text,
					icon: menuitem.icon,
					glyph: menuitem.glyph,
					handler: 'onMainMenuClick' // MainController中的事件处理程序
				})
			})
			var item = {
				text: group.text,
				menu: submenu,
				icon: group.icon,
				glyph: group.glyph
			};
			items.push(item);
		})
		return items;
	},	
	stores: {
	}
});