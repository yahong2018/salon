Ext.define("app.ux.advancedSearch.SearchWindow", {
    extend: "Ext.window.Window",
    xtype: "advanced_search_panel",
    uses: ["app.ux.advancedSearch.SearchItem"],
    modal: true,
    bodyPadding: 5,
    autoScroll: true,
    layout: {
        type: "vbox",
        align: 'stretch'
    },
    width: 700,
    tbar: [
        {
            xtype: "button", text: "新增", handler: function () {
                this.up("advanced_search_panel").addSearchItem();
            }
        },
        {
            xtype: "button", text: "清除", handler: function () {
                this.up("advanced_search_panel").clearAllSearchItems();
            }
        },
    ],
    items: [
        {
            xtype: "form",
            name: "panel_search_fields",
            height: 250,
            autoScroll: true,
            defaults: {
                layout: "hbox",
                margin: '5 0 0 0',
            },
        }
    ],
    buttons: [
        /*
        {
            text:"导入",
        },{
            text:"保存"
        },*/
        '->', {
            text: "查询",
            handler: function () {
                var me = this.up("advanced_search_panel");
                var search_panel = me.getSearchPanel();
                if (!search_panel.isValid()) {
                    return;
                }

                var expr = me.getSearchCondition();
                // expr = Ext.util.Base64.encode(expr);
                // if (expr != "") {
                //     expr = 'filterExpr=' + expr;
                // }
                var grid = me.dbGrid;

                // if (grid.getStore().getProxy().DefaultUrl == null) {
                //     grid.getStore().getProxy().DefaultUrl = grid.getStore().getProxy().url;
                // }
                // var url = grid.getStore().getProxy().DefaultUrl;
                // if (url.indexOf("?") == -1) {
                //     url += "?";
                // }
                // url += expr;
                // grid.getStore().getProxy().url = url;
                grid.getStore().clearCustomerFilter();
                grid.getStore().addCustomFilter(expr);
                grid.getStore().buildFilterUrl();
                grid.getStore().load();
            }
        }, {
            text: "取消",
            handler: function () {
                var me = this.up("advanced_search_panel");
                me.close();
                me.destroy();
                delete me;
                me = null;
            }
        }
    ],
    getSearchPanel: function () {
        return search_panel = this.down('[name="panel_search_fields"]');
    },
    addSearchItem: function () {
        var search_panel = this.getSearchPanel();
        search_panel.add(Ext.create({
            xtype: 'app_ux_advancedSearch_SearchItem',
            dbGrid: this.dbGrid
        }));
    },
    deleteSearchItem: function (item) {
        var search_panel = this.getSearchPanel();
        search_panel.items.remove(item);

        item.destroy();
        delete item;
    },
    clearAllSearchItems: function () {
        var search_panel = this.getSearchPanel();
        while (search_panel.items.getCount() > 0) {
            var item = search_panel.items.removeAt(0);
            item.destroy();
            delete item;
        }
    },

    getSearchCondition: function () {
        var search_panel = this.getSearchPanel();
        var expr = "";
        for (var i = 0; i < search_panel.items.getCount(); i++) {
            var item = search_panel.items.getAt(i);
            expr = expr + "(" + this.getExpr(item) + ")";

            if (i < search_panel.items.getCount() - 1) {
                var logicOpreator = item.down('[name="logicOperator"]').getSelectedRecord();
                if (logicOpreator == null) {
                    logicOpreator = " and ";
                } else {
                    logicOpreator = logicOpreator.get("abbr");
                }
                expr = expr + logicOpreator;
            }
        }

        return expr;
    },

    getExpr: function (item) {
        var column = item.down("dbgrid_DbGridSearchFieldsComboBox").getSelectedRecord();
        var operator = item.down("dbgrid_DbGridSearchOperatorComboBox").getSelectedRecord();
        var value = item.down("dbgrid_DbGridSearchFieldValueTextField").value || "";
        var grid = this.dbGrid;

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

        var expr = fieldName + ' ' + operator.get('abbr');
        var fieldType = field.type;
        if (fieldType == 'string' || fieldType == 'date') {
            expr = expr + "'" + value + "'";
        } else {
            expr = expr + value;
        }

        return expr;
    }
});