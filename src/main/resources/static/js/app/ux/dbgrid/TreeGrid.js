Ext.define("app.ux.dbgrid.TreeGrid",{
    extend:"Ext.tree.Panel",
    xtype: 'treeGrid',
    alias: 'widget.treeGrid',
    requires:["app.ux.data.DataOperation","app.ux.dbgrid.DbGridToolbar"],
    mixins:["app.ux.data.DataOperation"],   

    hideDefeaultPagebar:true,    
    hideSearchBar:true,

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