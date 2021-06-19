import React, { MutableRefObject, useRef } from 'react';
import { RouteComponentProps, useNavigate } from '@reach/router';
import { BugPayload } from '../shared/models/BugPayload';
import { Messages } from 'primereact/messages';
import BugInformationEditor from './BugInformationEditor';
import { createBug } from '../shared/services/bug.service';

const BugCreate: React.FunctionComponent<RouteComponentProps> = () => {
    const messagesRef: MutableRefObject<Messages | null> = useRef(null);
    const navigate = useNavigate();

    const handleSubmit = (values: BugPayload) => {
        createBug(values).then(() => navigate('/'));
    };

    return (
        <>
            <Messages ref={messagesRef} />
            <div className="row">
                <div className="col">
                    <h3>Create Bug</h3>
                    <BugInformationEditor
                        onSubmit={handleSubmit}
                        initialValues={{
                            name: '',
                            description: '',
                            id: null,
                            status: null,
                            createdAt: '',
                            updatedAt: '',
                        }}
                    />
                </div>
            </div>
        </>
    );
};

export default BugCreate;
