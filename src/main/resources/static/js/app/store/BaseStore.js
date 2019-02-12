Ext.define('app.store.BaseStore', {
    extend: 'Ext.data.Store',
    requires: ['app.ux.MapDataMeta'],
    mixins:["app.store.StoreOperation"],
 
    constructor: function (config) {
        this.initUrlMeta();
         
        var proxyConfig = {
            type: 'ajax',
            url: this.getSelectUrl(),           
            reader: {
                type: "json",
                rootProperty: 'rootProperty'
            }
        };

        var configBase = {autoLoad:true, proxy: proxyConfig };
        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    }
});