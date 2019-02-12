Ext.define("app.ux.excel.ImportController", {
    extend: "Ext.app.Controller",
    alias: "controller.excelImportController",

    cancelButtonClick: function () {                  
    },

    prevButtonClick: function () {
        this.doCardNavigation(-1);
    },

    nextButtonClick: function () {
        this.doCardNavigation(1);       
    },
    
    doCardNavigation: function (incr) {
        debugger;

        var me = this.getView();  
        var viewModel = this.getViewModel();
        viewModel.navigator.activeIndex = viewModel.navigator.activeIndex + incr;
        me.getLayout().setActiveItem(viewMode.navigator.activeIndex);

        me.down('[buttonId="btnPrev"]').setDisabled(next === 0);
        if (next == 2) {
            me.down('[buttonId="btnNext"]').setText("完成");
        } else {
            me.down('[buttonId="btnNext"]').setText("下一步");
        }
    }
});