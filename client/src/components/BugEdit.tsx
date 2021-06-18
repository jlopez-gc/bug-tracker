import React, { MutableRefObject, useEffect, useRef, useState } from 'react';
import { RouteComponentProps, useParams } from '@reach/router';
import { BugPayload } from '../shared/models/BugPayload';
import { findBugById } from '../shared/services/bug.service';
import { Messages } from 'primereact/messages';
import { Field, FieldProps, Form, Formik, FormikValues } from 'formik';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import DateMask from '../shared/components/DateMask';

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

    const options = [
        { id: 1, name: 'Pending' },
        { id: 2, name: 'In Progress' },
        { id: 3, name: 'Done' },
    ];

    return (
        <>
            <Messages ref={messagesRef} />
            <div className="row">
                <div className="col-8">
                    <h3>Edit Bug</h3>
                    <Formik
                        onSubmit={(values: FormikValues) => console.log('Submit', values)}
                        initialValues={bug}
                        enableReinitialize
                    >
                        <Form>
                            <div className="row mb-3">
                                <div className="col">
                                    <Field name="name">
                                        {(fieldProps: FieldProps) => (
                                            <InputText className="form-control" {...fieldProps.field} />
                                        )}
                                    </Field>
                                </div>
                                <div className="col">
                                    <Field name="status">
                                        {(fieldProps: FieldProps) => (
                                            <Dropdown options={options} optionLabel="name" {...fieldProps.field} />
                                        )}
                                    </Field>
                                </div>
                            </div>
                            <div className="row mb-3">
                                <div className="col">
                                    <Field name="description">
                                        {(fieldProps: FieldProps) => (
                                            <InputTextarea rows={10} className="form-control " {...fieldProps.field} />
                                        )}
                                    </Field>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col">
                                    <Button type="submit">Submit</Button>
                                </div>
                            </div>
                        </Form>
                    </Formik>
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
