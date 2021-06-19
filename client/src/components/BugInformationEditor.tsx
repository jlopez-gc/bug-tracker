import React from 'react';
import { ErrorMessage, Field, FieldProps, Form, Formik } from 'formik';
import { InputText } from 'primereact/inputtext';
import StatusSelectorDropdown from '../shared/components/StatusSelectorDropdown';
import { InputTextarea } from 'primereact/inputtextarea';
import { Button } from 'primereact/button';
import { BugPayload } from '../shared/models/BugPayload';
import * as Yup from 'yup';

const BugSchema = Yup.object().shape({
    name: Yup.string().max(255, 'Exceeded maximum length').required('Name is required'),
    description: Yup.string(),
    status: Yup.object().nullable(true).required('Status is required'),
});

const BugInformationEditor = (props: { onSubmit: any; initialValues: BugPayload }) => {
    return (
        <Formik
            onSubmit={props.onSubmit}
            initialValues={props.initialValues}
            validationSchema={BugSchema}
            enableReinitialize
        >
            <Form>
                <div className="row mb-3">
                    <div className="col-8">
                        <Field name="name">
                            {(fieldProps: FieldProps) => <InputText className="form-control" {...fieldProps.field} />}
                        </Field>
                        <CustomErrorMessage name="name" />
                    </div>
                    <div className="col-4">
                        <Field name="status">
                            {(fieldProps: FieldProps) => <StatusSelectorDropdown fieldInputProps={fieldProps.field} />}
                        </Field>
                        <br />
                        <CustomErrorMessage name="status" />
                    </div>
                </div>
                <div className="row mb-3">
                    <div className="col">
                        <Field name="description">
                            {(fieldProps: FieldProps) => (
                                <InputTextarea rows={10} className="form-control " {...fieldProps.field} />
                            )}
                        </Field>
                        <CustomErrorMessage name="description" />
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <Button type="submit">Submit</Button>
                    </div>
                </div>
            </Form>
        </Formik>
    );
};

interface CustomErrorMessageProps {
    name: string;
}

const CustomErrorMessage: React.FunctionComponent<CustomErrorMessageProps> = (props) => {
    const { name } = props;

    return (
        <ErrorMessage name={name}>
            {(errorMessage: String) => <div className="text-danger">{errorMessage}</div>}
        </ErrorMessage>
    );
};

export default BugInformationEditor;
