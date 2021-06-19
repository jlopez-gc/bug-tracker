import React, { ChangeEvent, MutableRefObject, useEffect, useRef, useState } from 'react';
import { Messages } from 'primereact/messages';
import { InputText } from 'primereact/inputtext';
import { DataTable, DataTableRowClickEventParams } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { BugPayload } from '../shared/models/BugPayload';
import { findAllBugs } from '../shared/services/bug.service';
import { RouteComponentProps, useNavigate } from '@reach/router';
import { Button } from 'primereact/button';

const BugList: React.FunctionComponent<RouteComponentProps> = () => {
    const [bugs, setBugs] = useState<BugPayload[]>([]);
    const [globalFilter, setGlobalFilter] = useState<string>('');
    const messagesReference: MutableRefObject<Messages | null> = useRef(null);
    const navigate = useNavigate();

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
            <div className="row mb-1">
                <div className="col">
                    <Messages ref={messagesReference} />
                </div>
            </div>
            <div className="row mb-3">
                <div className="col">
                    <span className="p-input-icon-left">
                        <i className="pi pi-search" />
                        <InputText
                            placeholder="Search"
                            value={globalFilter}
                            onChange={(event: ChangeEvent<HTMLInputElement>) => setGlobalFilter(event.target.value)}
                            data-testid="search-input"
                        />
                    </span>
                    <Button type="button" className="float-end" onClick={() => navigate('/bug')}>
                        Create Bug
                    </Button>
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <DataTable
                        value={bugs}
                        removableSort
                        paginator
                        rows={10}
                        globalFilter={globalFilter}
                        onRowClick={(event: DataTableRowClickEventParams) => navigate(`/bug/${event.data.id}`)}
                    >
                        <Column field="id" header="ID" sortable />
                        <Column field="name" header="Name" sortable />
                        <Column field="status.name" header="Status" filterField="status.name" sortable />
                    </DataTable>
                </div>
            </div>
        </>
    );
};

export default BugList;
