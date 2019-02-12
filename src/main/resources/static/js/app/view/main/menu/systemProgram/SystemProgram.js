var programId = '';
Ext.define("app.view.main.menu.systemProgram.SystemProgram", {
    extend: "app.ux.dbgrid.TreeGrid",
    xtype: "app_view_main_menu_systemProgram_SystemProgram",
    requires:['app.view.main.menu.systemProgram.SystemProgramController','app.view.main.menu.programPrivilege.ProgramPrivilege','app.store.admin.SystemProgramStore', 'app.model.admin.SystemProgramModel'],
    uses:["app.view.main.menu.systemProgram.SystemProgramDetailForm"],
    controller: {
        type: 'app_view_main_menu_systemProgram_SystemProgramController'
    },
    rootVisible: false,        
    columns: [
        {
            xtype: 'treecolumn',
            text: '菜单名称',
            dataIndex: 'programName',
            width:300,
        },
        {
            text: '菜单编号',
            dataIndex: 'programId',
            width:100,
        },
        {
            text: '菜单路径',
            dataIndex: 'url',
            flex:1,
        },
        {
            text: '参数',
            dataIndex: 'parameters', 
            hidden:true,
            flex:1,
        },
        {
            text: '排序',
            dataIndex: 'showOrder',
            width:50,
        },
        {
            text: '状态',
            dataIndex: 'status',
            hidden:true,
            flex:1,
        },
        {
            text: '图标',
            dataIndex: 'glyph',
            hidden:true,
            flex:1,
        }
    ],
    additionToolbarItems: [
    ],
    constructor:function(config){
        var configBase = {
           store: Ext.create({xtype:'app_store_admin_SystemProgramStore'}),
           detailFormClass: 'app_view_main_menu_systemProgram_SystemProgramDetailForm',
           detailWindowTitle: '菜单管理',           
        }
        Ext.applyIf(config,configBase);

        this.callParent(arguments);
    },
    listeners: {
        selectionchange: 'treeSelectionChanged'
	},
	afterDelete:function(){
		   var store = this.store;
		   store.load();
		}
});