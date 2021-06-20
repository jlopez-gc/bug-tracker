import React from 'react';
import BugInformationEditor from '../BugInformationEditor';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import * as statusService from '../../shared/services/status.service';
import { emptyBug } from '../../fixtures/bug.fixtures';
import { mockedStatuses } from '../../fixtures/status.fixtures';

beforeEach(() => jest.spyOn(statusService, 'findAllStatuses').mockImplementation(() => Promise.resolve(mockedStatuses)));

test('it shows require validation messages information is not filled', async () => {
    const handleSubmit = jest.fn();
    const { findByText } = render(<BugInformationEditor onSubmit={handleSubmit} initialValues={emptyBug} />);

    userEvent.click(screen.getByRole('button', { name: /submit/i }));

    const nameErrorMessage = await findByText(/Name is required/i);
    const statusErrorMessage = await findByText(/Status is required/i);

    expect(nameErrorMessage).toBeInTheDocument();
    expect(statusErrorMessage).toBeInTheDocument();

    expect(handleSubmit).not.toHaveBeenCalled();

    jest.clearAllMocks();
});

test('it shows max length validation message when name is too long', async () => {
    const handleSubmit = jest.fn();
    const { findByText } = render(
        <BugInformationEditor
            onSubmit={handleSubmit}
            initialValues={{
                name: 'In semper nisl eget tellus consequat luctus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut congue lacinia erat, ut facilisis dui facilisis a. Vestibulum sapien ipsum, malesuada a vehicula id, mattis et orci. Sed quis neque sit amet ipsum mattis congue vel sit amet nisl. Morbi efficitur tortor rhoncus ullamcorper vulputate. In rhoncus massa non rutrum efficitur. Nulla hendrerit urna eu laoreet euismod. Ut placerat tincidunt nisi, a accumsan lectus volutpat eget. Quisque non tempor nunc. Quisque pellentesque pulvinar tellus, in pretium lectus bibendum mollis. Curabitur rhoncus nisl nec dolor placerat, sit amet rhoncus dui ultrices.',
                description: '',
                id: null,
                status: mockedStatuses[0],
                createdAt: '',
                updatedAt: '',
            }}
        />,
    );

    userEvent.click(screen.getByRole('button', { name: /submit/i }));

    const nameErrorMessage = await findByText(/Exceeded maximum length/i);

    expect(nameErrorMessage).toBeInTheDocument();

    expect(handleSubmit).not.toHaveBeenCalled();

    jest.clearAllMocks();
});

test('it calls submit function when data is correct', async () => {
    const handleSubmit = jest.fn();
    render(
        <BugInformationEditor
            onSubmit={handleSubmit}
            initialValues={{
                name: 'Testing issue',
                description: '',
                id: null,
                status: mockedStatuses[0],
                createdAt: '',
                updatedAt: '',
            }}
        />,
    );

    userEvent.click(screen.getByRole('button', { name: /submit/i }));

    await waitFor(() => expect(handleSubmit).toHaveBeenCalled());

    jest.clearAllMocks();
});
