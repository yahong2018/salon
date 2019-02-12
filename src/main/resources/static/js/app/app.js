Ext.Loader.setConfig({
    enabled : true
});

Ext.Loader.setPath({
    // 'Ext.ux' : jsRoot + '/ext/ux',
    // 'Ext.app' : jsRoot + '/ext/app',
    'app' : jsRoot + '/app',
  //  'tmpl' : jsRoot + "/template/manage/create",
  //  'common' : jsRoot + '/zhxh'
});

Ext.tip.QuickTipManager.init();

Ext.application({
    name: 'imms',
    // paths: {
    //    'app': jsRoot + '/app'
    // },
    extend: 'app.Application',
    requires: [        
        'app.view.main.Main'
    ],
    mainView: 'app.view.main.Main'
});