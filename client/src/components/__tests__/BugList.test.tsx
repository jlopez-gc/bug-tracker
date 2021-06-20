import React from 'react';
import BugList from '../BugList';
import userEvent from '@testing-library/user-event';
import { renderWithRouterWrapper } from '../../testing.util';
import * as bugService from '../../shared/services/bug.service';
import { act } from '@testing-library/react';
import { mockedIssues } from '../../fixtures/bug.fixtures';

afterEach(() => jest.clearAllMocks());

test('renders the bug id, name and status when found', async () => {
    jest.spyOn(bugService, 'findAllBugs').mockImplementation(() => Promise.resolve(mockedIssues));

    const { findByText } = renderWithRouterWrapper(<BugList path="/" />);
    const idElement = await findByText(/524/i);
    expect(idElement).toBeInTheDocument();
    const nameElement = await findByText(/Test Bug 1/i);
    expect(nameElement).toBeInTheDocument();
    const statusElement = await findByText(/Pending/i);
    expect(statusElement).toBeInTheDocument();
});

test('filters result using search input', async () => {
    jest.spyOn(bugService, 'findAllBugs').mockImplementation(() => Promise.resolve(mockedIssues));

    const { getByTestId, findByText } = renderWithRouterWrapper(<BugList path="/" />);

    const nameElement = await findByText(/Test Bug 1/i);
    expect(nameElement).toBeInTheDocument();
    const searchInput = getByTestId(/search-input/i);
    userEvent.type(searchInput, 'Impossible');
    expect(nameElement).not.toBeInTheDocument();
    const noRecordMessage = await findByText(/No records found/i);
    expect(noRecordMessage).toBeInTheDocument();
});

test('renders error message on error', async () => {
    jest.spyOn(bugService, 'findAllBugs').mockImplementation(() => Promise.reject());
    const { findByText } = renderWithRouterWrapper(<BugList path="/" />);
    const linkElement = await findByText(/An error occurred, please try again later/i);
    expect(linkElement).toBeInTheDocument();
});

test('redirects to bug creation page when create button is clicked', async () => {
    jest.spyOn(bugService, 'findAllBugs').mockImplementation(() => Promise.resolve(mockedIssues));
    const { getByRole, findByText } = renderWithRouterWrapper(<BugList path="/" />);
    await findByText(/Test Bug 1/i);
    const buttonElement = getByRole('button', { name: /Create bug/i });
    expect(buttonElement).toBeInTheDocument();
    act(() => userEvent.click(buttonElement));
    const headerElement = await findByText(/Create bug/i);
    expect(headerElement).toBeInTheDocument();
});
