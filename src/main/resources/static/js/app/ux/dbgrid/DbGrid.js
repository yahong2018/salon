Ext.define('app.ux.dbgrid.DbGrid', {
    extend: 'Ext.grid.GridPanel',
    xtype: 'dbgrid',
    alias: 'widget.dbgrid',

    requires:["app.ux.data.DataOperation"],
    mixins:["app.ux.data.DataOperation"],    

    constructor: function (config) {
        var configBase = this.getInitConfig();
        Ext.applyIf(config, configBase);        

        this.callParent(arguments);
    },

    initComponent: function () {
        this.internalInitComponent();      
        this.callParent(arguments);
    }
});