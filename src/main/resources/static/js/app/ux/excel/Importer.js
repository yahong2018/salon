Ext.define("app.ux.excel.Importer", {
    extend: "Ext.form.Panel",
    xtype: "app_ux_excel_impoter",
    requires: [
        'Ext.layout.container.Card',"app.ux.excel.ImportViewModel","app.ux.excel.ImportController"
        ,"app.ux.excel.cards.FileSelectPanel","app.ux.excel.cards.DataDisplayAndSettingPanel",
        "app.ux.excel.cards.ImportFinishedPanel"
    ],
    viewModel:{
        type:"excelImportViewModel",
    },
    controller:{
        type:"excelImportController"        
    },
    layout: "card",
    items: [
        {
            xtype:"app_ux_excel_cards_FileSelectPanel"
        },{
            xtype:"app_ux_excel_cards_DataDisplayAndSettingPanel"
        },{
            xtype:"app_ux_excel_cards_ImportFinishedPanel"
        }
    ],
    bbar: [
        {
            text: "取消",
            handler: "cancelButtonClick"
        },
        "->",
        {
            text: "上一步",
            buttonId:"btnPrev",
            handler: "prevButtonClick"
        }, {
            text: "下一步",
            buttonId:"btnNext",
            handler: "nextButtonClick"
        }
    ],   
});