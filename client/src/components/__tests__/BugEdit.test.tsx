import React from 'react';
import { renderWithRouterWrapper } from '../../testing.util';
import BugEdit from '../BugEdit';
import * as bugService from '../../shared/services/bug.service';
import * as statusService from '../../shared/services/status.service';
import { AxiosResponse } from 'axios';
import userEvent from '@testing-library/user-event';
import { screen } from '@testing-library/react';
import { mockedStatuses } from '../../fixtures/status.fixtures';
import { validBug } from '../../fixtures/bug.fixtures';

beforeEach(() => {
    jest.spyOn(bugService, 'findBugById').mockImplementation(() => Promise.resolve(validBug));

    jest.spyOn(statusService, 'findAllStatuses').mockResolvedValue(mockedStatuses);
});

test('displays bug information when bug exists', async () => {
    const { findByDisplayValue } = renderWithRouterWrapper(<BugEdit path="/bug/:id" />, { route: '/bug/1' });
    const nameElement = await findByDisplayValue(/Test Bug 1/i);
    const descriptionElement = await findByDisplayValue(/This is a simple bug description/i);
    const statusElement = await findByDisplayValue(/Pending/i);
    expect(nameElement).toBeInTheDocument();
    expect(descriptionElement).toBeInTheDocument();
    expect(statusElement).toBeInTheDocument();
});

test('displays success message when bug updated', async () => {
    jest.spyOn(bugService, 'updateBugById').mockResolvedValue({} as AxiosResponse);

    const { findByDisplayValue, findByText } = renderWithRouterWrapper(<BugEdit path="/bug/:id" />, { route: '/bug/1' });

    const nameElement = await findByDisplayValue(/Test Bug 1/i);
    expect(nameElement).toBeInTheDocument();

    userEvent.click(screen.getByRole('button', { name: /submit/i }));

    const successMessage = await findByText(/Bug updated successfully/i);

    expect(successMessage).toBeInTheDocument();
});

test('displays error message when bug could not be updated', async () => {
    jest.spyOn(bugService, 'updateBugById').mockRejectedValue({} as AxiosResponse);

    const { findByDisplayValue, findByText } = renderWithRouterWrapper(<BugEdit path="/bug/:id" />, { route: '/bug/1' });

    const nameElement = await findByDisplayValue(/Test Bug 1/i);
    expect(nameElement).toBeInTheDocument();

    userEvent.click(screen.getByRole('button', { name: /submit/i }));

    const successMessage = await findByText(/Bug could not be updated/i);

    expect(successMessage).toBeInTheDocument();
});
