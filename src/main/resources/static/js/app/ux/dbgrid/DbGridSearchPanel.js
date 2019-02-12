Ext.define('app.ux.dbgrid.DbGridSearchPanel', {
    extend: 'Ext.container.Container',
    xtype: 'dbgrid_DbGridSearchPanel',
    uses: ['app.ux.dbgrid.DbGridSearchOperatorComboBox', 'app.ux.dbgrid.DbGridSearchFieldsComboBox', 'app.ux.dbgrid.DbGridSearchFieldValueTextField'],

    layout: 'hbox',
    constructor: function (config) {
        var configBase = {
            items: [
                { xtype: 'dbgrid_DbGridSearchFieldsComboBox', width: 200, dbGrid: config.dbGrid },
                { xtype: 'dbgrid_DbGridSearchOperatorComboBox', width: 120 },
                { xtype: 'dbgrid_DbGridSearchFieldValueTextField', width: 120 },
                {
                    xtype: 'splitbutton', glyph: 0xf002, tooltip: '查找', handler: function () {
                        this.up('dbgrid_DbGridSearchPanel').doSearch();
                    },
                    menu: [
                        {
                            text: '搜索', handler: function () {
                                this.up('dbgrid_DbGridSearchPanel').doSearch();
                            }
                        },
                        {
                            text: '高级搜索', handler: function () {
                                var grid = this.up('dbgrid');
                                grid.advancedSearch();
                            }
                        },
                    ]
                }
            ]
        };

        Ext.applyIf(config, configBase);

        this.callParent(arguments);
    },
    doSearch: function () {
        var grid = this.up('dbgrid');
        if (grid.doSearch) {
            var panel = this;
            var field = panel.down('dbgrid_DbGridSearchFieldsComboBox').getSelectedRecord();
            var operator = panel.down('dbgrid_DbGridSearchOperatorComboBox').getSelectedRecord();
            var value = panel.down('dbgrid_DbGridSearchFieldValueTextField').getValue();

            grid.doSearch(field, operator, value);
        };
    }
})