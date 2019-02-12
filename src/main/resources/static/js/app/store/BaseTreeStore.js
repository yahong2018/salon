Ext.define('app.store.BaseTreeStore', {
    extend: 'Ext.data.TreeStore',
    requires: ['app.ux.MapDataMeta'],
    mixins:["app.store.StoreOperation"],
 
    constructor: function (config) {
        this.initUrlMeta();
         
        var proxyConfig = {
			type: 'ajax',
			url: this.getSelectUrl()
		}
        var configBase = {autoLoad:true, proxy: proxyConfig };
        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    }
});