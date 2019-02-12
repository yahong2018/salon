Ext.define('app.view.main.menu.systemProgram.SystemProgramHomepage', {
    extend: 'Ext.panel.Panel',
    xtype: 'app_view_main_menu_systemProgram_SystemProgramHomepage',
    requires: ['app.store.admin.ProgramPrivilegeStore','app.model.admin.ProgramPrivilegesModel','app.store.admin.SystemProgramStore', 'app.model.admin.SystemProgramModel',
        'app.view.main.menu.systemProgram.SystemProgramController','app.view.main.menu.systemProgram.SystemProgramDetailForm',
        'app.view.main.menu.programPrivilege.ProgramPrivilegeController','app.view.main.menu.programPrivilege.ProgramPrivilegeDetailForm',
        'app.view.main.menu.programPrivilege.ProgramPrivilege','app.view.main.menu.systemProgram.SystemProgram',],
    controller: {
        type: 'app_view_main_menu_systemProgram_SystemProgramController'
    },

    frame: false,
    layout: 'border',
    items: [
        {
            region: 'west',
            xtype: 'app_view_main_menu_systemProgram_SystemProgram',
            width: 1000,
            // title: '菜单列表',
        },
        {
            region: 'center',
            xtype: 'app_view_main_menu_programPrivilege_ProgramPrivilege',
            // title: '菜单按钮列表',
        }
    ]
}
);