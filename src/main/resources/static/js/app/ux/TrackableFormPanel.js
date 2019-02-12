Ext.define('app.ux.TrackableFormPanel', {
    extend: 'Ext.form.FormPanel',
    alias: "widget.app_ux_TrackableFormPanel",

    constructor: function (config) {
        var configBase = {
            items: [{
                xtype: "hidden",
                name: "createBy",
            }, {
                xtype: "hidden",
                name: "createDate",
            }, {
                xtype: "hidden",
                name: "updateBy",
            }, {
                xtype: "hidden",
                name: "updateDate",
            }, {
                xtype: "hidden",
                name: "optLock",
            }, {
                xtype: "hidden",
                name: "delFlag",
            }]
        };

        Ext.applyIf(config,configBase);

        this.callParent(arguments);
    }
});