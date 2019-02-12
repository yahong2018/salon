Ext.define('app.ux.dbgrid.DbGridDeleteButton', {
    extend: 'app.ux.dbgrid.DbGridButton'
    ,xtype: 'dbgriddeletebutton'
   
    ,constructor:function(config){
        var defaultHandler = this.doClick;
        var configBase = {
             text:'删除'
             ,btnName:'btnDelete'
             ,glyph: 0xf014
             ,tooltip:'删除选定的数据'
             ,handler:defaultHandler
        };
        Ext.applyIf(config,configBase);

        this.callParent(arguments);
    }
    , doClick: function () {        
        var grid = this.getGrid();
        var records = grid.getSelectionModel().getSelection();
        if (!records || records.length == 0) {
			Ext.MessageBox.alert("系统提示", "请先选择待删除记录！");
			
            return;
        }

        Ext.MessageBox.show({
            title: '系统提示',
            msg: '确实要删除这<font color="red">【' + records.length + '】</font>条记录么？',
            buttons: Ext.MessageBox.YESNO,
            icon: Ext.MessageBox.QUESTION,
            fn: function (buttonId) {
                if (buttonId == "yes") {
                    var deleteRecords = new Array();
                    Ext.each(records, function (item, index, itemsItSelf) {
                        deleteRecords.push(item.getId());
                    });

                    if (grid.beforeDelete) {
                        if (!grid.beforeDelete(records)) {
                            return;
                        }
                    }
                    app.ux.Utils.ajaxRequest({
                        url: grid.getStore().getDeleteUrl(),
                        method: 'POST',
                        jsonData: deleteRecords,
                        successCallback:function(result, response, opts){           
                            if (grid.store) {
                                Ext.each(records, function (item, index, itemsItSelf) {
                                    grid.store.remove(item);
                                });
                                grid.store.commitChanges();
                            }
                            if (grid.afterDelete) {
                                grid.afterDelete(response, opts);
                            }
  
                            Ext.toast({
                                html: '数据已删除',
                                title: '系统提示',
                                width: 200,
                                align: 't'
                            });
                        },
                        failureCallback:function(response, opts){
                            if (grid.afterFailure) {
                                grid.afterFailure(response, opts);
                            }
                        }
                    });  
                }
            }
        });
    }
});