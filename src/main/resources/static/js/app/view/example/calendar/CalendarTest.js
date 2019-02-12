if (!Ext.calendar) {
    Ext.calendar = {}
}
if (!Ext.calendar.date) {
    Ext.calendar.date = {}
}
if (!Ext.calendar.dd) {
    Ext.calendar.dd = {}
}
if (!Ext.calendar.form) {
    Ext.calendar.form = {}
}
if (!Ext.calendar.header) {
    Ext.calendar.header = {}
}
if (!Ext.calendar.model) {
    Ext.calendar.model = {}
}
if (!Ext.calendar.panel) {
    Ext.calendar.panel = {}
}
if (!Ext.calendar.store) {
    Ext.calendar.store = {}
}
if (!Ext.calendar.theme) {
    Ext.calendar.theme = {}
}

if (!Ext.calendar.util) {
    Ext.calendar.util = {}
}
if (!Ext.calendar.view) {
    Ext.calendar.view = {}
}

if (!Ext.overrides.calendar) {
    Ext.overrides.calendar = {}
}
if (!Ext.overrides.calendar.panel) {
    Ext.overrides.calendar.panel = {}
}
if (!Ext.overrides.calendar.view) {
    Ext.overrides.calendar.view = {}
}


(Ext.cmd.derive("Ext.calendar.EventBase"
    , Ext.Gadget
    , {
        config: {
            defaultTitle: "(New Event)"
            , endDate: null
            , mode: null
            , model: null
            , palette: null
            , resize: false
            , startDate: null
            , title: ""
            , touchAction: {
                panX: false
                , panY: false
            }
            , view: null
        }
        , cloneForProxy: function () {
            var b = this.self;
            return new b(this.config)
        }
        , updateModel: function (e) {
            var d = this, g;
            if (e) {
                d.setStartDate(e.getStartDate());
                d.setEndDate(e.getEndDate());
                d.setTitle(e.getTitle());
                g = d.element.dom;
                g.setAttribute("data-eventId", e.id);
                g.setAttribute("data-calendarId", e.getCalendarId())
            }
        }
        , updateResize: function (b) {
            this.toggleCls(this.$allowResizeCls, b)
        }
        , privates: {
            $allowResizeCls: "x-calendar-event-resizable"
        }
    }
    , 0
    , 0
    , ["widget"]
    , { widget: true }
    , 0,
    0,
    [Ext.calendar, "EventBase"],
    0
));