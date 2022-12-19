new tempusDominus.TempusDominus(document.getElementById('datetimepicker1'),
    {
        restrictions: {
            minDate: undefined,
            maxDate: undefined,
            disabledDates: [],
            enabledDates: [],
            daysOfWeekDisabled: [],
            disabledTimeIntervals: [],
            disabledHours: [],
            enabledHours: []
        },
        display: {
            icons: {
                type: 'icons',
                time: 'fa-solid fa-clock',
                date: 'fa-solid fa-calendar',
                up: 'fa-solid fa-arrow-up',
                down: 'fa-solid fa-arrow-down',
                previous: 'fa-solid fa-chevron-left',
                next: 'fa-solid fa-chevron-right',
                today: 'fa-solid fa-calendar-check',
                clear: 'fa-solid fa-trash',
                close: 'fa-solid fa-xmark'
            },
            sideBySide: false,
            calendarWeeks: false,
            viewMode: 'calendar',
            toolbarPlacement: 'bottom',
            keepOpen: false,
            buttons: {
                today: false,
                clear: false,
                close: false
            },
            components: {
                calendar: true,
                date: true,
                month: true,
                year: true,
                decades: true,
                clock: true,
                hours: true,
                minutes: true,
                seconds: false,
                useTwentyfourHour: undefined
            },
            inline: false,
            theme: 'auto'
        },
        stepping: 1,
        useCurrent: true,
        defaultDate: undefined,
        localization: {
            today: 'Go to today',
            clear: 'Clear selection',
            close: 'Close the picker',
            selectMonth: 'Select Month',
            previousMonth: 'Previous Month',
            nextMonth: 'Next Month',
            selectYear: 'Select Year',
            previousYear: 'Previous Year',
            nextYear: 'Next Year',
            selectDecade: 'Select Decade',
            previousDecade: 'Previous Decade',
            nextDecade: 'Next Decade',
            previousCentury: 'Previous Century',
            nextCentury: 'Next Century',
            pickHour: 'Pick Hour',
            incrementHour: 'Increment Hour',
            decrementHour: 'Decrement Hour',
            pickMinute: 'Pick Minute',
            incrementMinute: 'Increment Minute',
            decrementMinute: 'Decrement Minute',
            pickSecond: 'Pick Second',
            incrementSecond: 'Increment Second',
            decrementSecond: 'Decrement Second',
            toggleMeridiem: 'Toggle Meridiem',
            selectTime: 'Select Time',
            selectDate: 'Select Date',
            dayViewHeaderFormat: { month: 'long', year: '2-digit' },
            locale: 'default',
            startOfTheWeek: 0,
            /**
             * This is only used with the customDateFormat plugin
             */
            dateFormats: {
                LTS: 'h:mm:ss T',
                LT: 'h:mm T',
                L: 'MM/dd/yyyy',
                LL: 'MMMM d, yyyy',
                LLL: 'MMMM d, yyyy h:mm T',
                LLLL: 'dddd, MMMM d, yyyy h:mm T',
                LLLLL: 'yyyy-MM-dd HH:mm'
            },
            /**
             * This is only used with the customDateFormat plugin
             */
            ordinal: (n) => n,
            /**
             * This is only used with the customDateFormat plugin
             */
            format: 'L'
        },
        keepInvalid: false,
        debug: false,
        allowInputToggle: false,
        viewDate: new DateTime(),
        multipleDates: false,
        multipleDatesSeparator: '; ',
        promptTimeOnDateChange: false,
        promptTimeOnDateChangeTransitionDelay: 200,
        meta: {},
        container: undefined
    }
)

new tempusDominus.TempusDominus(document.getElementById('datetimepicker1'),
    {
        display: {
            icons: {
                type: 'icons',
                time: 'fa-solid fa-clock',
                date: 'fa-solid fa-calendar',
                up: 'fa-solid fa-arrow-up',
                down: 'fa-solid fa-arrow-down',
                previous: 'fa-solid fa-chevron-left',
                next: 'fa-solid fa-chevron-right',
                today: 'fa-solid fa-calendar-check',
                clear: 'fa-solid fa-trash',
                close: 'fa-solid fa-xmark'
            },
            sideBySide: false,
            calendarWeeks: false,
            viewMode: 'calendar',
            toolbarPlacement: 'bottom',
            keepOpen: false,
            buttons: {
                today: false,
                clear: false,
                close: false
            },
            components: {
                calendar: true,
                date: true,
                month: true,
                year: true,
                decades: true,
                clock: true,
                hours: true,
                minutes: true,
                seconds: false,
                //deprecated use localization.hourCycle = 'h24' instead
                useTwentyfourHour: undefined
            },
            inline: false,
            theme: 'auto'
        }
    }
)

// new tempusDominus.TempusDominus(document.getElementById('datetimepicker1'), {
//    localization: pl-PL
//}

const picker = new tempusdominus
    .TempusDominus(document.getElementById('datetimepicker1'));

picker.enable();

const subscription = picker.subscribe(tempusdominus.Namespace.events.change, (e) => {
    console.log(e);
});

// event listener can be unsubscribed to:
subscription.unsubscribe();

//you can also provide multiple events:
const subscriptions = picker.subscribe(
    [tempusdominus.Namespace.events.show,tempusdominus.Namespace.events.hide],
    [(e)=> console.log(e), (e) => console.log(e)]
)