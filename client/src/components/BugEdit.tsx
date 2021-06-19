import React, { MutableRefObject, useEffect, useRef, useState } from 'react';
import { RouteComponentProps, useParams } from '@reach/router';
import { BugPayload } from '../shared/models/BugPayload';
import { findBugById, updateBugById } from '../shared/services/bug.service';
import { Messages } from 'primereact/messages';
import DateMask from '../shared/components/DateMask';
import BugInformationEditor from './BugInformationEditor';

const BugEdit: React.FunctionComponent<RouteComponentProps> = () => {
    const routeParams = useParams();

    const [bug, setBug] = useState<BugPayload>({
        name: '',
        description: '',
        id: null,
        status: null,
        createdAt: '',
        updatedAt: '',
    });

    const messagesRef: MutableRefObject<Messages | null> = useRef(null);

    useEffect(() => {
        findBugById(routeParams.id)
            .then((bug) => setBug(bug))
            .catch((error) => {
                messagesRef.current?.show({
                    severity: 'error',
                    sticky: true,
                    detail: error.response.data.message ?? 'An unknown error occurred, please try again later',
                });
            });
    }, [routeParams.id]);

    const handleSubmit = (values: BugPayload) => {
        updateBugById(routeParams.id, values).then(() => console.log('Updated'));
    };

    return (
        <>
            <Messages ref={messagesRef} />
            <div className="row">
                <div className="col-8">
                    <h3>Edit Bug</h3>
                    <BugInformationEditor onSubmit={handleSubmit} initialValues={bug} />
                </div>
                <div className="col-4">
                    <h3>Information</h3>
                    <div>
                        <span className="fw-bold">ID: </span>
                        <span>{bug.id}</span>
                    </div>
                    <div>
                        <span className="fw-bold">Created: </span>
                        <DateMask date={bug.createdAt} />
                    </div>
                    <div>
                        <span className="fw-bold">Updated: </span>
                        <DateMask date={bug.updatedAt} />
                    </div>
                </div>
            </div>
        </>
    );
};

export default BugEdit;
