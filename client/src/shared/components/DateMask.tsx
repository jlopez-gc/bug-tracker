import { isEmpty, isNil } from 'lodash';
import React from 'react';
import DateTimeFormatOptions from '../models/DateTimeFormatOptions';

const DateMask = (props: { date: string; options?: DateTimeFormatOptions }) => {
    const defaultOptions: DateTimeFormatOptions = {
        year: 'numeric',
        month: 'numeric',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        hour12: false,
    };
    return (
        <span>
            {isNil(props.date) || isEmpty(props.date)
                ? ''
                : Intl.DateTimeFormat(navigator.language, { ...defaultOptions, ...props.options }).format(
                      new Date(props.date),
                  )}
        </span>
    );
};

export default DateMask;
