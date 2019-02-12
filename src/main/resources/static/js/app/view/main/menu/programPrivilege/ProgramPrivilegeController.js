Ext.define('app.view.main.menu.programPrivilege.ProgramPrivilegeController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.app_view_main_menu_programPrivilege_ProgramPrivilegeController',
    uses:[],
    insert:function(programPrivilege){
      	 var deailWindow = Ext.create('app.view.main.menu.programPrivilege.ProgramPrivilegeWindow', {
        	  isNew: true,//新增标识
              title: '新增菜单按钮',
              listGrid:this.getView(),
              store: this.getView().store,
              items: [{
                  xtype: 'app_view_main_menu_programPrivilege_ProgramPrivilegeDetailForm'
              }]
          });
          var form = deailWindow.down('form');
          var record = deailWindow.store.createModel({});
          if (form.beforeLoadRecord) {
              form.beforeLoadRecord({isNew:true, record:record});
          }
          form.loadRecord(record);
          deailWindow.show();
     },
     //修改
     update :function(programPrivilege){
    	 var selected =this.getView().getSelectionModel().getSelection();
         if (!selected || selected.length == 0) {
             Ext.MessageBox.alert("系统提示", "请先选择一条待编辑记录！");
             return;
         }

         if(selected.length == 1){
             var deailWindow = Ext.create('app.view.main.menu.programPrivilege.ProgramPrivilegeWindow', {
                 isNew: false,//新增标识
                 title: '修改菜单按钮',
                 listGrid:this.getView(),
                 store: this.getView().store,
                 items: [{
                     xtype: 'app_view_main_menu_programPrivilege_ProgramPrivilegeDetailForm'
                 }]
             });

             var record = selected[0];
             var form = deailWindow.down('app_view_main_menu_programPrivilege_ProgramPrivilegeDetailForm');
             if (form.beforeLoadRecord) {
                 form.beforeLoadRecord({isNew:false, record:record});
             }
             form.loadRecord(selected[0]);
             deailWindow.show();
         }else{
             Ext.Msg.alert("系统提示","一次不能同时修改多行");
         }
       },
       delete :function(programPrivilege){
           var records = this.getView().getSelectionModel().getSelection();
           if (!records || records.length == 0) {
               Ext.MessageBox.alert("系统提示", "请先选择待删除记录！");
               return;
           }else{
               var deleteFlag = true;//默认可以删除

               if(!deleteFlag) {
                   return;
               }
           }
           var grid =this.getView();
           Ext.MessageBox.show({
               title: '系统提示',
               msg: '确实要删除这<font color="red">【' + records.length + '】</font>条记录的所有菜单按钮么？',
               buttons: Ext.MessageBox.YESNO,
               icon: Ext.MessageBox.QUESTION,
               fn: function (buttonId) {
                   if (buttonId == "yes") {
                       var deleteRecords = new Array();
                       Ext.each(records, function (item, index, itemsItSelf) {
                           deleteRecords.push(item.data.programPrivilegeId);
                       });
                       app.ux.Utils.ajaxRequest({
                           url: webRoot+grid.store.dao.deleteUrl + "?id="+deleteRecords,
                           method: 'POST',
                           jsonData: deleteRecords,
                           successCallback:function(result){
                               if (grid.store) {
                                   Ext.each(records, function (item, index, itemsItSelf) {
                                 	  grid.store.remove(item);
                                   });
                                   grid.store.commitChanges();
                               }
                           },
                           failureCallback:function(response, opts){
                               Ext.MessageBox.alert("系统提示", "删除失败！");
                           }
                       });
                   }
               }
           });
       }
});