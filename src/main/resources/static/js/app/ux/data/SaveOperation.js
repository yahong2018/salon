Ext.define("app.ux.data.SaveOperation", {
    xtype: 'app_ux_data_SaveOperation',
    uses: ['app.ux.Utils', 'Ext.window.Toast'],

    doSave: function (saveAndNew) {
        var me = this;
        var store = me.store;
        var formCmp = me.getFormCmp();
        var form = formCmp.getForm();
        var grid = me.listGrid;

        if (form.isValid()) {
            var theUrl = store.getUpdateUrl();
            if (me.dataMode == app.ux.data.DataMode.INSERT) {
                theUrl = store.getInsertUrl();
            }
            if (formCmp.beforePost) {
                if (formCmp.beforePost({ dataMode: me.dataMode, seq: app.ux.data.DataOperationSeq.BEFORE, record: form.getRecord() }) === false) {
                    return;
                }
            }

            form.submit({
                url: theUrl,
                success: function (form, action) {
                    store.load({
                        callback: function (records, operation, success) {
                            var theNewRecord = store.createModel({});
                            var idProperty = theNewRecord.getIdProperty();
                            var idField = me.down('[name="' + idProperty + '"]');
                            idField.setReadOnly(!saveAndNew);
                            var oldIndex = store.find(idProperty, action.result.data[idProperty]);
                            var oldRecord = store.getAt(oldIndex);

                            var callConfig = {
                                dataMode: saveAndNew == true ? app.ux.data.DataMode.INSERT : app.ux.data.DataMode.POST,
                                seq: saveAndNew == true ? app.ux.data.DataOperationSeq.BEFORE : app.ux.data.DataOperationSeq.AFTER,
                                record: saveAndNew == true ? theNewRecord : oldRecord
                            };

                            if (formCmp.afterPost) {
                                formCmp.afterPost(callConfig)
                            }
                            formCmp.loadRecord(callConfig.record);

                            if (formCmp.onRecordLoad) {
                                formCmp.onRecordLoad(callConfig);
                            }

                            if (saveAndNew != true && form.owner.afterSaveAction == 'keep') {
                                // Ext.Msg.alert('系统提示', '已成功保存！');
                            } else if (saveAndNew != true) {
                                me.close();
                            }
                            if (saveAndNew === true) {
                                me.dataMode = app.ux.data.DataMode.INSERT;
                            }
                            Ext.toast({
                                html: '数据已保存',
                                title: '系统提示',
                                width: 200,
                                align: 't'
                            });
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
    }
});