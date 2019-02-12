Ext.define("app.ux.data.DataOperation", {
    xtype: 'app_ux_data_DataOperation',

    requires: ['app.ux.dbgrid.DbGridToolbar'],
    uses: ['app.ux.dbgrid.DetailWindow', 'Ext.util.Base64', 'app.ux.advancedSearch.SearchWindow',
           'app.ux.Utils', 'app.ux.data.DataMode'],

    getInitConfig: function () {
        var me = this;
        return {
            columnLines: true,
            style: 'padding-left:5px;',
            insertDetailWindowClass: 'app.ux.dbgrid.DetailWindow',
            editDetailWindowClass: 'app.ux.dbgrid.DetailWindow',
            frame: true,
            listeners: {
                rowdblclick: function (grid, rowindex, e) {
                    me.showDetailWindow(grid, rowindex, e);
                }
            }
        };
    },

    internalInitComponent: function () {
        var e = {sender:this};
        if(this.beforeComponentInit){
            this.beforeComponentInit(e);
        }

        var haseDefaultPagebar = false;
        var hasDefaultToolbar = false;
        var me = this;

        this.selModel = new Ext.selection.CheckboxModel();

        if (me.dockedItems) {
            for (var i = 0; i < me.dockedItems.length; i++) {
                var item = me.dockedItems[i];
                if (item.xtype == "pagingtoolbar") {
                    haseDefaultPagebar = true;
                    continue;
                }

                if (item.xtype == "dbgridtoolbar") {
                    hasDefaultToolbar = true;
                    item.dbGrid = me;
                    continue;
                }
            }
        } else {
            me.dockedItems = [];
        }

        if (!haseDefaultPagebar && !me.hideDefeaultPagebar) {
            me.dockedItems.unshift(
                Ext.create({
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    store: me.store,
                    displayInfo: true,
                })
            );
        }

        if (!hasDefaultToolbar && !me.hideDefaultToolbar) {
            var config = {
                xtype: 'dbgridtoolbar',
                dbGrid: me,
                hideSearchBar: me.hideSearchBar
            };

            var defaultToolbar = Ext.create(config);
            me.dockedItems.push(defaultToolbar);
        }

        if(this.afterComponentInit){
            this.afterComponentInit(e);
        }
    },

    createDetailWindow: function (dataMode) {
        var detailWindow;
        if (this.createDetailWindowFun) {
            detailWindow = this.createDetailWindowFun();
        } else {
            detailWindow = Ext.create(this.insertDetailWindowClass, {
                dataMode: dataMode,
                store: this.store,
                listGrid: this,
                items: [{
                    xtype: this.detailFormClass,
                }]
            });
        }

        return detailWindow;
    },

    doInsert: function (config) {
        config=config||{};
        var grid = this;
        if (this.beforeInsert) {
            if (this.beforeInsert(config.sender) === false) {
                return;
            }
        }
        var record = this.store.createModel({});
        var detailWindow = this.createDetailWindow(app.ux.data.DataMode.INSERT);
        detailWindow.title = '新增 — [' + this.detailWindowTitle + ']';

        var form = detailWindow.getFormCmp();
        form.loadRecord(record);
        if (form.onRecordLoad) {
            form.onRecordLoad({
                dataMode: app.ux.data.DataMode.INSERT,
                seq: app.ux.data.DataOperationSeq.BEFORE,
                record: record,
                sender:config.sender
            });
        }

        var currentTopWindow = Ext.app.Application.instance.getMainView().down('maincenter').getActiveTab();
        var programId = currentTopWindow.menuData.get('programId');
        var canInsert = app.ux.Utils.hasPrivilege({ programId: programId, privilegeCode: "INSERT" });
        if (!canInsert) {
            detailWindow.down('[buttonName="save"]').setDisabled(true);
            detailWindow.down('[buttonName="saveAndInsert"]').setDisabled(true);
        }

        detailWindow.show();
    },
    
    doEdit: function (config) {
        config = config||{};
        var grid = this;
        var record = this.getSelectionModel().getSelection();
        if (!record || record.length == 0) {
            Ext.MessageBox.alert("系统提示", "请先选择一条待编辑记录！");
            return;
        }

        record = record[0];
        if (this.beforeEdit) {
            if (this.beforeEdit(record,config.sender) === false) {
                return;
            }
        }

        var detailWindow = this.createDetailWindow(app.ux.data.DataMode.EDIT);
        detailWindow.title = '修改 — [' + this.detailWindowTitle + ']';
        var form = detailWindow.getFormCmp();
        form.loadRecord(record);
        var idField = form.down('[name="' + record.getIdProperty() + '"]');
        if (idField) {
            idField.readOnly = true;
        }

        if (form.onRecordLoad) {
            form.onRecordLoad({
                dataMode: app.ux.data.DataMode.EDIT,
                seq: app.ux.data.DataOperationSeq.BEFORE,
                record: record,
                sender:config.sender
            });
        }

        var currentTopWindow = Ext.app.Application.instance.getMainView().down('maincenter').getActiveTab();
        var programId = currentTopWindow.menuData.get('programId');
        var canUpdate = app.ux.Utils.hasPrivilege({ programId: programId, privilegeCode: "UPDATE" });
        if (!canUpdate) {
            detailWindow.down('[buttonName="save"]').setDisabled(true);
            detailWindow.down('[buttonName="saveAndInsert"]').setDisabled(true);
        }

        detailWindow.show();
    },

    showDetailWindow: function (grid, rowindex, e) {
        var grid = this;
        var record = this.getSelectionModel().getSelection();
        if (!record || record.length == 0) {
            return;
        }

        record = record[0];
        var detailWindow = this.createDetailWindow(app.ux.data.DataMode.BROWSE);
        detailWindow.title = this.detailWindowTitle;
        var form = detailWindow.getFormCmp();
        form.loadRecord(record);
        var idField = form.down('[name="' + record.getIdProperty() + '"]');
        if (idField) {
            idField.readOnly = true;
        }

        if (form.onRecordLoad) {
            form.onRecordLoad({
                dataMode: app.ux.data.DataMode.BROWSE,
                seq: app.ux.data.DataOperationSeq.BEFORE,
                record: record,
                sender:grid
            });
        }

        var currentTopWindow = Ext.app.Application.instance.getMainView().down('maincenter').getActiveTab();
        var programId = currentTopWindow.menuData.get('programId');

        var canUpdate = app.ux.Utils.hasPrivilege({ programId: programId, privilegeCode: "UPDATE" });
        if (!canUpdate) {
            detailWindow.down('[buttonName="save"]').setDisabled(true);
            detailWindow.down('[buttonName="saveAndInsert"]').setDisabled(true);
        }

        detailWindow.show();
    },

    doSearch: function (column, operator, value) {
        debugger;

        var grid = this;
        var expr = "";
        grid.getStore().clearCustomerFilter();        
        if (column != null && operator != null && value != '' && value != null) {
            var model = grid.store.getModel();
            if (model == null) {
                return;
            }
            var fieldName = column.get('dataIndex');
            if (fieldName == null) {
                return;
            }

            var field = model.getField(fieldName);
            if (field == null) {
                return;
            }
            if (field.dbFieldName != null) {
                fieldName = field.dbFieldName;
            }

            expr = fieldName + ' ' + operator.get('abbr');
            var fieldType = field.type;
            if (fieldType == 'string' || fieldType == 'date') {
                expr = expr + "'" + value + "'";
            } else {
                expr = expr + value;
            }            
            grid.getStore().addCustomFilter(expr);

            // expr = Ext.util.Base64.encode(expr);
            // expr = 'filterExpr=' + expr;
        }
        /*
        if (grid.getStore().getProxy().DefaultUrl == null) {
            grid.getStore().getProxy().DefaultUrl = grid.getStore().getProxy().url;
        }
        var url = grid.getStore().getProxy().DefaultUrl;
        if (url.indexOf("?") == -1) {
            url += "?";
        }
        url += expr;
        grid.getStore().getProxy().url = url;
        */

        grid.getStore().buildFilterUrl();
        grid.getStore().load();
    },
    advancedSearch: function () {
        var grid = this;
        Ext.create("app.ux.advancedSearch.SearchWindow", {
            title: "高级查询",
            dbGrid: grid,
        }).show();
    },
    getSelectedRecord: function () {
        var record = this.getSelectionModel().getSelection();
        if (!record || record.length == 0) {
            return null;
        }
        return record[0];
    },

    listeners: {
        destroy: function (me, eOpts) {
            // debugger;            
        }
    }

});
