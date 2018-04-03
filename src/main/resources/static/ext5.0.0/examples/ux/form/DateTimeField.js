Ext.define('Ext.ux.form.DateTimeField.TimePickerField', {
    extend: 'Ext.form.field.Base',
    alias: 'widget.timepicker',

    alternateClassName: 'Ext.form.field.TimePickerField',

    requires: ['Ext.form.field.Number'],
    inputType: 'text',
    fieldLabel: '时间',
    labelWidth: 40,
    style: 'padding:4px 0; margin: 0;',
    value: null,
    spinnerCfg: {
        width: 40,
    },

    initComponent: function() {
        var me = this;
        me.value = me.value || Ext.Date.format(new Date(), 'H:i:s');
        me.callParent(arguments);
        me.spinners = [];
        var cfg = Ext.apply({}, me.spinnerCfg, {
            disabled: me.disabled,
            style: 'float: left',
            listeners: {
                change: {
                    fn: me.onSpinnerChange,
                    scope: me
                }
            }
        });
        me.hoursSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minNum: 0,
            maxNum: 23,
        }));
        me.minutesSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minNum: 0,
            maxNum: 59,
        }));
        me.secondsSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minNum: 0,
            maxNum: 59,
        }));
        me.spinners.push(me.hoursSpinner, me.minutesSpinner, me.secondsSpinner);
    },

    onRender: function() {
        var me = this, spinnerWrapDom, spinnerWrap;
        me.callParent(arguments);
        spinnerWrap = Ext.get(Ext.DomQuery.selectNode('div', this.el.dom));
        me.callSpinnersFunction('render', spinnerWrap);
        this.el.dom.getElementsByTagName('input')[0].style.display = 'none';
        var newTimePicker = Ext.DomHelper.append(spinnerWrap, {
            tag: 'div',
            cls: 'x-form-clear-left'
        }, true);
        this.setRawValue(this.value);
    },
    _valueSplit: function(v) {
        if(Ext.isDate(v)) {
            v = Ext.Date.format(v, 'H:i:s');
        }
        var split = v.split(':');
        return {
            h: split.length > 0 ? split[0] : 0,
            m: split.length > 1 ? split[1] : 0,
            s: split.length > 2 ? split[2] : 0
        };
    },
    onSpinnerChange: function() {
        if(!this.rendered) {
            return;
        }
        //限制时间范围
        var args = arguments; //this, newValue, oldValue, eOpts
        args[0].setValue( (args[1]>args[0].maxNum) ? args[0].minNum : args[0].value );
        args[0].setValue( (args[1]<args[0].minNum) ? args[0].maxNum : args[0].value );
        this.fireEvent('change', this, this.getValue(), this.getRawValue());
    },

    // 依次调用各输入框函数, call each spinner's function
    callSpinnersFunction: function(funName, args) {
        for(var i = 0; i < this.spinners.length; i++) {
            if( this.spinners[i][funName] != null && this.spinners[i][funName] != undefined ){
                this.spinners[i][funName](args);
            }
        }
    },

    getRawValue: function() {
        if(!this.rendered) {
            var date = this.value || new Date();
            return this._valueSplit(date);
        }
        else {
            return {
                h: this.hoursSpinner.getValue(),
                m: this.minutesSpinner.getValue(),
                s: this.secondsSpinner.getValue()
            };
        }
    },
    setRawValue: function(value) {
        var v = this._valueSplit(value);
        if(this.hoursSpinner) {
            this.hoursSpinner.setValue(v.h);
            this.minutesSpinner.setValue(v.m);
            this.secondsSpinner.setValue(v.s);
        }
    },

    getValue: function() {
        var v = this.getRawValue();
        return Ext.String.leftPad(v.h, 2, '0') + ':' + Ext.String.leftPad(v.m, 2, '0') + ':'
            + Ext.String.leftPad(v.s, 2, '0');
    },

    setValue: function(value) {
        this.value = Ext.isDate(value) ? Ext.Date.format(value, 'H:i:s') : value;
        if(!this.rendered) {
            return;
        }
        this.setRawValue(this.value);
        this.validate();
    },

    disable: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('disable', arguments);
    },

    enable: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('enable', arguments);
    },

    setReadOnly: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('setReadOnly', arguments);
    },

    clearInvalid: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('clearInvalid', arguments);
    },

    isValid: function(preventMark) {
        return this.hoursSpinner.isValid(preventMark) && this.minutesSpinner.isValid(preventMark)
            && this.secondsSpinner.isValid(preventMark);
    },

    validate: function() {
        return this.hoursSpinner.validate() && this.minutesSpinner.validate() && this.secondsSpinner.validate();
    }
});

