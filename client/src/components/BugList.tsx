import React, { ChangeEvent, MutableRefObject, useEffect, useRef, useState } from 'react';
import { Messages } from 'primereact/messages';
import { InputText } from 'primereact/inputtext';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { BugPayload } from '../shared/models/BugPayload';
import { findAllBugs } from '../shared/services/bug.service';

const BugList: React.FunctionComponent = () => {
    const [bugs, setBugs] = useState<BugPayload[]>([]);
    const [globalFilter, setGlobalFilter] = useState<string>('');
    const messagesReference: MutableRefObject<Messages | null> = useRef(null);

    useEffect(() => {
        findAllBugs()
            .then((bugs: BugPayload[]) => setBugs(bugs))
            .catch(() =>
                messagesReference.current?.show({
                    severity: 'error',
                    detail: 'An error occurred, please try again later',
                    sticky: true,
                }),
            );
    }, []);

    return (
        <>
            <Messages ref={messagesReference} />
            <span className="p-input-icon-left" style={{ marginBottom: '8px' }}>
                <i className="pi pi-search" />
                <InputText
                    placeholder="Search"
                    value={globalFilter}
                    onChange={(event: ChangeEvent<HTMLInputElement>) => setGlobalFilter(event.target.value)}
                />
            </span>
            <DataTable value={bugs} removableSort paginator rows={10} globalFilter={globalFilter}>
                <Column field="id" header="ID" sortable />
                <Column field="name" header="Name" sortable />
                <Column field="status.name" header="Status" filterField="status.name" sortable />
            </DataTable>
        </>
    );
};

export default BugList;
