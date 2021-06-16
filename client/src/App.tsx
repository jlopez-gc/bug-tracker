import React, {ChangeEvent, MutableRefObject, useEffect, useRef, useState} from 'react';
import './App.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {findAllBugs} from "./shared/services/bug.service";
import {InputText} from "primereact/inputtext";
import {Bug} from "./shared/models/Bug";
import {Messages} from "primereact/messages";

function App() {
    const [bugs, setBugs] = useState<Bug[]>([]);
    const [globalFilter, setGlobalFilter] = useState<string>('');
    const messagesReference: MutableRefObject<Messages | null> = useRef(null);

    useEffect(() => {
        findAllBugs()
            .then((bugs: Bug[]) => setBugs(bugs))
            .catch(() => messagesReference.current?.show({severity: "error", detail: "An error occurred, please try again later", sticky: true}));
    }, []);

    return (
        <div className="App">
            <header className="App-header">
                <Messages ref={messagesReference}/>
                <span className="p-input-icon-left" style={{marginBottom: "8px"}}>
                    <i className="pi pi-search"/>
                    <InputText placeholder="Search" value={globalFilter}
                               onChange={(event: ChangeEvent<HTMLInputElement>) => setGlobalFilter(event.target.value)}/>
                </span>
                <DataTable value={bugs} removableSort paginator rows={10} globalFilter={globalFilter}>
                    <Column field="id" header="ID" sortable/>
                    <Column field="name" header="Name" sortable/>
                    <Column field="description" header="Description" sortable/>
                </DataTable>
            </header>
        </div>
    );
}


export default App;
