Ext.define('app.view.main.menu.systemProgram.SystemProgramController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.app_view_main_menu_systemProgram_SystemProgramController',
    uses:[],
    treeSelectionChanged: function(model, selected, index) {
    	if(null!=selected &&  undefined !=selected[0]){
    		programId =  selected[0].data.programId;
    	}else{
    		programId = "";
    	}
    	var grid =this.getView().up('app_view_main_menu_systemProgram_SystemProgramHomepage').down('app_view_main_menu_programPrivilege_ProgramPrivilege');
		var expr = "?programId=" + programId;
		grid.getStore().getProxy().url = webRoot + "/admin/programPrivilege/getProgramPrivilegeByProgramId.handler" + expr;
		grid.getStore().load();
    }
});