Ext.define('app.ux.WebSocket', {
    /*
      websocket:
      url:'ws://localhost:8080/Chat/message',
    */
    constructor: function(config) {
        config = config || {};

        this.url = config.url;
        this.onConnected = config.onConnected;
        this.onError = config.onError;
        this.onClose = config.onClose;
        this.onMessage = config.onMessage;

        this.callParent(arguments);
    },

    open: function() {
        var websocket = new WebSocket(this.url);
        this.websocket = websocket;
        var me = this;
        websocket.onopen = function() {
            if (me.onConnected) {
                me.onConnected();
            }
        }
        websocket.onerror = function(evt) {
            if (me.onError) {
                me.onError(evt);
            }
        }
        websocket.onclose = function() {
            if (me.onClose) {
                me.onClose();
            }
            me.websocket = null;
        }
        websocket.onMessage = function(evt) {
            if (me.onMessage) {
                me.onMessage(evt.data);
            }
        }

    },
    close: function() {
        this.websocket.close();
        this.websocket = null;
    },
    send: function(msg) {
        this.websocket.send(msg);
    }
});