Ext.define('Ext.ux.form.DateTimeField.DateTimePicker', {
    extend: 'Ext.picker.Date',
    alias: 'widget.datetimepicker',
    requires: ['Ext.ux.form.DateTimeField.TimePickerField','Ext.dom.Query'],

    todayText: '现在',
    timeLabel: '时间',
    buttonText: '确定',

    initComponent: function() {
        this.callParent();
        this.value = this.value || new Date();
    },
    onRender: function(container, position) {
        this.callParent(arguments);
        var me = this;


        //确认按键
        var btnCfg = Ext.apply({}, {}, {
            style: 'center',
            listeners: {
                click: {
                    fn: function(){
                        this.confirmDate();
                    },
                    scope: me
                }
            }
        });
        me.confirmBtn = Ext.create('Ext.Button', Ext.apply({}, btnCfg, {
            text: '确认',
        }));
        me.confirmBtn.render(this.el.child('div div.x-datepicker-footer'));


        if(!this.timefield) {
            this.timefield = Ext.create('Ext.ux.form.DateTimeField.TimePickerField', {
                fieldLabel: this.timeLabel,
                labelWidth: 40,
                value: Ext.Date.format(this.value, 'H:i:s'),
            });
        }

        this.timefield.ownerCt = this;//指定范围
        this.timefield.on('change', this.timeChange, this);//

        var table = Ext.get(Ext.DomQuery.selectNode('table', this.el.dom));

        var tfEl = Ext.DomHelper.insertAfter(table, {
            tag: 'div',
            style: 'border:0px;',
            children: [{
                tag: 'div',
                cls: 'x-datepicker-footer ux-timefield'
            }]
        }, true);
        this.timefield.render(this.el.child('div div.ux-timefield'));

        var p = this.getEl().parent('div.x-layer');
        if(p) {
            p.setStyle("height", p.getHeight() + 31);
        }
    },
    // listener 时间域修改, timefield change
    timeChange: function(tf, time, rawtime) {
        this.value = this.fillDateTime(this.value);
    },
    fillDateTime: function(value) {
        if(this.timefield) {
            var rawtime = this.timefield.getRawValue();
            value.setHours(rawtime.h);
            value.setMinutes(rawtime.m);
            value.setSeconds(rawtime.s);
        }
        return value;
    },
    changeTimeFiledValue: function(value) {
        this.timefield.un('change', this.timeChange, this);
        this.timefield.setValue(this.value);
        this.timefield.on('change', this.timeChange, this);
    },
    setValue: function(value) {
        this.value = value;
        this.changeTimeFiledValue(value);
        return this.update(this.value);
    },
    getValue: function() {
        return this.fillDateTime(this.value);
    },
    handleDateClick: function(e, t) {
        var me = this,
            handler = me.handler;
        e.stopEvent();
        if(!me.disabled && t.dateValue && !Ext.fly(t.parentNode).hasCls(me.disabledCellCls)) {
            me.doCancelFocus = me.focusOnSelect === false;
            me.setValue(this.fillDateTime(new Date(t.dateValue)));
            delete me.doCancelFocus;
            me.fireEvent('select', me, me.value);
            if(handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
    },
    //确认按键
    confirmDate: function(){
        var that = this;
        that.fireEvent('select', that, that.value);//模拟用户选择
        that.onSelect();
    },
    selectToday: function() {
        var me = this,
            btn = me.todayBtn,
            handler = me.handler;
        if(btn && !btn.disabled) {
            me.setValue(new Date());
            me.fireEvent('select', me, me.value);
            if(handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
        return me;
    }
});

Ext.define('Ext.ux.form.DateTimeField.DateTimeField', {
    extend: 'Ext.form.field.Date',
    alias: 'widget.datetimefield',
    requires: ['Ext.ux.form.DateTimeField.DateTimePicker'],
    initComponent: function() {
        this.format = this.format;
        this.callParent();
    },
    format: 'Y-m-d H:i:s',
    createPicker: function() {
        var me = this,
            format = Ext.String.format;
        return Ext.create('Ext.ux.form.DateTimeField.DateTimePicker', {
            ownerCt: me.ownerCt,
            floating: true,
            focusOnShow: true,
            minDate: me.minValue,
            maxDate: me.maxValue,
            disabledDatesRE: me.disabledDatesRE,
            disabledDatesText: me.disabledDatesText,
            disabledDays: me.disabledDays,
            disabledDaysText: me.disabledDaysText,
            format: me.format,
            showToday: me.showToday,
            startDay: me.startDay,
            minText: format(me.minText, me.formatDate(me.minValue)),
            maxText: format(me.maxText, me.formatDate(me.maxValue)),
            listeners: {
                scope: me,
                select: me.onSelect
            },
            keyNavConfig: {
                esc: function() {
                    me.collapse();
                }
            }
        });
    }
});