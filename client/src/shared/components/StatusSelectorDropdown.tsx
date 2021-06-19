import { FieldInputProps } from 'formik';
import React, { useEffect, useState } from 'react';
import { StatusPayload } from '../models/StatusPayload';
import { findAllStatuses } from '../services/status.service';
import { Dropdown } from 'primereact/dropdown';

const StatusSelectorDropdown = (props: { fieldInputProps: FieldInputProps<any> }) => {
    const [options, setOptions] = useState<StatusPayload[] | null>(null);

    useEffect(() => {
        findAllStatuses().then((statuses: StatusPayload[]) => setOptions(statuses));
    }, []);

    return (
        <Dropdown
            options={options ?? []}
            optionLabel="name"
            {...props.fieldInputProps}
            disabled={!options}
            placeholder="Select status"
        />
    );
};

export default StatusSelectorDropdown;
