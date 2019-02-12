Ext.define('app.view.main.menu.programPrivilege.ProgramPrivilege', {
    extend: 'app.ux.dbgrid.DbGrid',
    xtype: 'app_view_main_menu_programPrivilege_ProgramPrivilege',
    requires: ['app.view.main.menu.programPrivilege.ProgramPrivilegeController', 'app.model.admin.ProgramPrivilegesModel'],
    uses: ['app.view.main.menu.programPrivilege.ProgramPrivilegeDetailForm', 'app.model.admin.ProgramPrivilegesModel', 'app.store.admin.ProgramPrivilegeStore'],
    controller: {
        type: 'app_view_main_menu_programPrivilege_ProgramPrivilegeController'
    },
    columns: [
        { dataIndex: 'programPrivilegeId', text: '编号', width:70},
        { dataIndex: 'programId', text: '菜单编号',flex:1},
        { dataIndex: 'privilegeCode', text: '权限代码', flex:1},
        { dataIndex: 'privilegeName', text: '权限名称', flex: 1},
    ],
    dockedItems: [
        {
            xtype: "dbgridtoolbar",
            hideSearch: true,
            hideInsert: true,
            hideEdit: true,
            hideDelete: true,
        }
    ],
    tbar:[
        {text:'新增',glyph: 0xf067,handler:'insert'},
        {text:'修改',glyph: 0xf067,handler:'update'},
        {text:'删除',glyph: 0xf014,handler:'delete'},
    ],
    additionToolbarItems: [
    ],
    hideDefeaultPagebar: false,
    constructor:function(config){
        var configBase = {
           store: Ext.create({xtype:'app_store_admin_ProgramPrivilegeStore'}),
           detailFormClass: 'app_view_main_menu_programPrivilege_ProgramPrivilegeDetailForm',
           detailWindowTitle: '菜单按钮管理',
        }
        Ext.applyIf(config,configBase);
        this.callParent(arguments);
    },
    listeners: {
        afterrender: function () {
            //this.store.load();
        },
    },
    beforeLoadRecord:function(config){
     	 var grid = this.ownerCt.down("app_view_main_menu_programPrivilege_ProgramPrivilege");
     	 if(programId != ""){
     		grid.getStore().getProxy().url =webRoot+"/admin/programPrivilege/getProgramPrivilegeByProgramId.handler?programId="+programId;
            grid.getStore().load();
     	 }
    },    
});