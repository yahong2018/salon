Ext.define("app.ux.dbgrid.ItemDetailForm", {
    extend: "Ext.form.Panel",
    xtype:"app_ux_dbgrid_ItemDetailForm",
    
    /**
     * 函数名称  getMasterFieldName
     *   作用   获取主记录的引用字段
     *   比如订单明细  SalesOrderItem 属于 SalesOrder，通过 SalesOrder.orderId 来实现
     * 
     *   getMasterFieldName:function(){
     *      return {
     *           detailField:"orderId",
     *           masterField:"orderId"
     *      };
     *   }
     */

    onRecordLoad: function (config) {      
        if (!config.isNew) {
            return;
        }

        if (!this.getMasterFieldName) {
            return;
        }

        var masterForm = this.ownerCt.listGrid.up("app_ux_dbgrid_MasterForm");
        var masterRecord={};
        if(masterForm){
            masterRecord = masterForm.getRecord();
        }else if(this.ownerCt.listGrid.getMasterGrid)   {              
            var masterGrid = this.ownerCt.listGrid.getMasterGrid();
            masterRecord = masterGrid.getSelectedRecord();
        }else{
            return;
        }
        var masterDetailFieldName = this.getMasterFieldName();
        config.record.set(masterDetailFieldName.detailField,masterRecord.get(masterDetailFieldName.masterField));
    }
});