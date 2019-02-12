Ext.define("app.ux.dbgrid.MasterForm", {
    extend: "Ext.form.FormPanel",
    xtype:"app_ux_dbgrid_MasterForm",
    afterSaveAction: 'keep',

    /**
     * 函数类型   虚函数
     * 函数名称   getDetailPanel() 
     * 作用      获取明细面板
     * */

    /**
     * 函数类型   虚函数
     * 函数名称   getDetailsConfigList()
     * 作用       获取明细项配置
     * 返回格式    [{
     *                name:"salesOrderItem",
     *                store:this.down("app_view_example_masterDetail_salesOrder_salesItem_SalesItemGrid").store,
     *                filter:"ORDER_ID='${masterId}'"
     *            }]
     */

    onRecordLoad: function (config) {        
        var isNew = config.isNew;
        var record = config.record;
        if (isNew && this.getDetailPanel) {
            var detailPanel = this.getDetailPanel();
            detailPanel.setDisabled(true);

            var detailConfigList = this.getDetailsConfigList();
            for (var i = 0; i < detailConfigList.length; i++) {
                var detailConfig = detailConfigList[i];

                detailConfig.store.removeAll(true);
            }

            return;
        }

        if (!this.getDetailsConfigList) {
            return;
        }

        var detailConfigList = this.getDetailsConfigList();
        for (var i = 0; i < detailConfigList.length; i++) {
            var detailConfig = detailConfigList[i];
            var expr = detailConfig.filter;
            expr = new Ext.XTemplate(expr).apply(config.record.data);
            var expr = Ext.util.Base64.encode(expr);
            expr = 'filterExpr=' + expr;

            if (detailConfig.store.getProxy().DefaultUrl == null) {
                detailConfig.store.getProxy().DefaultUrl = detailConfig.store.getProxy().url;
            }
            var url = detailConfig.store.getProxy().DefaultUrl;
            if (url.indexOf("?") == -1) {
                url += "?";
            }
            url += expr;
            detailConfig.store.getProxy().url = url;
            detailConfig.store.load();
        }
    },

    afterPost: function (config) {
        var isNew = config.isNew;
        var record = config.record;

        if (isNew) {
            return; //如果是"新增"
        }

        if (!this.getDetailPanel) {
            return;
        }
                            
        var detailPanel = this.getDetailPanel();
        detailPanel.setDisabled(false);
    }
});