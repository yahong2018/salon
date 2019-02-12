Ext.define('app.view.main.menu.programPrivilege.ProgramPrivilegeWindow', {
	extend: 'Ext.window.Window',
    alias: 'widget.detailwindow',
    uses: ['app.ux.Utils'],


    modal: true,
    maximizable: true,
    minimizable: true,
    buttons: [
        '->'
        , {
            text: '保存',
            buttonName:'save',
            handler: function () {
                var me = this.up('detailwindow');
                me.doSave();
            }
        }, {
            text: '保存并新增',
            buttonName:'saveAndInsert',
            handler: function () {
                var me = this.up('detailwindow');
                me.doSave(true);
            }
        }
        , {
            text: '取消', handler: function () {
                var me = this.up('detailwindow');
                me.close();
                me.destroy();
                delete me;
                me = null;
            }
        }
    ],
    doSave: function (saveAndNew) {
        var me = this;
        var store = me.store;
        var formCmp = me.down('form');
        var form = formCmp.getForm();

        if (form.isValid()) {
            var theUrl = store.getUpdateUrl();
            if (me.isNew) {
                theUrl = store.getInsertUrl();
            }
            if (form.owner.beforePost) {
                if (form.owner.beforePost({ isNew: me.isNew, record: form.getRecord() }) === false) {
                    return;
                }
            }
            Ext.MessageBox.confirm(   
   	             "提示" ,"确认是否保存?"  
   	            ,function( button,text ){  
   	                if( button == 'yes'){ 
   	                 me.submitEdit(store,theUrl,form,me,saveAndNew); 
   	                }
   	            }   
   	        );
            
        }
    },
    submitEdit:function(store,theUrl,form,me,saveAndNew){
        form.submit({
            url: theUrl,
            success: function (form, action) {
                store.load({
                    callback: function (records, operation, success) {
                        me.isNew = saveAndNew;
                        var record = store.createModel({});
                        var idProperty = record.getIdProperty();
                        var idField = me.down('[name="' + idProperty + '"]');
                        //idField.setReadOnly(!saveAndNew);
                        debugger;
                        if (saveAndNew != true) {
                            var index = store.find(idProperty, action.result.data[idProperty]);
                            record = store.getAt(index);
                        }
                        if (form.owner.afterPost) {
                            form.owner.afterPost({ isNew: me.isNew, record: record })
                        }

                        if (form.owner.beforeLoadRecord) {
                            form.owner.beforeLoadRecord({ isNew: me.isNew, record: record });
                        }
                        form.loadRecord(record);
                        if (form.owner.afterLoadRecord) {
                            form.owner.afterLoadRecord({ isNew: me.isNew, record: record });
                        }
                        var programPrivilegeId=form.getValues().programPrivilegeId;
                        if(programPrivilegeId!=""||programPrivilegeId!=null){
                        	Ext.ComponentQuery.query("button[text=保存]")[0].setDisabled(true);
                        	Ext.ComponentQuery.query("button[text=保存并新增]")[0].setDisabled(true);
                        }
                        if(saveAndNew==true){
                        	Ext.ComponentQuery.query("button[text=保存]")[0].setDisabled(false);
                        	Ext.ComponentQuery.query("button[text=保存并新增]")[0].setDisabled(false);
                        }
                        if (saveAndNew != true && form.owner.afterSaveAction == 'keep') {
                            Ext.Msg.alert('系统提示', '已成功保存！');
                        } else if (saveAndNew != true) {
                            me.close();
                        }
                    }
                });
            },
            failure: function (form, action) {
                var message = action.response.responseText.trim().replace("\n", "<br>");
                Ext.MessageBox.show({
                    title: '系统提示',
                    msg: message,
                    buttons: Ext.MessageBox.OK,
                    icon: Ext.MessageBox.ERROR,
                });
            }
        });
    }
});