Ext.define("app.ux.advancedSearch.SearchItem", {
    extend: "Ext.container.Container",
    xtype: "app_ux_advancedSearch_SearchItem",
    layout: "hbox",
    margin: '5 0 0 0',

    uses: ["Ext.form.ComboBox",'app.ux.dbgrid.DbGridSearchOperatorComboBox', 'app.ux.dbgrid.DbGridSearchFieldsComboBox', 'app.ux.dbgrid.DbGridSearchFieldValueTextField'],
    defaults:{
        padding:5,
    },

    layout: 'hbox',
    constructor: function (config) {
       var configBase = {
            items: [
                { xtype: 'dbgrid_DbGridSearchFieldsComboBox',allowBlank:false, width: 200, dbGrid: config.dbGrid },
                { xtype: 'dbgrid_DbGridSearchOperatorComboBox', allowBlank:false,width: 120 },
                { xtype: 'dbgrid_DbGridSearchFieldValueTextField', width: 120 },
                {
                    xtype: 'combobox',name:"logicOperator", width: 120, store: {
                        fields: ['abbr', 'name'],
                        data: [
                            { "abbr": "and", "name": "且" },
                            { "abbr": "or", "name": "或" },
                        ]
                    },
                    queryMode: 'local',
                    valueField: 'abbr',
                    displayField: 'name'
                },
                {
                    xtype: "button", text: "删除", margin: '7 0 0 0', handler: function () {
                        this.up("advanced_search_panel").deleteSearchItem(this.up("app_ux_advancedSearch_SearchItem"));
                    }
                },
            ]
        };

        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    },    
});