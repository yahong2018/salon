Ext.define("app.ux.excel.cards.FileSelectPanel",{
    extend:"Ext.panel.Panel",
    alias: 'widget.app_ux_excel_cards_FileSelectPanel',     
    layout: 'anchor',
    defaults: {
        anchor: '100%'
    },
    items:[
        {
            xtype:"label",
            text:"请选择需要上传文件。所上传文件必须符合格式。可以点击“单击下载模板”下载上传的模板，根据模板填写以减少错误。"
        },
        {
            xtype: 'fileuploadfield',
            hideLabel: true,
        },{
            xtype:"label",
            html:"<a href='{template.url}'>单击下载模板</a>"
        }
    ]
});